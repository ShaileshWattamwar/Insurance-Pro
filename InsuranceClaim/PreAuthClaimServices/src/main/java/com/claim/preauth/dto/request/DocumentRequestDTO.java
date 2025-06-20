package com.claim.preauth.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentRequestDTO {

	@NotNull(message = "Reference ID is required")
	private String referenceId;						// pre-authId
	
	@NotNull(message = "Claim type is required")
	private String claimType;						// PLANNED
	
	private String admissionType;
	
    private String fileType;						
    
}
