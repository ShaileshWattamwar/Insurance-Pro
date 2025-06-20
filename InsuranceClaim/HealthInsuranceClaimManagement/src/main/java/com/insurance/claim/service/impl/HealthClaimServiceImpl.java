package com.insurance.claim.service.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insurance.claim.FeignClient.PaymentFeignClient;
import com.insurance.claim.FeignClient.PreAuthServiceClient;
import com.insurance.claim.constants.AuthStatus;
import com.insurance.claim.constants.ClaimStatus;
import com.insurance.claim.dto.PaymentDetailsDTO;
import com.insurance.claim.dto.request.CashlessRequestDTO;
import com.insurance.claim.dto.request.ReimbursementRequestDTO;
import com.insurance.claim.dto.response.BankDetailsResponseDTO;
import com.insurance.claim.dto.response.CashlessResponseDTO;
import com.insurance.claim.dto.response.PreAuthClientResponse;
import com.insurance.claim.dto.response.ReimbursementResponseDTO;
import com.insurance.claim.entities.HospitalEntity;
import com.insurance.claim.entities.PolicyClaimEntity;
import com.insurance.claim.entities.PolicyHolder;
import com.insurance.claim.exceptions.InvalidInputException;
import com.insurance.claim.exceptions.ResourceNotFoundException;
import com.insurance.claim.repositories.HospitalRepository;
import com.insurance.claim.repositories.PolicyClaimRepository;
import com.insurance.claim.repositories.PolicyHolderRepository;
import com.insurance.claim.services.interfaces.HealthClaimService;
import com.insurance.claim.utils.ClaimValidationUtils;
import com.insurance.claim.utils.MappingUtils;
import com.insurance.claim.utils.NotificationHandler;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class HealthClaimServiceImpl implements HealthClaimService {

	private final PolicyClaimRepository policyClaimRepo;
	private final PolicyHolderRepository holderRepo;
	private final HospitalRepository hospitalRepo;
	private final ClaimValidationUtils validationUtil;
	private final PaymentFeignClient paymentClient;
	private final PreAuthServiceClient preAuthClient;
	private final NotificationHandler notificationHandler;
	private final MappingUtils mapper;

	private static final Logger logger = LoggerFactory.getLogger(HealthClaimServiceImpl.class);

	@Override
	public CashlessResponseDTO initiateCashlessClaim(CashlessRequestDTO reqDTO) {
		logger.info("Started Cashless Claim || policyHolderId={} hospitalId={} admissionType={}",
				reqDTO.getPolicyHolderId(), reqDTO.getHospitalId(), reqDTO.getAdmissionType());

		HospitalEntity hospital = getHospital(reqDTO.getHospitalId());
		PolicyHolder holder = getPolicyHolder(reqDTO.getPolicyHolderId());

		boolean hasPreAuth = reqDTO.getPreAuthId() != null && !reqDTO.getPreAuthId().isBlank();
		logger.info("is Pre-Auth Present?|| hasPreAuth={}", hasPreAuth);

		validationUtil.isCashlessAllowed(reqDTO, hospital.getHospitalType(), hasPreAuth);

		PolicyClaimEntity claimEntity = mapper.cashlessToEntity(reqDTO, holder, hospital);
		claimEntity.setClaimStatus(ClaimStatus.INITIATED);
		logger.info("Mapped Cashless resquest DTO to Entity || CashlessClaimEntity={}", claimEntity);

		PolicyClaimEntity savedClaim = policyClaimRepo.save(claimEntity);
		logger.info("Claim saved | claimId={}, status={}", savedClaim.getPolicyClaimId(), savedClaim.getClaimStatus());

		switch (reqDTO.getAdmissionType()) {

		case PLANNED -> {

			if (!hasPreAuth) {
				throw new IllegalArgumentException("Pre-Authorization is mandatory for Planned admission.");
			}

			try {
				ResponseEntity<PreAuthClientResponse> preAuthResponse = preAuthClient
						.getPreAuthDetailsById(reqDTO.getPreAuthId());

				String preAuthStatus = preAuthResponse.getBody().getAuthStatus().name();
				BigDecimal approvedAmount = preAuthResponse.getBody().getApprovedAmount();

				if (AuthStatus.APPROVED.name().equalsIgnoreCase(preAuthStatus)) {
					if (approvedAmount == null || approvedAmount.compareTo(BigDecimal.ZERO) <= 0) {
						throw new InvalidInputException(
								"Approved amount must be present in Pre-Auth for Planned Admission.");
					}
					savedClaim.setApprovedAmount(approvedAmount);
					savedClaim.setClaimStatus(ClaimStatus.APPROVED);
				} else {
					savedClaim.setClaimStatus(ClaimStatus.REJECTED);
				}

			} catch (Exception e) {
				logger.error("Failed to fetch pre-auth details: {}", e.getMessage());

			}

			policyClaimRepo.save(savedClaim);
			validationUtil.saveClaimStatusHistory(savedClaim);

			// Publish NotificationEvent for APPROVED/ REJECTED
//			publishNotification(savedClaim, holder);
		}

		case EMERGENCY -> {

			boolean isValid = validationUtil.validatePolicyAndHolder(reqDTO.getPolicyHolderId(),
					reqDTO.getExpectedCost(), holder);
			ClaimStatus newStatus = isValid ? ClaimStatus.APPROVED : ClaimStatus.REJECTED;

			updateClaimStatus(savedClaim, newStatus, isValid ? reqDTO.getExpectedCost() : null);

			// Publish NotificationEvent for APPROVED/ REJECTED
//			publishNotification(savedClaim, holder);
		}

		default -> throw new IllegalArgumentException("Unsupported Admission Type: " + reqDTO.getAdmissionType());
		}

		return CashlessResponseDTO.builder().claimId(savedClaim.getPolicyClaimId()).preAuthId(savedClaim.getPreAuthId())
				.claimType(savedClaim.getClaimType()).claimStatus(savedClaim.getClaimStatus())
				.createdAt(savedClaim.getCreatedAt()).approvedAmount(savedClaim.getApprovedAmount())
				.message("Your Cashless Claim has been " + savedClaim.getClaimStatus() + " Successfully").build();
	}

	@Override
	public ReimbursementResponseDTO initiateReimbursementClaim(ReimbursementRequestDTO reqDTO) {
		logger.info("Started REIMBURSEMENT Claim || policyHolderId={} hospitalType={} admissionType={}",
				reqDTO.getPolicyHolderId(), reqDTO.getHospitalDetails().getHospitalType(), reqDTO.getAdmissionType());

		validationUtil.isReimbursementAllowed(reqDTO);

		// PaymentService Feign call
		PaymentDetailsDTO paymentDetails = paymentClient.savePaymentDetails(reqDTO.getPaymentDetails());
		Long paymentDetailsId = paymentDetails.getPaymentDetailsId();
		PolicyHolder policyHolder = getPolicyHolder(reqDTO.getPolicyHolderId());

		// 2. Map DTO to Entity
		PolicyClaimEntity claimEntity = mapper.reimbursementToEntity(reqDTO, paymentDetailsId, policyHolder);
		claimEntity.setClaimStatus(ClaimStatus.INITIATED);
		logger.info("Mapped requestDTO to Entity={}", claimEntity);

		PolicyClaimEntity savedClaim = policyClaimRepo.save(claimEntity);
		logger.info("Reimbursement Claim saved in DB || PolicyClaimEntity={}", savedClaim);

		// 3. Save INITIATED status history
		validationUtil.saveClaimStatusHistory(savedClaim);

		boolean isValid = validationUtil.validatePolicyAndHolder(reqDTO.getPolicyHolderId(), reqDTO.getClaimedAmount(),
				policyHolder);
		ClaimStatus newStatus = isValid ? ClaimStatus.APPROVED : ClaimStatus.REJECTED;

		updateClaimStatus(savedClaim, newStatus, isValid ? reqDTO.getClaimedAmount() : null);

//		5. PaymentService Feign call
		BankDetailsResponseDTO bankDetails = paymentClient.getBankDetailsById(reqDTO.getBankDetailsId());
		
		return buildReimbursementResponse(savedClaim, bankDetails);
	}

	private HospitalEntity getHospital(Long hospitalId) {
		HospitalEntity hospital = hospitalRepo.findById(hospitalId)
				.orElseThrow(() -> new ResourceNotFoundException("Hospital Not found with ID:: " + hospitalId));
		logger.info("Hospital Info || id={} name={} type={}", hospital.getHospitalId(), hospital.getHospitalName(),
				hospital.getHospitalType());

		return hospital;
	}

	private PolicyHolder getPolicyHolder(String policyHolderId) {
		PolicyHolder holder = holderRepo.findById(policyHolderId).orElseThrow(
				() -> new ResourceNotFoundException("PolicyHolder doesn't exist with ID:: " + policyHolderId));
		logger.info("PolicyHolder Info || id={} name={}", holder.getPolicyHolderId(),
				holder.getFirstName() + " " + holder.getLastName());

		return holder;
	}
	
	private ReimbursementResponseDTO buildReimbursementResponse(PolicyClaimEntity savedClaim, BankDetailsResponseDTO bankDetails) {
		return ReimbursementResponseDTO.builder().claimId(savedClaim.getPolicyClaimId())
				.claimType(savedClaim.getClaimType()).claimStatus(savedClaim.getClaimStatus())
				.createdAt(savedClaim.getCreatedAt()).bankDetails(bankDetails) // fetch from 
				.approvedAmount(savedClaim.getApprovedAmount())
				.message("REIMBURSEMENT claim " + savedClaim.getClaimStatus() + " successfully").build();
	}

	private void updateClaimStatus(PolicyClaimEntity claim, ClaimStatus newStatus, BigDecimal approvedAmount) {
		if (newStatus == ClaimStatus.APPROVED) {
			if (approvedAmount == null || approvedAmount.compareTo(BigDecimal.ZERO) <= 0) {
				throw new InvalidInputException("Approved amount must be provided for APPROVED status.");
			}
			claim.setApprovedAmount(approvedAmount);
		}
		claim.setClaimStatus(newStatus);
		policyClaimRepo.save(claim);
		validationUtil.saveClaimStatusHistory(claim);

		// Publish NotificationEvent for APPROVED/ REJECTED
		notificationHandler.handleNotification(claim);
		logger.info("Notified || PolicyHolder={} and Hospital={}", claim.getPolicyHolder().getPolicyHolderId(), claim.getHospital().getHospitalId());
	}

}
