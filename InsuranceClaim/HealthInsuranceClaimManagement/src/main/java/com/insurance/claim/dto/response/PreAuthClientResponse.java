package com.insurance.claim.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.insurance.claim.constants.AuthStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreAuthClientResponse {
	
	private String preAuthId;
	private String policyHolderId;
	private Long hospitalId;
	private String healthCard;
	private AuthStatus authStatus;
	private BigDecimal approvedAmount;
	private String message;
	private List<DocumentResponse> uplodedDocs;
	private LocalDateTime createdAt;
	
}
