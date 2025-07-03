package com.claim.preauth.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.claim.preauth.constant.AdmissionType;
import com.claim.preauth.constant.ClaimType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PreAuthRequestDTO {
	
	private String policyId;
	private String policyHolderId;
	private Long hospitalId;
	private String healthCard;
	private ClaimType claimType;			// CASHLESS
	private AdmissionType admissionType;	// PLANNED or EMERGENCY
	private LocalDate admissionDate;  		// JSON DateFormat : "2025-07-06"
	private String patientName;
	private String relation;				// Relation to PolicyHolder
	private String disease;
	private String treatmentType;
	private String doctorName;
	private BigDecimal expectedCost;	
			
}
