package com.claim.preauth.services.impl;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.claim.preauth.dto.request.NotificationEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationEventProducer {
	
	 private final KafkaTemplate<String, NotificationEvent> kafkaTemplate;
	 public static final String NOTIFICATION_TOPIC = "notification-events";

	    public void sendNotification(NotificationEvent event) {
	        kafkaTemplate.send(NOTIFICATION_TOPIC, event);
	    }
	
}
