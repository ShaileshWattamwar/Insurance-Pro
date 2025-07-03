package com.insurance.claim.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.insurance.claim.constants.ClaimStatus;
import com.insurance.claim.constants.ClaimType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CashlessResponseDTO{
	private String claimId;
	private String preAuthId;
    private ClaimType claimType;
    private ClaimStatus claimStatus;
    private BigDecimal approvedAmount;
    private LocalDateTime createdAt;
    private String message;
}
