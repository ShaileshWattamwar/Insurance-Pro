package com.insurance.claim.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.insurance.claim.dto.HospitalDTO;
import com.insurance.claim.entities.HospitalEntity;
import com.insurance.claim.exceptions.ResourceNotFoundException;
import com.insurance.claim.repositories.HospitalRepository;
import com.insurance.claim.services.interfaces.HospitalService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {

	private final HospitalRepository hospitalRepo;
	private final ModelMapper modelMapper;

	@Override
	public HospitalDTO getHospitalById(Long id) {
		HospitalEntity hospitalById = hospitalRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Hospital Not Found With ID::" + id));
		return modelMapper.map(hospitalById, HospitalDTO.class);
	}

	@Override
	public List<HospitalDTO> getAllHospitals() {
		List<HospitalEntity> hospitalList = hospitalRepo.findAll();
		return hospitalList.stream().map(entity -> modelMapper.map(entity, HospitalDTO.class))
				.collect(Collectors.toList());
	}

}
