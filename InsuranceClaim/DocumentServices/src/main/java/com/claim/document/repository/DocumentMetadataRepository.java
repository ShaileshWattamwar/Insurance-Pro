package com.claim.document.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.claim.document.entities.DocumentMetadata;

public interface DocumentMetadataRepository extends MongoRepository<DocumentMetadata, String> {
	
	List<DocumentMetadata> findByReferenceIdAndClaimType(String referenceId, String claimType);
	
	// Can Add FindByReferenceId
	
	List<DocumentMetadata> findByReferenceId(String referenceId);
	
	// Can Add FindByClaimType
	List<DocumentMetadata> findByClaimType(String claimType);
}
