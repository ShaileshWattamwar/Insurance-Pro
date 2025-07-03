package com.insurance.claim.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.insurance.claim.constants.PolicyStatus;
import com.insurance.claim.dto.HealthPolicyDTO;
import com.insurance.claim.entities.HealthPolicy;
import com.insurance.claim.exceptions.ResourceNotFoundException;
import com.insurance.claim.repositories.HealthPolicyRepository;
import com.insurance.claim.services.interfaces.PolicyService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PolicyServiceImpl implements PolicyService {

	private final HealthPolicyRepository policyRepo;
	private final ModelMapper modelMapper;

	@Override
	public HealthPolicyDTO getPolicyById(String policyId) {
		HealthPolicy healthPolicy = policyRepo.findById(policyId)
				.orElseThrow(() -> new ResourceNotFoundException("Health Policy Not Found With ID::" + policyId));
		return modelMapper.map(healthPolicy, HealthPolicyDTO.class);
	}

	@Override
	public List<HealthPolicyDTO> getAllPolicies() {
		List<HealthPolicy> allPolicies = policyRepo.findAll();
		return allPolicies.stream().map(policy -> modelMapper.map(policy, HealthPolicyDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<HealthPolicyDTO> getPoliciesByStatus(String policyStatus) {
		List<HealthPolicy> policyListByStatus = policyRepo.findByPolicyStatus(PolicyStatus.valueOf(policyStatus.toUpperCase()));
		return policyListByStatus.stream().map(policy -> modelMapper.map(policy, HealthPolicyDTO.class))
				.collect(Collectors.toList());
	}

}
