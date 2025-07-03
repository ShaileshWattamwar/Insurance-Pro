package com.claim.preauth.utils;

import org.springframework.stereotype.Component;

import com.claim.preauth.constant.NotificationCategory;
import com.claim.preauth.constant.NotificationType;
import com.claim.preauth.dto.request.NotificationEvent;
import com.claim.preauth.dto.response.HospitalDTO;
import com.claim.preauth.dto.response.PolicyholderDTO;
import com.claim.preauth.entity.PreAuthorizationEntity;
import com.claim.preauth.services.impl.NotificationEventProducer;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificationHandler {

	private final NotificationEventProducer notificationProducer;
	
	public void handleNotification(PreAuthorizationEntity savedPreAuth, PolicyholderDTO holder, HospitalDTO hospital) {
		
		String referenceId = savedPreAuth.getPreAuthRequestId();
		String status = savedPreAuth.getAuthStatus().toString();
		String message = String.format("Pre-Authorization Request (%s) has been %s for Policyholder %s.",
				savedPreAuth.getPreAuthRequestId(), status, savedPreAuth.getPolicyHolderId());
		
		String subject = "Pre-Authorization " + savedPreAuth.getAuthStatus();
		
		// Notify PolicyHolder
		NotificationEvent policyHolderEvent = NotificationEvent.builder()
				.referenceId(referenceId)
				.recipientEmail(holder.getEmailId())
				.recipientPhone(holder.getContactNo())
				.subject(subject)
				.message(message)
				.category(NotificationCategory.PREAUTH)
				.type(NotificationType.BOTH)
				.build();
		
		notificationProducer.sendNotification(policyHolderEvent);
		
		// Notify Hospital
		NotificationEvent hospitalEvent = NotificationEvent.builder()
				.referenceId(referenceId)
				.recipientEmail(hospital.getEmail())
				.recipientPhone(hospital.getContact())
				.subject(subject)
				.message(message)
				.category(NotificationCategory.PREAUTH)
				.type(NotificationType.BOTH)
				.build();
		
		notificationProducer.sendNotification(hospitalEvent);
	}
}









