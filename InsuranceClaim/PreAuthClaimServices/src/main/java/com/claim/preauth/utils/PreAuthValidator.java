package com.claim.preauth.utils;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.claim.preauth.constant.AdmissionType;
import com.claim.preauth.constant.ClaimType;
import com.claim.preauth.constant.DecisionReason;
import com.claim.preauth.constant.HospitalType;
import com.claim.preauth.dto.request.PreAuthRequestDTO;
import com.claim.preauth.dto.response.DocumentResponse;
import com.claim.preauth.dto.response.HealthPolicyDTO;
import com.claim.preauth.dto.response.HospitalDTO;
import com.claim.preauth.dto.response.PolicyholderDTO;
import com.claim.preauth.exceptions.NoDocumentUploadException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PreAuthValidator {

	private static final Logger logger = LoggerFactory.getLogger(PreAuthValidator.class);

	// Validating Pre-Auth Request
	public boolean isPreAuthAllowed(HospitalDTO hospital, AdmissionType admissionType, ClaimType claimType,
			Long hospitalId) {
		logger.info("Validating Pre-Auth Request by FeignClient || admissionType={}, claimType={}, hospitalId={}",
				admissionType.toString(), claimType.toString(), hospitalId);

		boolean isNetworkHospital = false;
		if (HospitalType.NETWORK.toString().equalsIgnoreCase(hospital.getHospitalType().toString())) {
			isNetworkHospital = true;
		}
		return admissionType == AdmissionType.PLANNED && claimType == ClaimType.CASHLESS && isNetworkHospital;
	}

	// Validating Policy and PolicyHolder
	public DecisionReason validatePreAuth(PreAuthRequestDTO preAuthDto, HealthPolicyDTO policy,
			PolicyholderDTO policyholder) {
		logger.info("Validating PolicyHolder by FeignClient || policyHolderId={}", preAuthDto.getPolicyHolderId());

		// isPolicy Active
		if (LocalDate.now().isBefore(policy.getStartDate()) || LocalDate.now().isAfter(policy.getExpireDate())) {
			return DecisionReason.POLICY_INACTIVE;
		}

		// is PolicyHolder linked to Policy
		if (policyholder == null || !policyholder.getPolicy().getPolicyId().equals(preAuthDto.getPolicyId())) {
			return DecisionReason.HOLDER_MISMATCH;
		}

		// validate HealthCard
		if (!(preAuthDto.getHealthCard().equalsIgnoreCase(policyholder.getHealthCard()))) {
			return DecisionReason.HEALTHCARD_MISSMATCH;
		}

		// Expected Cost check
		if (policy.getSumInsured().compareTo(preAuthDto.getExpectedCost()) < 0) {
			return DecisionReason.INSUFFICIENT_SUM_INSURED;
		}

		logger.info("Validation Successful || PolicyHolderId={} ", preAuthDto.getPolicyHolderId());
		return DecisionReason.VALIDATION_SUCCESS;

	}

	public boolean validateDocument(List<DocumentResponse> documentList, String referenceId) {
		logger.info("validating Uploaded Documents for Referenceid ||ReferenceId={}", referenceId);
		if (documentList == null || documentList.isEmpty()) {
			throw new NoDocumentUploadException("No Documents uploaded for reference ID: " + referenceId);
		}

		return true;
	}

}
