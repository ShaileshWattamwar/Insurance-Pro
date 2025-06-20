package com.claim.preauth.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.claim.preauth.dto.request.DocumentRequestDTO;
import com.claim.preauth.dto.response.DocumentResponse;
import com.claim.preauth.entity.PreAuthorizationEntity;
import com.claim.preauth.feignclient.DocumentServiceClient;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PreAuthDocumentHandler {

	private final DocumentServiceClient documentClient;
	private static final Logger logger = LoggerFactory.getLogger(PreAuthDocumentHandler.class);

	public void upload(MultipartFile file, PreAuthorizationEntity preAuth) {
		logger.info("Uploading Pre-auth Document || preAuthRequestId={}", preAuth.getPreAuthRequestId());

		DocumentRequestDTO docRequest = buildDocRequestDTO(preAuth, file);
		documentClient.uploadDocument(file, docRequest);
	}

	public List<DocumentResponse> fetchDocuments(String referenceId) {
		return documentClient.getDocumentsByReferenceId(referenceId);
	}

	private DocumentRequestDTO buildDocRequestDTO(PreAuthorizationEntity savedPreAuth, MultipartFile file) {
		return DocumentRequestDTO.builder().referenceId(savedPreAuth.getPreAuthRequestId())
				.claimType(savedPreAuth.getClaimType().toString())
				.admissionType(savedPreAuth.getAdmissionType().toString()).fileType(file.getContentType()).build();
	}

}
