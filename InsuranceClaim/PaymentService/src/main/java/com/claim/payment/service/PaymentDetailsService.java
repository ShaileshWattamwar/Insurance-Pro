package com.claim.payment.service;

import com.claim.payment.dto.PaymentDetailsDTO;

public interface PaymentDetailsService  {
	
	PaymentDetailsDTO createPayment(PaymentDetailsDTO dto);
    PaymentDetailsDTO getPaymentById(Long id);
}
