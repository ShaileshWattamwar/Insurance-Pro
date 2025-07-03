package com.insurance.claim.entities;

import java.time.LocalDateTime;

import com.insurance.claim.constants.ClaimStatus;

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
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "claim_status_history")
public class ClaimStatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_claim_id")
    private PolicyClaimEntity policyClaim; 
    
    @Column(name = "submittedBy", nullable = false)
    private String submittedBy;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "current_status")
    private ClaimStatus currentStatus;
    private String remarks;
    private String updatedBy;
    private LocalDateTime updatedAt;
    
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }   
}

