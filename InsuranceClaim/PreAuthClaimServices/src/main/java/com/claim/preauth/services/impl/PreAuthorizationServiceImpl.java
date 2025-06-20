package com.claim.preauth.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.claim.preauth.constant.AuthStatus;
import com.claim.preauth.constant.DecisionReason;
import com.claim.preauth.dto.request.PreAuthRequestDTO;
import com.claim.preauth.dto.response.HealthPolicyDTO;
import com.claim.preauth.dto.response.HospitalDTO;
import com.claim.preauth.dto.response.PolicyholderDTO;
import com.claim.preauth.dto.response.PreAuthResponseDTO;
import com.claim.preauth.dto.response.PreAuthStatusHistoryResponse;
import com.claim.preauth.entity.PreAuthStatusHistoryEntity;
import com.claim.preauth.entity.PreAuthorizationEntity;
import com.claim.preauth.exceptions.InvalidPreAuthRequestException;
import com.claim.preauth.exceptions.ResourceNotFoundException;
import com.claim.preauth.feignclient.InsuranceClaimClient;
import com.claim.preauth.repository.PreAuthStatusHistoryRepository;
import com.claim.preauth.repository.PreAuthorizationRepository;
import com.claim.preauth.services.PreAuthorizationService;
import com.claim.preauth.utils.NotificationHandler;
import com.claim.preauth.utils.PreAuthValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PreAuthorizationServiceImpl implements PreAuthorizationService {

	private final PreAuthorizationRepository preAuthRepo;
	private final PreAuthStatusHistoryRepository statusHistoryRepo;
	private final InsuranceClaimClient insuranceClaimClient;
	private final NotificationHandler notificationHandler;
	private final PreAuthValidator validator;
	private final ModelMapper modelMapper;
	private static final Logger logger = LoggerFactory.getLogger(PreAuthorizationServiceImpl.class);

	@Override
	public PreAuthResponseDTO initiatePreAuthorization(PreAuthRequestDTO preAuthDto) {

		// If Planned + Cashless + Network Then Proceed
		HospitalDTO hospital = insuranceClaimClient.getHospital(preAuthDto.getHospitalId());
		boolean isAllowed = validator.isPreAuthAllowed(hospital, preAuthDto.getAdmissionType(),
				preAuthDto.getClaimType(), preAuthDto.getHospitalId());

		if (!isAllowed) {
			throw new InvalidPreAuthRequestException("Pre-Authorization allowed for Planned + Cashless + Network.");
		}
		// DTO-Entity
		PreAuthorizationEntity authEntity = modelMapper.map(preAuthDto, PreAuthorizationEntity.class);

		HealthPolicyDTO policy = insuranceClaimClient.getPolicy(preAuthDto.getPolicyId());
		PolicyholderDTO policyholder = insuranceClaimClient.getPolicyholder(preAuthDto.getPolicyHolderId());

		// Validate: Policy and PolicyHolder
		DecisionReason claimValidate = validator.validatePreAuth(preAuthDto, policy, policyholder);
		if (claimValidate.equals(DecisionReason.VALIDATION_SUCCESS)) {
			authEntity.setAuthStatus(AuthStatus.APPROVED);
		} else {
			authEntity.setAuthStatus(AuthStatus.REJECTED);
		}

		// PreAuthRequestDTO store into DB
		PreAuthorizationEntity savedPreAuth = preAuthRepo.save(authEntity);
		logger.info("Saved Pre-Auth Request || ID={}", savedPreAuth.getPreAuthRequestId());

		// DocumentFeignClient through handler
//		documentHandler.upload(file, savedPreAuth);

		// update status as Approved/Rejected
		saveStatusHistory(savedPreAuth, "Pre-Authorization processed: " + savedPreAuth.getAuthStatus().toString());

		// Fetch uploaded docs
//		List<DocumentResponse> documentList = documentHandler.fetchDocuments(authEntity.getPreAuthRequestId());
//		validator.validateDocument(documentList, savedPreAuth.getPreAuthRequestId());
//		logger.info("Retrieving List of Documents|| documentList={}", documentList);

		// PreAuth Response
		PreAuthResponseDTO preAuthResponse = buildPreAuthResponse(savedPreAuth);

		// Notify PolicyHolder with 'AuthStatus.APPROVED/REJECTED'
		notificationHandler.handleNotification(savedPreAuth, policyholder, hospital);
		logger.info("Notified || PolicyHolder={} and Hospital={}", policyholder.getPolicyHolderId(),
				hospital.getHospitalId());

		logger.info("Sending Response to PolicyHolder || preAuthResponse={}", preAuthResponse);
		return preAuthResponse;
	}

	private void saveStatusHistory(PreAuthorizationEntity preAuthEntity, String remark) {
		// Update Status
		PreAuthStatusHistoryEntity preAuthStatusHistoryEntity = PreAuthStatusHistoryEntity.builder()
				.preAuthId(preAuthEntity.getPreAuthRequestId()).currentStatus(preAuthEntity.getAuthStatus())
				.remarks(remark).updatedAt(LocalDateTime.now()).build();

		logger.info("Pre-Auth Status Updated || preAuthStatusHistoryEntity={} ", preAuthStatusHistoryEntity);
		statusHistoryRepo.save(preAuthStatusHistoryEntity);
	}

	private PreAuthResponseDTO buildPreAuthResponse(PreAuthorizationEntity savedPreAuth) {
		return PreAuthResponseDTO.builder().preAuthId(savedPreAuth.getPreAuthRequestId())
				.policyHolderId(savedPreAuth.getPolicyHolderId()).hospitalId(savedPreAuth.getHospitalId())
				.healthCard(savedPreAuth.getHealthCard()).authStatus(savedPreAuth.getAuthStatus())
				.message("Pre-authorization request submitted successfully.").createdAt(savedPreAuth.getCreatedAt())
				.build();
	}

	public List<PreAuthStatusHistoryResponse> getStatusHistoryById(String id) {
		List<PreAuthStatusHistoryEntity> preAuthHistory = statusHistoryRepo.findByPreAuthId(id);
		return preAuthHistory.stream().map(entity -> modelMapper.map(entity, PreAuthStatusHistoryResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<PreAuthRequestDTO> getAllPreAuths() {
		List<PreAuthorizationEntity> allPreAuths = preAuthRepo.findAll();
		return allPreAuths.stream().map(entity -> modelMapper.map(entity, PreAuthRequestDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public PreAuthResponseDTO getPreAuthDetailsById(String id) {
		PreAuthorizationEntity preAuthDetails = preAuthRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pre-Authorization Request Not Found with ID:" + id));
		return PreAuthResponseDTO.builder().preAuthId(preAuthDetails.getPreAuthRequestId())
				.policyHolderId(preAuthDetails.getPolicyHolderId()).hospitalId(preAuthDetails.getHospitalId())
				.healthCard(preAuthDetails.getHealthCard()).authStatus(preAuthDetails.getAuthStatus())
				.message("Pre-Authorization has been Successfully").createdAt(preAuthDetails.getCreatedAt()).build();
	}

}
