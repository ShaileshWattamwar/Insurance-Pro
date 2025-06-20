package com.insurance.claim.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.insurance.claim.dto.response.PreAuthClientResponse;

@FeignClient(value = "PREAUTH-CLAIM-SERVICES")
public interface PreAuthServiceClient  {

	@GetMapping("/v1/preauth/{preAuthId}")
	ResponseEntity<PreAuthClientResponse> getPreAuthDetailsById(@PathVariable("preAuthId") String preAuthId);
	
}
