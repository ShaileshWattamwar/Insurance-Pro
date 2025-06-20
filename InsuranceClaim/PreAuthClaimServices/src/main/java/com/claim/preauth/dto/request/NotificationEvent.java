package com.claim.preauth.dto.request;

import com.claim.preauth.constant.NotificationCategory;
import com.claim.preauth.constant.NotificationType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationEvent {
	
	private String referenceId;         // claimId or pre-authId or paymentId
    private String recipientEmail;
    private String recipientPhone;
    private String subject;
    private String message;						// Notification message body
    private NotificationCategory category; 		// CLAIM, PREAUTH, PAYMENT
    private NotificationType type;     			// EMAIL, SMS, BOTH
}
