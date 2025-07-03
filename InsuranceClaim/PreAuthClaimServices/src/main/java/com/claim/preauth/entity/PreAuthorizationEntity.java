package com.claim.preauth.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.claim.preauth.constant.AdmissionType;
import com.claim.preauth.constant.AuthStatus;
import com.claim.preauth.constant.ClaimType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "preauthorization")
public class PreAuthorizationEntity {
	
	@Id
	@Column(name = "PreAuthRequestId", columnDefinition = "CHAR(36)")
	@GeneratedValue(strategy = GenerationType.UUID)
	private String preAuthRequestId;
	
	@Column(name = "policyId", nullable = false)
	private String policyId;
	
	@Column(name = "policyHolderId", nullable = false)
	private String policyHolderId;
	
	@Column(name = "hospitalId", nullable = false)
	private Long hospitalId;
	
	@Column(name = "healthCard", nullable = false)
	private String healthCard;
	
	@Enumerated(EnumType.STRING)
	private ClaimType claimType;		// CASHLESS 

	@Enumerated(EnumType.STRING)
	private AdmissionType admissionType; // PLANNED or EMERGENCY
	
	@Column(name = "admissionDate")
	private LocalDate admissionDate;
	
	@Column(name = "patientName", nullable = false)
	private String patientName;
	
	@Column(name = "relation", nullable = false)
	private String relation;
	
	@Column(name = "disease",nullable = false)
	private String disease;
	
	@Column(name = "doctor_name")
	private String doctorName;
	
	@Column(name = "treatmentType", nullable = false)
	private String treatmentType;
	
	@Column(name = "expectedCost", precision = 12, scale = 2)
	private BigDecimal expectedCost;
	
	@Column(name = "approved_Amount", precision = 12, scale = 2)
	private BigDecimal approvedAmount;
	
	@Column(name = "createdAt", updatable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "updatedAt")
	private LocalDateTime updatedAt;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "authStatus", length = 50)
	private AuthStatus authStatus = AuthStatus.INITIATED;		// INITIATED, APPROVED, REJECTED, etc.
	
	@PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
