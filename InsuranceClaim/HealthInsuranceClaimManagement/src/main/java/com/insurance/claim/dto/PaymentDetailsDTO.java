package com.insurance.claim.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PaymentDetailsDTO {
	
	private Long paymentDetailsId;
	
	@NotBlank(message = "TransactionId type is required")
	private String transactionId;
	
	@NotBlank(message = "paymentMethod type is required")
    private String paymentMethod;          	// Example: UPI, NEFT, Card
   
	@NotNull(message = "Paid Amount type is required")
	private BigDecimal paidAmount;
	
	@NotNull(message = "paymentDate type is required")
    private LocalDateTime paymentDate;
    
  
}
