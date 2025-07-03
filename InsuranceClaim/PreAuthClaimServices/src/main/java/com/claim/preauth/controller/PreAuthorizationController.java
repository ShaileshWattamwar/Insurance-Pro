package com.claim.preauth.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claim.preauth.dto.request.PreAuthRequestDTO;
import com.claim.preauth.dto.response.PreAuthResponseDTO;
import com.claim.preauth.services.impl.PreAuthorizationServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/preauth")
@RequiredArgsConstructor
@Slf4j
public class PreAuthorizationController {

	private final PreAuthorizationServiceImpl preAuthService;
	private static final Logger logger = LoggerFactory.getLogger(PreAuthorizationController.class);
	
	
	@PostMapping("/submit")
	public ResponseEntity<PreAuthResponseDTO> submitPreAuthWithDocument(@RequestBody PreAuthRequestDTO preAuthDto) {
		
		logger.info("Initialized Pre-Authorization request || preAuthDto={}", preAuthDto);
		
		PreAuthResponseDTO preAuthorizationResponse = preAuthService.initiatePreAuthorization(preAuthDto);
		
		logger.info("Response from PreAuthorizationServiceImpl || preAuthorizationResponse={}", preAuthorizationResponse);
		return new ResponseEntity<PreAuthResponseDTO>(preAuthorizationResponse, HttpStatus.CREATED);
	}
	
	
	
//	@PostMapping(value = "/submit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//	public ResponseEntity<?> submitPreAuthWithDocument(@RequestPart("file") MultipartFile file,
//			@RequestPart("preAuthDto") PreAuthRequestDTO preAuthDto) {
//		
//		logger.info("Initialized Pre-Authorization request || preAuthDto={}", preAuthDto);
//		
//		PreAuthResponseDTO preAuthorizationResponse = preAuthService.initiatePreAuthorization(file, preAuthDto);
//		
//		logger.info("Response from PreAuthorizationServiceImpl || preAuthorizationResponse={}", preAuthorizationResponse);
//		return new ResponseEntity<PreAuthResponseDTO>(preAuthorizationResponse, HttpStatus.CREATED);
//	}

	@GetMapping("/{preAuthId}")
	public ResponseEntity<PreAuthResponseDTO> getPreAuthDetailsById(@PathVariable("preAuthId") String preAuthId) {
		PreAuthResponseDTO preAuthResponse = preAuthService.getPreAuthDetailsById(preAuthId);
		return new ResponseEntity<PreAuthResponseDTO>(preAuthResponse, HttpStatus.OK);
	}


	@GetMapping("/")
	public ResponseEntity<?> getAllPreAuths() {
		return new ResponseEntity<List<PreAuthRequestDTO>>(preAuthService.getAllPreAuths(), HttpStatus.OK);
	}



}
