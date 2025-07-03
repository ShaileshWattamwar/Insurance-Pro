package com.insurance.claim.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class DocumentResponse {
	
	private String id;
	private String referenceId;						// pre-authId
	private String fileName;
	private String claimType;						// CASHLESS 
	private String admissionType;					// PLANNED or EMERGENCY						
    private LocalDateTime uploadedAt;
}
