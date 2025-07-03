package com.insurance.claim.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.claim.constants.PolicyStatus;
import com.insurance.claim.entities.HealthPolicy;

public interface HealthPolicyRepository extends JpaRepository<HealthPolicy, String>{

	List<HealthPolicy> findByPolicyStatus(PolicyStatus policyStatus);
	
}
