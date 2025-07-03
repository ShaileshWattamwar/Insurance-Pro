package com.claim.notification.service;

import com.claim.notification.dto.NotificationEvent;

public interface NotificationService {
	
	void processNotification(NotificationEvent event);
}
