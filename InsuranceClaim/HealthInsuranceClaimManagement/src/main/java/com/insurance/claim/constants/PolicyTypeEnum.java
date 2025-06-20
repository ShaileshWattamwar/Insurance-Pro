package com.insurance.claim.constants;

import lombok.Getter;

public enum PolicyTypeEnum {
	
	HOME("HM0292", "HOME"),
    HEALTH("HLT7483", "HEALTH"),
    AUTO("AUTO203", "AUTO"),
    LIFE("LIFE001", "LIFE"),
    PET("PET102", "PET");
	
	@Getter
    private final String code;
	@Getter
    private final String label;

    PolicyTypeEnum(String code, String label) {
        this.code = code;
        this.label = label;
    }
    
    // Helper to get label by code
    public static PolicyTypeEnum getLabelByCode(String code) {
        for (PolicyTypeEnum type : values()) {
            if (type.code.equalsIgnoreCase(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown policy code: " + code);
    }	
}
