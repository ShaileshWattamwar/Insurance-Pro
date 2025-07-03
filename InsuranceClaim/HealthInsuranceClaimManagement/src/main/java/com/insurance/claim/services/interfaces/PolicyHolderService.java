package com.insurance.claim.services.interfaces;

import java.util.List;

import com.insurance.claim.dto.PolicyHolderDTO;

public interface PolicyHolderService {
	
	PolicyHolderDTO getPolicyHolderById(String id); 
	
	List<PolicyHolderDTO> getAllPolicyHolders();
}
