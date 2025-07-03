package com.claim.preauth.services;

import java.util.List;

import com.claim.preauth.dto.request.PreAuthRequestDTO;
import com.claim.preauth.dto.response.PreAuthResponseDTO;

public interface PreAuthorizationService {

//	PreAuthResponseDTO initiatePreAuthorization(MultipartFile file, PreAuthRequestDTO preAuthDto);
	
	PreAuthResponseDTO initiatePreAuthorization(PreAuthRequestDTO preAuthDto);
	
	List<PreAuthRequestDTO> getAllPreAuths();
	
	PreAuthResponseDTO getPreAuthDetailsById(String id);
	
}
