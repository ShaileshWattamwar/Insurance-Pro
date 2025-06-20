package com.claim.notification.entity;

import java.time.LocalDateTime;

import com.claim.notification.constants.NotificationCategory;
import com.claim.notification.constants.NotificationStatus;
import com.claim.notification.constants.NotificationType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Entity
@Table(name = "notifications")
public class NotificationEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String referenceId;				// claimId or pre-authId or paymentId
    private String recipientEmail;			// PolicyHolder 
    private String recipientPhone;			// PolicyHolder or Hospital

    @Enumerated(EnumType.STRING)
    private NotificationType type;			// EMAIL, SMS, BOTH

    @Enumerated(EnumType.STRING)
    private NotificationCategory category;		// CLAIM, PREAUTH, PAYMENT

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;			// APPROVED, REJECTED, COMPLETED

    private String subject;
    private String message;
    private String errorMessage;
    private LocalDateTime sentAt;
	
}
