package com.claim.preauth.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PreAuthValidationRequest {
	private Long policyId;
	private Long policyHolderId;
	private Long hospitalId;
}
