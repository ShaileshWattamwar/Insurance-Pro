package com.insurance.claim.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.insurance.claim.FeignClient.DocumentServiceClient;
import com.insurance.claim.constants.AdmissionType;
import com.insurance.claim.constants.ClaimContants;
import com.insurance.claim.constants.ClaimType;
import com.insurance.claim.constants.HospitalType;
import com.insurance.claim.constants.PolicyStatus;
import com.insurance.claim.dto.request.CashlessRequestDTO;
import com.insurance.claim.dto.request.ReimbursementRequestDTO;
import com.insurance.claim.dto.response.DocumentResponse;
import com.insurance.claim.entities.ClaimStatusHistory;
import com.insurance.claim.entities.HealthPolicy;
import com.insurance.claim.entities.PolicyClaimEntity;
import com.insurance.claim.entities.PolicyHolder;
import com.insurance.claim.exceptions.CashlessNotAllowedException;
import com.insurance.claim.exceptions.InsufficientCoverageException;
import com.insurance.claim.exceptions.NoDocumentUploadException;
import com.insurance.claim.exceptions.PolicyInactiveException;
import com.insurance.claim.exceptions.ReimbursementNotAllowedException;
import com.insurance.claim.exceptions.ResourceNotFoundException;
import com.insurance.claim.repositories.ClaimStatusRepository;
import com.insurance.claim.repositories.HealthPolicyRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClaimValidationUtils {

	private final HealthPolicyRepository policyRepo;
	private final DocumentServiceClient documentClient;
	private final ClaimStatusRepository claimStatusRepository;

	private static final Logger logger = LoggerFactory.getLogger(ClaimValidationUtils.class);
	
	// CASHLESS CLAIM VALIDATION 
	public void isCashlessAllowed(CashlessRequestDTO reqDTO, HospitalType hospitalType, boolean hasPreAuth) {
		if (hospitalType != HospitalType.NETWORK) {
            throw new CashlessNotAllowedException("Cashless claims are allowed only at network hospitals.");
        }
		
		// For PLANNED: must have pre-authorization
		if (reqDTO.getAdmissionType() == AdmissionType.PLANNED && !hasPreAuth) {
            throw new CashlessNotAllowedException("Planned admission requires pre-authorization.");
        }
		
	}
	
	// REIMBURSEMENT CLAIM VALIDATION
	public void isReimbursementAllowed(ReimbursementRequestDTO reqDTO) {
		
		// Allowed Only (EMERGENCY + REIMBURSEMENT + NON_NETWORK)
		boolean isAllowed = (reqDTO.getAdmissionType() == AdmissionType.EMERGENCY) && (reqDTO.getClaimType() == ClaimType.REIMBURSEMENT)
				&& (reqDTO.getHospitalDetails().getHospitalType() == HospitalType.NONNETWORK);
		
		if (!isAllowed) {
			throw new ReimbursementNotAllowedException("Please try to apply for Cashless.");
		}
		
	}
	
	// POLICY AND HOLDER VALIDATION
	public boolean validatePolicyAndHolder(String policyHolderId, BigDecimal amount, PolicyHolder holder) {
		
		if (holder == null) {
            throw new ResourceNotFoundException("Policy holder not found: " + policyHolderId);
        }
		
		HealthPolicy linkedPolicy = holder.getPolicy();
		if (linkedPolicy == null) {
			throw new ResourceNotFoundException("No policy linked with the policy holder.");
		}

		HealthPolicy policy = policyRepo.findById(linkedPolicy.getPolicyId()).orElseThrow(
				() -> new ResourceNotFoundException("Policy not found with ID: " + linkedPolicy.getPolicyId()));

		logger.info("Validating Policy | PolicyId={}", policy.getPolicyId());
		
		// policy status and expiry
		if (!policy.getPolicyStatus().equals(PolicyStatus.ACTIVE) || policy.getExpireDate().isBefore(LocalDate.now())) {
			throw new PolicyInactiveException("Policy is inactive or expired: " + policy.getPolicyId());
		}

		if (policy.getSumInsured().compareTo(amount) < 0) {
			throw new InsufficientCoverageException("Claimed amount exceeds the available sum insured.");
		}

		logger.info("Policy [{}] validated: Active={}, ExpiryDate={}, SumInsured={}", policy.getPolicyId(),
				policy.getPolicyStatus().equals(PolicyStatus.ACTIVE), policy.getExpireDate(), policy.getSumInsured());
		return true;
	}
	
	// DOCUMENT VALIDATION FOR ANY CLAIM
	public boolean validateDocumentReimbursement(String referenceId) {
		logger.info("validating Uploaded Documents for Referenceid ||ReferenceId={}", referenceId);
		List<DocumentResponse> documentsList = documentClient.getDocumentsByReferenceId(referenceId);

		if (documentsList == null || documentsList.isEmpty()) {
			throw new NoDocumentUploadException("No Documents uploaded for reference ID: " + referenceId);
		}

		return true;
	}

	public void saveClaimStatusHistory(PolicyClaimEntity savedClaim) {
		ClaimStatusHistory statusHistory = ClaimStatusHistory.builder().policyClaim(savedClaim)
				.submittedBy(savedClaim.getSubmittedBy()).currentStatus(savedClaim.getClaimStatus())
				.remarks(ClaimContants.remarks + savedClaim.getClaimStatus()).updatedBy(ClaimContants.role).build();

		claimStatusRepository.save(statusHistory);

		logger.info("updated ClaimStatusHistory ||claimId={} with status={}", savedClaim.getPolicyClaimId(),
				statusHistory.getCurrentStatus());
	}

}
