package com.claim.preauth.entity;

import java.time.LocalDateTime;

import com.claim.preauth.constant.AuthStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "PreAuthStatusHistory")
public class PreAuthStatusHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String preAuthId;
    
    @Enumerated(EnumType.STRING)
    private AuthStatus currentStatus;		// INITIATED, APPROVED, REJECTED etc.
    private String remarks;
    private LocalDateTime updatedAt;
//    private String updatedBy;				// "System", "Admin", "ClaimManager"
}

