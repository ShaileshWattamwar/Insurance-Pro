package com.insurance.claim.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor

public class HealthPolicyDTO {
	
	private String policyId;
	private String policyCode;
    private String policyType;
    private String coverageDetails;
    private BigDecimal premiumAmount;
    private BigDecimal sumInsured;
    private LocalDate startDate;
    private LocalDate expireDate;
    private String policyStatus;
}
