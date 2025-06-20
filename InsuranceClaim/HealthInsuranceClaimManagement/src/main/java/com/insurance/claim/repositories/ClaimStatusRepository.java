package com.insurance.claim.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.claim.entities.ClaimStatusHistory;

public interface ClaimStatusRepository extends JpaRepository<ClaimStatusHistory, Long>{
	
//	List<ClaimStatusHistory> findByPolicy_Claim_Id(String claimId);
}
