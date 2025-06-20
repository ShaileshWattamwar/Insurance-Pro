package com.insurance.claim.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "policyholder")
public class PolicyHolder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "PolicyHolderId")
	private String policyHolderId;
	
	@Column(name = "HealthCard", nullable = false, unique = true)
	private String healthCard;
	
	@Column(name = "FirstName")
	private String firstName;
	
	@Column(name = "LastName")
	private String lastName;
	
	@Column(name = "Email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "Password", nullable = false)
	private String password;
	
	@Column(name = "Aadhaar", nullable = false)
	private String aadhaar;
	
	@Column(name = "ContactNo")
	private String contactNo;
	
	@Column(name = "DoB")
	private LocalDate dob;
	
	@Column(name = "Gender")
	private String gender;
	
	@Column(name = "Address")
	private String address;
	
	@OneToMany(mappedBy = "policyHolder", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<PolicyClaimEntity> claims = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PolicyId", referencedColumnName = "policyId")
	private HealthPolicy policy;
}
