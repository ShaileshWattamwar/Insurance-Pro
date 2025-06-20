package com.claim.notification.service.impl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.claim.notification.constants.NotificationStatus;
import com.claim.notification.dto.NotificationEvent;
import com.claim.notification.entity.NotificationEntity;
import com.claim.notification.exceptions.NotificationFailedException;
import com.claim.notification.repo.NotificationRepository;
import com.claim.notification.service.NotificationService;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.Body;
import software.amazon.awssdk.services.ses.model.Content;
import software.amazon.awssdk.services.ses.model.Destination;
import software.amazon.awssdk.services.ses.model.Message;
import software.amazon.awssdk.services.ses.model.SendEmailRequest;

@Service
@RequiredArgsConstructor
public class AwsEmailServiceImpl implements NotificationService {
	
	private final SesClient sesClient;
	private final NotificationRepository notificationRepo;
	private static final Logger logger = LoggerFactory.getLogger(AwsEmailServiceImpl.class);
	
	@Override
	public void processNotification(NotificationEvent event) {
		
		logger.info("Initiating Email notification | NotificationEvent={} ", event);
		
		boolean status = false;
		String errorMsg = null;
		
		try {
			logger.info("Sending EMAIL to {}", event.getRecipientEmail());
			logger.debug("Subject={}, Message={}", event.getSubject(), event.getMessage());
			
			SendEmailRequest request = SendEmailRequest.builder()
	                .destination(Destination.builder().toAddresses(event.getRecipientEmail()).build())
	                .message(Message.builder()
	                    .subject(Content.builder().data(event.getSubject()).build())
	                    .body(Body.builder().text(Content.builder().data(event.getMessage()).build()).build())
	                    .build())
	                .source("verified@example.com") // Must be verified in SES
	                .build();
			
			sesClient.sendEmail(request);
			
			logger.info("Email notification sent successfully|| NotificationEvent={} ", event);
			status = true;
		} catch (Exception e) {
			errorMsg = e.getMessage();
			throw new NotificationFailedException("Mail Notification failed ::"+e);
		}
		
		NotificationEntity entity = notificationRepo.save(NotificationEntity.builder()
				.referenceId(event.getReferenceId())
				.recipientEmail(event.getRecipientEmail())
				.recipientPhone(event.getRecipientPhone())
				.type(event.getType())
				.category(event.getCategory())
				.status(status? NotificationStatus.SENT : NotificationStatus.FAILED)
				.subject(event.getSubject())
				.message(event.getMessage())
				.errorMessage(errorMsg)
				.sentAt(LocalDateTime.now())				
				.build());
		
		logger.info("Mail NotificationEntity save to DB | NotificationEntity={} ", entity);
		
	}

}
