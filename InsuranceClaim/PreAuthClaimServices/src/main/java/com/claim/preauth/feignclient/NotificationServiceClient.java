package com.claim.preauth.feignclient;

import org.springframework.web.bind.annotation.PostMapping;

//@FeignClient(name = "notification-service", url = "${service.notification.url}")
public interface NotificationServiceClient {
	
	@PostMapping("")
    void sendNotification(String notificationDTO);
}
