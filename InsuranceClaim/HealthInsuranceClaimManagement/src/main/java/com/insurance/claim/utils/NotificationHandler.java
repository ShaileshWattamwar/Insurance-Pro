package com.insurance.claim.utils;

import org.springframework.stereotype.Component;

import com.insurance.claim.constants.NotificationCategory;
import com.insurance.claim.constants.NotificationType;
import com.insurance.claim.dto.request.NotificationEvent;
import com.insurance.claim.entities.HospitalEntity;
import com.insurance.claim.entities.PolicyClaimEntity;
import com.insurance.claim.entities.PolicyHolder;
import com.insurance.claim.service.NotificationEventProducer;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificationHandler {

	private final NotificationEventProducer notificationProducer;

	public void handleNotification(PolicyClaimEntity claim) {

		PolicyHolder holder = claim.getPolicyHolder();
		HospitalEntity hospital = claim.getHospital();

		String referenceId = claim.getPolicyClaimId();
		String status = claim.getClaimStatus().toString();
		String message = String.format("Your Claim Request (%s) has been %s.  Policyholder: %s, Type: %s, Hospital ID: %s", 
				referenceId, status, holder.getPolicyHolderId(), claim.getClaimType(), hospital.getHospitalId());

		String subject = "Pre-Authorization " + claim.getClaimStatus();

		// Notify PolicyHolder
		NotificationEvent policyHolderEvent = NotificationEvent.builder()
				.referenceId(referenceId)
				.recipientEmail(holder.getEmail())
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
