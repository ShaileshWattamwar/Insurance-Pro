package com.insurance.claim.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.insurance.claim.constants.PolicyStatus;
import com.insurance.claim.constants.PolicyType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "healthpolicy")
public class HealthPolicy{
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "PolicyId")
	private String policyId;
	
	@Column(name = "PolicyCode", nullable = false)
	private String policyCode= "HLT7483";	// HM0292, HLT7483, AUTO203, etc
		
	@Enumerated(EnumType.STRING)
	@Column(name = "PolicyType", nullable = false)
	private PolicyType policyType;						// INDIVIDUAL, FAMILY_FLOATER, GROUP, SENIOR_CITIZEN
	
	@Column(name = "CoverageDetails")
	private String coverageDetails;
	
	@Column(name = "PremiumAmount")
	private BigDecimal premiumAmount;
	
	@Column(name = "SumInsured")
	private BigDecimal sumInsured;
	
	@Column(name = "StartDate")
	private LocalDate startDate;
	
	@Column(name = "ExpireDate")
	private LocalDate expireDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PolicyStatus", nullable = false)
	private PolicyStatus policyStatus;					// ACTIVE, EXPIRED, SUSPENDED, CANCELLED
	
	@OneToMany(mappedBy = "policy", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<PolicyHolder> policyHolders = new ArrayList<>();
	
}
