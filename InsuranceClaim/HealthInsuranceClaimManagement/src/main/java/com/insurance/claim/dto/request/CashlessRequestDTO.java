package com.insurance.claim.dto.request;

import java.math.BigDecimal;

import com.insurance.claim.constants.AdmissionType;
import com.insurance.claim.constants.ClaimType;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CashlessRequestDTO {
	
	@NotNull(message = "Admission type is required")
	private AdmissionType admissionType;		// PLANNED or EMERGENCY
	
	@Column(name = "claimType", nullable = false)
	private ClaimType claimType;  				// CASHLESS or REIMBURSEMENT
	
	@NotBlank(message = "Patient name is required")
    private String patientName;
	
	@NotBlank(message = "Disease name is required")
    private String disease;
    private String doctorName;
    private BigDecimal expectedCost;
    
    @NotBlank(message = "Treatment type is required")
    private String treatmentType;

    // Referenced by ID: available in DB
    private Long hospitalId;		// can fetch bankdetails using hospitalId
    
    @NotNull(message = "Health Card is required")
    private String healthCard;
    	
    @NotNull(message = "Policy holder ID is required")
    private String policyHolderId;			
    
    private String preAuthId;

    private String submittedBy;
	
}
