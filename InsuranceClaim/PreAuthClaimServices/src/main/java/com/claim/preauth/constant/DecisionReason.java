package com.claim.preauth.constant;

public enum DecisionReason {
	
	POLICY_INVALID("Policy not found"),
    POLICY_INACTIVE("Policy is not active"),
    INSUFFICIENT_SUM_INSURED("Insufficient sum insured"),
    HOLDER_MISMATCH("Policy holder not linked to policy"),
    HOSPITAL_NOT_EMPANELED("Hospital is not empaneled"),
    DISEASE_NOT_COVERED("Disease not covered by policy"),
    HEALTHCARD_MISSMATCH("PolicyHolder's HealthCard missmatch"),
    DOCUMENTS_MISSING("Required documents missing or invalid"),
    VALIDATION_SUCCESS("All validations passed");

    private final String message;

    DecisionReason(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
