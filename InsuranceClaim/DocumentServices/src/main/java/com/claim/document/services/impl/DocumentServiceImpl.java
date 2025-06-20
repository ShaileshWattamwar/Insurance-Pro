package com.claim.document.services.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.claim.document.dto.request.DocumentDTO;
import com.claim.document.entities.DocumentMetadata;
import com.claim.document.repository.DocumentMetadataRepository;
import com.claim.document.response.DocumentResponse;
import com.claim.document.services.DocumentService;
import com.claim.document.utilities.AWSs3Util;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
	
	@Autowired
	private final AWSs3Util s3Util;
	private final DocumentMetadataRepository documentMetadataRepo;
	private final ModelMapper modelMapper;

	@Override
	public DocumentResponse uploadDocument(MultipartFile file, DocumentDTO docDTO) throws IOException {

//		Uploaded ON AWS S3 file URL
		String s3Url = s3Util.uploadFile(file);

//		DTO to Entity
		DocumentMetadata metadata = DocumentMetadata.builder().fileName(file.getOriginalFilename())
				.fileType(file.getContentType()).size(file.getSize()).s3Url(s3Url).claimType(docDTO.getClaimType())
				.referenceId(docDTO.getReferenceId())
				.admissionType(docDTO.getAdmissionType())
				.uploadedAt(LocalDateTime.now()).build();

//		Save Document Info to MongoDB Database
		DocumentMetadata savedMetadata = documentMetadataRepo.save(metadata);

//		Return converted Entity to Response
		return DocumentResponse.builder()
				.id(savedMetadata.getId())
				.referenceId(savedMetadata.getReferenceId())
				.fileName(savedMetadata.getFileName())
				.claimType(savedMetadata.getClaimType())
				.admissionType(savedMetadata.getAdmissionType())
				.s3Url(savedMetadata.getS3Url())
				.build();
	}

	@Override
	public List<DocumentResponse> getDocumentsByReferenceId(String referenceId) {
		List<DocumentMetadata> listOfUploadedDocument = documentMetadataRepo.findByReferenceId(referenceId);
		return listOfUploadedDocument.stream().map(entity -> modelMapper.map(entity, DocumentResponse.class))
											  .collect(Collectors.toList());
	}

	@Override
	public Resource downloadDocument(String documentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteDocument(String documentId) {
		// TODO Auto-generated method stub
		
	}

}
