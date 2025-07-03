package com.claim.document.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentResponse {

	private String id;
	private String referenceId;		// preAuthId or claimId
	private String fileName;
	private String claimType; 		// CASHLESS or Reimbursement
	private String admissionType;
	private String s3Url;
}
