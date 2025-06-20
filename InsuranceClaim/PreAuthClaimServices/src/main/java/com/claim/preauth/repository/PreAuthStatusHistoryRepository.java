package com.claim.preauth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.claim.preauth.entity.PreAuthStatusHistoryEntity;

public interface PreAuthStatusHistoryRepository extends JpaRepository<PreAuthStatusHistoryEntity, Long> {
	
	List<PreAuthStatusHistoryEntity> findByPreAuthId(String id);
}
