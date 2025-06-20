package com.claim.payment.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.claim.payment.dto.PaymentDetailsDTO;
import com.claim.payment.entities.BankDetails;
import com.claim.payment.entities.PaymentDetails;
import com.claim.payment.repos.BankDetailsRepository;
import com.claim.payment.repos.PaymentDetailsRepository;
import com.claim.payment.service.PaymentDetailsService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PaymentDetailsServiceImpl implements PaymentDetailsService {

	private final PaymentDetailsRepository paymentRepo;
	private final BankDetailsRepository bankRepo;
	private final ModelMapper modelMapper;

	@Override
	public PaymentDetailsDTO createPayment(PaymentDetailsDTO dto) {
		PaymentDetails payment = modelMapper.map(dto, PaymentDetails.class);

		// Set associated bank details
		BankDetails bankDetails = bankRepo.findById(dto.getBankDetailsId())
				.orElseThrow(() -> new RuntimeException("BankDetails not found"));
		payment.setBankDetails(bankDetails);

		return modelMapper.map(paymentRepo.save(payment), PaymentDetailsDTO.class);
	}

	@Override
	public PaymentDetailsDTO getPaymentById(Long id) {
		PaymentDetails payment = paymentRepo.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
		return modelMapper.map(payment, PaymentDetailsDTO.class);
	}

}
