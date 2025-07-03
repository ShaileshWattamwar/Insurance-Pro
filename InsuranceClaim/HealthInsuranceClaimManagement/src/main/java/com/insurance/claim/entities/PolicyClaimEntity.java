package com.insurance.claim.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.insurance.claim.constants.AdmissionType;
import com.insurance.claim.constants.ClaimStatus;
import com.insurance.claim.constants.ClaimType;

import jakarta.persistence.CascadeType;
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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "policyclaim")
public class PolicyClaimEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "PolicyClaimId")
    private String policyClaimId; 
    
    @Enumerated(EnumType.STRING)
    @Column(name = "admissionType", nullable = false)
    private AdmissionType admissionType;	// PLANNED or EMERGENCY
    
    @Enumerated(EnumType.STRING)
    @Column(name = "claimType", nullable = false)
    private ClaimType claimType;	// CASHLESS or REIMBURSEMENT
    
    @Column(name = "patientName", nullable = false)
    private String patientName;
    
    @Column(name = "disease", nullable = false)
    private String disease;
    
    @Column(name = "doctorName", nullable = true)
    private String doctorName;
    
    @Column(name = "expectedCost", nullable = true)
    private BigDecimal expectedCost;	// in case of Planned
    
    
    @Column(name = "claimedAmount", precision = 12, scale = 2, nullable = true)
    private BigDecimal claimedAmount;		// in case of Reimbursement
    
    @Column(name="approvedAmount")
    private BigDecimal approvedAmount;		// in case of Reimbursement/planned

    @Column(name = "treatmentType", nullable = false)
    private String treatmentType;			// Ex: Laparoscopic Surgery    
    
    @Column(name = "submittedBy", nullable = true)
    private String submittedBy;
    
    @Column(name = "preAuthId", nullable = true)
    private String preAuthId;			// in case of Planned

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "HospitalId", referencedColumnName = "HospitalId")
    private HospitalEntity hospital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PolicyHolderId", referencedColumnName = "PolicyHolderId")
    private PolicyHolder policyHolder;
    
    @Column(name = "BankDetailsId")
    private Long bankDetailsId;			// In case of Reimbursement
    
    @Column(name = "PaymentDetailsId")
    private Long paymentDetailsId;		// In case of Reimbursement
    
    @Column(name = "createdAt")
    private LocalDateTime createdAt;
    
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }    
    
    @Enumerated(EnumType.STRING)
    @Column(name = "claim_status")
    private ClaimStatus claimStatus = ClaimStatus.INITIATED;		// UNDER_REVIEW or APPROVED or REJECTED or SETTLED
	
}
