package com.claim.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claim.payment.dto.PaymentDetailsDTO;
import com.claim.payment.service.PaymentDetailsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
	
	private final PaymentDetailsService paymentService;

	@PostMapping("/")
	public ResponseEntity<PaymentDetailsDTO> createPayment(@RequestBody PaymentDetailsDTO dto) {
		return ResponseEntity.ok(paymentService.createPayment(dto));
	}

	@PostMapping("/save")
	public ResponseEntity<PaymentDetailsDTO> savePaymentDetails(@RequestBody PaymentDetailsDTO dto) {
		return ResponseEntity.ok(paymentService.createPayment(dto));
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<PaymentDetailsDTO> getPaymentById(@PathVariable Long id) {
		return ResponseEntity.ok(paymentService.getPaymentById(id));
	}
}
