package com.insurance.claim.entities;

import java.util.ArrayList;
import java.util.List;

import com.insurance.claim.constants.HospitalType;

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
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hospital")
public class HospitalEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long hospitalId;
	
	private String hospitalName;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "hospitalType")
	private HospitalType hospitalType;		
	
	private String contact;
	private String street;
	private String city;
	private String state;
	private String pincode;
	private String email;
	
	@OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PolicyClaimEntity> claims = new ArrayList<>();
	
}
