package com.insurance.claim.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.insurance.claim.constants.ClaimStatus;
import com.insurance.claim.constants.ClaimType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ReimbursementResponseDTO {

	private String claimId;
    private ClaimType claimType;
    private ClaimStatus claimStatus;
    private BigDecimal approvedAmount;
    private LocalDateTime createdAt;
    private BankDetailsResponseDTO bankDetails;
    private String message;
}

