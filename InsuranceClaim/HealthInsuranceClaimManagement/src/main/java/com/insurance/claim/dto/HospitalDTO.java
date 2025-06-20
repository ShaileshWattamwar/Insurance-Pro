package com.insurance.claim.dto;

import com.insurance.claim.constants.HospitalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class HospitalDTO {
	
	private Long hospitalId;
	private String hospitalName;
    private HospitalType hospitalType;
    private String contact;
    private String street;
    private String city;
    private String state;
    private String pincode;
}
