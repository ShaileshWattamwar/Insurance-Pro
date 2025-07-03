package com.claim.preauth.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
