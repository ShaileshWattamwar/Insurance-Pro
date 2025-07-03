package com.insurance.claim.dto.request;

import java.math.BigDecimal;

import com.insurance.claim.constants.AdmissionType;
import com.insurance.claim.constants.ClaimType;
import com.insurance.claim.dto.HospitalDTO;
import com.insurance.claim.dto.PaymentDetailsDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ReimbursementRequestDTO {
	
	@NotNull(message = "Admission type is required")
	private AdmissionType admissionType;	// planned or emergency
	
	@Column(name = "claimType", nullable = false)
	private ClaimType claimType;  				// REIMBURSEMENT
	
	@NotBlank(message = "Patient name is required")
    private String patientName;
	
	@NotBlank(message = "Disease name is required")
    private String disease;
    private String doctorName;
    private BigDecimal claimedAmount;     // Required
    
    @NotBlank(message = "Treatment type is required")
    private String treatmentType;
    
    private String submittedBy;

    @NotNull(message = "Hospital ID is required")
    private HospitalDTO hospitalDetails;
    
    @NotNull(message = "Payment Details is required")
    private PaymentDetailsDTO paymentDetails;        // Required
    
    @NotNull(message = "Policy holder ID is required")
    private String policyHolderId;					// can fetch bankdetails 
    
    @NotNull(message = "BankDetailsId is required")
    private Long bankDetailsId;           
    
}
