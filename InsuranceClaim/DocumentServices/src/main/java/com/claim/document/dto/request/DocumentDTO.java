package com.claim.document.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {
	
	@NotNull(message = "Reference ID is required")
	private String referenceId;
	
	@NotNull(message = "Claim type is required")	
	private String claimType;			// CASHLESS

	private String admissionType;		// PLANNED or EMERGENCY
	
    private String fileType;
	
//	@NotNull(message = "Uploaded by is required")
//    private String uploadedBy;			// PolicyHolderId or HospitalId
	

}
