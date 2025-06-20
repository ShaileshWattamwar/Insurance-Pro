package com.claim.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.claim.notification.dto.NotificationEvent;
import com.claim.notification.service.impl.AwsEmailServiceImpl;
import com.claim.notification.service.impl.AwsSmsServiceImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DispatcherService {

	private final ApplicationContext context;
	private final Logger logger = LoggerFactory.getLogger(DispatcherService.class);
//	private final Map<String, NotificationService> notificationService;

	void dispatch(NotificationEvent event) {

		switch (event.getType()) {

		case EMAIL -> {
			context.getBean(AwsEmailServiceImpl.class).processNotification(event);
//							notificationService.get("").processNotification(eventDto);
			logger.info("Proccessing EMAIL Notification ={}", event.getType());
		}

		case SMS -> {
			context.getBean(AwsSmsServiceImpl.class).processNotification(event);
//						notificationService.get("").processNotification(event);
			logger.info("Proccessing SMS Notification ={}", event.getType());
		}

		case BOTH -> {
			context.getBean(AwsEmailServiceImpl.class).processNotification(event);
			context.getBean(AwsSmsServiceImpl.class).processNotification(event);
//				  notificationServices.get("").processNotification(event);
//	              notificationServices.get("").processNotification(event);
		}
		default -> throw new IllegalArgumentException("Unexpected value: " + event.getType());
		}
	}
}
