package com.claim.preauth.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.claim.preauth.constant.AuthStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PreAuthResponseDTO{
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
