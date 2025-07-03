package com.claim.payment.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.claim.payment.constant.BankOwnerType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "bank_details")
public class BankDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bankDetailsId")
	private Long bankDetailsId;

	@Column(name = "accountHolderName", nullable = false)
	private String accountHolderName;

	@Column(name = "accountNumber", nullable = false, unique = true)
	private String accountNumber;

	@Column(name = "ifscCode", nullable = false)
	private String ifscCode;

	@Column(name = "bankName", nullable = false)
	private String bankName;

	@Column(name = "linkedEntityId", nullable = false)
	private String linkedId; 		// hospitalId/policyholderId

	@Enumerated(EnumType.STRING)
	@Column(name = "ownerType", nullable = false)
	private BankOwnerType ownerType;

	@Column(name = "createdAt", nullable = false)
	private LocalDateTime createdAt;

	@Column(name = "updatedAt", nullable = false)
	private LocalDateTime updatedAt;
	
	@JsonIgnore
	@OneToMany(mappedBy = "bankDetails", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PaymentDetails> paymentDetailsList = new ArrayList<>();

	
	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = this.createdAt;
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
}
