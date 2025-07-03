package com.insurance.claim.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.claim.entities.PolicyClaimEntity;

public interface PolicyClaimRepository extends JpaRepository<PolicyClaimEntity, String> {

}
