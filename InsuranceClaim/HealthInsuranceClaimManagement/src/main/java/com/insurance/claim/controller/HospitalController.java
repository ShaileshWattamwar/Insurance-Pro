package com.insurance.claim.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.claim.dto.HospitalDTO;
import com.insurance.claim.services.interfaces.HospitalService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/hospitals")
@RequiredArgsConstructor
@Slf4j
public class HospitalController {

	private final HospitalService hospitalService;
	
	@GetMapping("/{id}")
	public ResponseEntity<HospitalDTO> getHospitalById(@PathVariable long id){
		HospitalDTO hospitalById = hospitalService.getHospitalById(id);
		return ResponseEntity.status(HttpStatus.OK).body(hospitalById);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<HospitalDTO>> getAllHospitals(){
		List<HospitalDTO> allHospitals = hospitalService.getAllHospitals();
		return new ResponseEntity<List<HospitalDTO>>(allHospitals, HttpStatus.OK);
	}
	
}
