package com.claim.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.claim.notification.dto.NotificationEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationConsumer {
	
	private static final Logger logger = LoggerFactory.getLogger(NotificationConsumer.class);
	private final DispatcherService dispatcherService; 
	
	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void notificationEventConsumer(NotificationEvent event) {
		logger.info("Notification Event consumed ={}", event);
		
		dispatcherService.dispatch(event);
	}
}
