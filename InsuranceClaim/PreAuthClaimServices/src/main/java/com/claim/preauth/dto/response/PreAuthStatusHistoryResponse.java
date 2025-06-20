package com.claim.preauth.dto.response;

import java.time.LocalDateTime;
import com.claim.preauth.constant.AuthStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class PreAuthStatusHistoryResponse {
	
	private Long id;
    private String preAuthId;
    private AuthStatus currentStatus;		// INITIATED, APPROVED, REJECTED etc.
    private String remarks;
    private LocalDateTime updatedAt;
}
