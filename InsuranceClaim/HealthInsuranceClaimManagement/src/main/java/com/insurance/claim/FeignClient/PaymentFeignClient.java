package com.insurance.claim.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.insurance.claim.dto.PaymentDetailsDTO;
import com.insurance.claim.dto.response.BankDetailsResponseDTO;

@FeignClient(value = "PAYMENT-SERVICE", path = "/v1/payments")
public interface PaymentFeignClient {
	
	@PostMapping("/save")
	PaymentDetailsDTO savePaymentDetails(@RequestBody PaymentDetailsDTO paymentDTO);
	
	@GetMapping("/bank/{id}")
	BankDetailsResponseDTO getBankDetailsById(@PathVariable Long id);
}
