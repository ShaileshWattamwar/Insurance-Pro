package com.claim.preauth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DocumentResponse {
	
	private String id;
	private String referenceId;						// pre-authId
	private String fileName;
	private String claimType;						// CASHLESS 
	private String admissionType;					// PLANNED or EMERGENCY						
   
}
