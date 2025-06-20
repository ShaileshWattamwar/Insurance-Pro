package com.insurance.claim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.claim.dto.request.CashlessRequestDTO;
import com.insurance.claim.dto.request.ReimbursementRequestDTO;
import com.insurance.claim.dto.response.CashlessResponseDTO;
import com.insurance.claim.dto.response.ReimbursementResponseDTO;
import com.insurance.claim.services.interfaces.HealthClaimService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/claims")
@RequiredArgsConstructor
@Slf4j
public class ClaimController {

	private final HealthClaimService healthClaimService;
	private static final Logger logger = LoggerFactory.getLogger(ClaimController.class);

	@PostMapping("/cashless")
	public ResponseEntity<CashlessResponseDTO> cashlessClaim(@Valid @RequestBody CashlessRequestDTO requestDTO) {
		logger.info("Initiating CASHLESS claim || policyHolderId={}, hospitalId={}, admissionType={}, claimType={}",
				requestDTO.getPolicyHolderId(), requestDTO.getHospitalId(),
				requestDTO.getAdmissionType(), requestDTO.getClaimType());

		CashlessResponseDTO claimedResponse = healthClaimService.initiateCashlessClaim(requestDTO);

		logger.info("CASHLESS claim submitted successfully || claimId={}", claimedResponse.getClaimId());
		return ResponseEntity.status(HttpStatus.CREATED).body(claimedResponse);
	}

	@PostMapping("/reimbursement")
	public ResponseEntity<ReimbursementResponseDTO> createReimbursementClaim(
			@Valid @RequestBody ReimbursementRequestDTO requestDTO) {
		logger.info("Initiating REIMBURSEMENT claim || policyHolderId={}, hospitalType={}, admissionType={}, claimType={}",
				requestDTO.getPolicyHolderId(), 
				requestDTO.getHospitalDetails().getHospitalType(),
				requestDTO.getAdmissionType(), requestDTO.getClaimType());

		ReimbursementResponseDTO claimedResponse = healthClaimService.initiateReimbursementClaim(requestDTO);

		logger.info("REIMBURSEMENT claim submitted successfully || claimId={}", claimedResponse.getClaimId());
		return ResponseEntity.status(HttpStatus.CREATED).body(claimedResponse);
	}

}
