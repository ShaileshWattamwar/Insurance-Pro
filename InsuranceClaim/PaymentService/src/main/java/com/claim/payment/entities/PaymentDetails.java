package com.claim.payment.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.claim.payment.constant.PaymentMethod;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment_details")
public class PaymentDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_details_id")
	private Long paymentDetailsId;

	@Column(name = "transaction_id", nullable = false, unique = true)
	private String transactionId;

	@Enumerated(EnumType.STRING)
	@Column(name = "payment_method", nullable = false)
	private PaymentMethod paymentMethod; // Ex: UPI, Card, NEFT

	@Column(name = "paid_amount", precision = 12, scale = 2, nullable = false)
	private BigDecimal paidAmount;

	@Column(name = "payment_date", nullable = false)
	private LocalDateTime paymentDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bankDetailsId")
	private BankDetails bankDetails;

	@Column(name = "policy_holder_id", nullable = false)
	private String policyHolderId;

	@Column(name = "hospital_id", nullable = false)
	private Long hospitalId;

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	@PrePersist
	public void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = this.createdAt;
	}

	@PreUpdate
	public void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
}
