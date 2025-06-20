package com.claim.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claim.payment.dto.BankDetailsDTO;
import com.claim.payment.service.BankDetailsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/payments")
@RequiredArgsConstructor
public class BankDetailsController {
	
	private final BankDetailsService bankDetailsService;

	@PostMapping("/")
	public ResponseEntity<BankDetailsDTO> createBank(@RequestBody BankDetailsDTO dto) {
		return ResponseEntity.ok(bankDetailsService.createBankDetails(dto));
	}

	@GetMapping("/bank/{id}")
	public ResponseEntity<BankDetailsDTO> getBankDetailsById(@PathVariable Long id) {
		return ResponseEntity.ok(bankDetailsService.getBankDetailsById(id));
	}
}
