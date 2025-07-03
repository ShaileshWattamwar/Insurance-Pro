package com.insurance.claim.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.claim.dto.PolicyHolderDTO;
import com.insurance.claim.services.interfaces.PolicyHolderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/policy-holders")
@RequiredArgsConstructor
@Slf4j
public class PolicyHolderController {

	private final PolicyHolderService policyHolderService;
	
	@GetMapping("/{id}")
	public ResponseEntity<PolicyHolderDTO> getPolicyHolderById(@PathVariable String id){
		PolicyHolderDTO policyHolderById = policyHolderService.getPolicyHolderById(id);
		return new ResponseEntity<PolicyHolderDTO>(policyHolderById, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<PolicyHolderDTO>> getAllPolicyHolders(){
		List<PolicyHolderDTO> allPolicyHolders = policyHolderService.getAllPolicyHolders();
		return new ResponseEntity<List<PolicyHolderDTO>>(allPolicyHolders, HttpStatus.OK);
	}
	
	
}
