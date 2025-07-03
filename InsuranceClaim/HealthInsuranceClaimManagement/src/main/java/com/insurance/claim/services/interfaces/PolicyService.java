package com.insurance.claim.services.interfaces;

import java.util.List;

import com.insurance.claim.dto.HealthPolicyDTO;

public interface PolicyService {
	
	HealthPolicyDTO getPolicyById(String policyId);
	
	List<HealthPolicyDTO> getAllPolicies();
	
	// to get active policies
	List<HealthPolicyDTO> getPoliciesByStatus(String policyStatus);
}
