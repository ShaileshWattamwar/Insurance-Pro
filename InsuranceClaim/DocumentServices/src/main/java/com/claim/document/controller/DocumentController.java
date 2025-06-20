package com.claim.document.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.claim.document.dto.request.DocumentDTO;
import com.claim.document.response.DocumentResponse;
import com.claim.document.services.DocumentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/documents")
@RequiredArgsConstructor
@Slf4j
public class DocumentController {

	private final DocumentService documentService;
	private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);

	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<DocumentResponse> uploadDocument(@RequestParam("file") MultipartFile file,
			@Valid @RequestPart("metaData") DocumentDTO metaData) throws IOException {
		
		logger.info("Uploading Pre-auth Document || preAuthRequestId={}", metaData.getReferenceId());
		if (file == null || file.isEmpty()) {
			throw new IllegalArgumentException("File must not be empty.");
		}
		
		DocumentResponse uploadedDocument = documentService.uploadDocument(file, metaData);
		return new ResponseEntity<DocumentResponse>(uploadedDocument, HttpStatus.CREATED);
	}

	@GetMapping("/reference/{referenceId}")
	public ResponseEntity<List<DocumentResponse>> getDocumentsByReferenceId(@PathVariable  String referenceId) {
		List<DocumentResponse> listOfDocs = documentService.getDocumentsByReferenceId(referenceId);
		return new ResponseEntity<List<DocumentResponse>>(listOfDocs, HttpStatus.OK);
	}
	
	
	@GetMapping("/download/{documentId}")
	public ResponseEntity<Resource> downloadDocument(@PathVariable String documentId) {
		Resource resource = documentService.downloadDocument(documentId);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);

	}

	@DeleteMapping("/delete/{documentId}")
	public ResponseEntity<String> deleteDocument(@PathVariable String documentId) {
		documentService.deleteDocument(documentId);
		return ResponseEntity.ok("Document deleted successfully: " + documentId);
	}

}
