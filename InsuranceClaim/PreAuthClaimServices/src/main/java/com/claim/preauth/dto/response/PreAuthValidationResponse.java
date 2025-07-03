package com.claim.preauth.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PreAuthValidationResponse {
    private boolean policyValid;
    private boolean policyHolderValid;
    private boolean hospitalValid;
}
