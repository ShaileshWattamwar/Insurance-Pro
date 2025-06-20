package com.claim.preauth.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.claim.preauth.dto.response.HealthPolicyDTO;
import com.claim.preauth.dto.response.HospitalDTO;
import com.claim.preauth.dto.response.PolicyholderDTO;

@FeignClient(name = "HEALTH-INSURANCE-CLAIM-MANAGEMENT")	// fallback="InsuranceClaimClientFallback"
public interface InsuranceClaimClient  {
	
	@GetMapping("/api/policies/{policyId}")
    HealthPolicyDTO getPolicy(@PathVariable("policyId") String policyId);

    @GetMapping("/api/policy-holders/{policyHolderId}")
    PolicyholderDTO getPolicyholder(@PathVariable("policyHolderId") String policyHolderId);

    @GetMapping("/api/hospitals/{hospitalId}")
    HospitalDTO getHospital(@PathVariable("hospitalId") Long hospitalId);
}
