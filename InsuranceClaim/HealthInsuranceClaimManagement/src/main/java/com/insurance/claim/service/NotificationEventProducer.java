package com.insurance.claim.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.insurance.claim.dto.request.NotificationEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificationEventProducer {
	
	 private final KafkaTemplate<String, NotificationEvent> kafkaTemplate;
	 public static final String NOTIFICATION_TOPIC = "notification-events";

	    public void sendNotification(NotificationEvent event) {
	        kafkaTemplate.send(NOTIFICATION_TOPIC, event);
	    }
	
}
