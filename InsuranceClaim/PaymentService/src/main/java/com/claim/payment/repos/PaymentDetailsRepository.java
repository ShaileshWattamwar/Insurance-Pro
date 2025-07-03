package com.claim.payment.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claim.payment.entities.PaymentDetails;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Long> {

}
