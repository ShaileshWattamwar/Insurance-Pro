package com.insurance.claim.services.interfaces;

import java.util.List;

import com.insurance.claim.dto.HospitalDTO;

public interface HospitalService {
	
	HospitalDTO getHospitalById(Long id);
	
	List<HospitalDTO> getAllHospitals();
}
