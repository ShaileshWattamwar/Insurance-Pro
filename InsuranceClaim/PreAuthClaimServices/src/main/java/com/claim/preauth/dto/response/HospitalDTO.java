package com.claim.preauth.dto.response;

import com.claim.preauth.constant.HospitalType;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class HospitalDTO {
	private Long hospitalId;
	private String hospitalName;
    private HospitalType hospitalType;
    private String contact;
    private String email;
    private String street;
    private String city;
    private String state;
    private String pincode;
}
