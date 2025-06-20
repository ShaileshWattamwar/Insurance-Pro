package com.claim.payment.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claim.payment.entities.BankDetails;

public interface BankDetailsRepository  extends JpaRepository<BankDetails, Long>{
	  Optional<BankDetails> findByAccountNumber(String accountNumber);
}
