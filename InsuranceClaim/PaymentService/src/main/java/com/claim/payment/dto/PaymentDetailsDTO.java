package com.claim.payment.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.claim.payment.constant.PaymentMethod;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentDetailsDTO {

	private Long paymentDetailsId;
	private String transactionId;
	private PaymentMethod paymentMethod;
	private BigDecimal paidAmount;
	private LocalDateTime paymentDate;
	private Long BankDetailsId;
	private String policyHolderId;
	private Long hospitalId;
}
