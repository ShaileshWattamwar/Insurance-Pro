package com.claim.payment.service;

import com.claim.payment.dto.BankDetailsDTO;

public interface BankDetailsService {
	BankDetailsDTO createBankDetails(BankDetailsDTO dto);
    BankDetailsDTO getBankDetailsById(Long id);
}
