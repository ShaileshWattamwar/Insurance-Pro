package com.insurance.claim.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PolicyHolderDTO {
	
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
