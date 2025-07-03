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
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Service
@RequiredArgsConstructor
public class AwsSmsServiceImpl implements NotificationService {

	private final SnsClient snsClient;
	private final NotificationRepository notificationRepo;
	private static final Logger logger = LoggerFactory.getLogger(AwsEmailServiceImpl.class);
	
	@Override
	public void processNotification(NotificationEvent event) {
		
		logger.info("Initiating Email notification | NotificationEvent={} ", event);
		
		boolean status = false;
		String errorMsg = null;
		
		try {
			logger.info("Sending SMS to {}", event.getRecipientPhone());
			
			PublishRequest publishRequest = PublishRequest.builder()
						.phoneNumber(event.getRecipientPhone())
						.message(event.getMessage())
						.build();
			
			snsClient.publish(publishRequest);
			logger.info("Email notification sent successfully| NotificationEvent={} ", event);
			status = true;
		} catch (Exception e) {
			errorMsg = e.getMessage();
			throw new NotificationFailedException("SMS Notification failed ::"+e);
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
		
		logger.info("SMS NotificationEntity save to DB | NotificationEntity={} ", entity);
	}

}
