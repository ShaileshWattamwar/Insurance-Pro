package com.claim.document.services;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.claim.document.dto.request.DocumentDTO;
import com.claim.document.response.DocumentResponse;

public interface DocumentService {
	
	DocumentResponse uploadDocument(MultipartFile file, DocumentDTO request) throws IOException;
    List<DocumentResponse> getDocumentsByReferenceId(String referenceId);
    Resource downloadDocument(String documentId);
    void deleteDocument(String documentId);
}
