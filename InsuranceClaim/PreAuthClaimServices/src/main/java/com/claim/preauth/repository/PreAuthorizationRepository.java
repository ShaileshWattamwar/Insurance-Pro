package com.claim.preauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claim.preauth.entity.PreAuthorizationEntity;

@Repository
public interface PreAuthorizationRepository extends JpaRepository<PreAuthorizationEntity, String> {
	
	
}
