package com.claim.preauth.dto.response;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PolicyholderDTO {
	private String policyHolderId;
	private String healthCard;
    private String firstName;
    private String lastName;
    private String emailId;
    private String password;
    private String aadhaar;
    private String contactNo;
    private LocalDate dob;
    private String gender;
    private String address;
    private HealthPolicyDTO policy;
}
