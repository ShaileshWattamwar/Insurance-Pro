package com.claim.notification.exceptions;

public class NotificationFailedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotificationFailedException(String msg) {
		super(msg);
	}
}
