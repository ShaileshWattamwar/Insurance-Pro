package com.insurance.claim.FeignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.insurance.claim.config.AppConfig;
import com.insurance.claim.dto.request.DocumentRequestDTO;
import com.insurance.claim.dto.response.DocumentResponse;

@FeignClient(name = "DOCUMENT-SERVICE", configuration = AppConfig.class )
public interface DocumentServiceClient {

	@PostMapping(value = "/v1/documents/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	DocumentResponse uploadDocument(@RequestPart("file") MultipartFile file,
			@RequestPart("docDto") DocumentRequestDTO docDto);
	
	@GetMapping(value = "/v1/documents/list")
	List<DocumentResponse> getDocumentsByReferenceId(@RequestParam String referenceId);
}


