package com.insurance.claim.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.claim.dto.HealthPolicyDTO;
import com.insurance.claim.services.interfaces.PolicyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/policies")
@RequiredArgsConstructor
@Slf4j
public class PolicyController {

	private final PolicyService policyService;
	
	@GetMapping("/{id}")
	public ResponseEntity<HealthPolicyDTO> getPolicyById(@PathVariable String id) {
		HealthPolicyDTO policyById = policyService.getPolicyById(id);
		return ResponseEntity.ok(policyById);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<HealthPolicyDTO>> getAllPolicies(){
		List<HealthPolicyDTO> allPolicies = policyService.getAllPolicies();
		return ResponseEntity.status(HttpStatus.OK).body(allPolicies);
	}
	
	@GetMapping
	public ResponseEntity<List<HealthPolicyDTO>> getPoliciesByStatus(@RequestParam String status){
		List<HealthPolicyDTO> policiesByStatus = policyService.getPoliciesByStatus(status);
		return new ResponseEntity<List<HealthPolicyDTO>>(policiesByStatus, HttpStatus.OK);
	}
	
}
