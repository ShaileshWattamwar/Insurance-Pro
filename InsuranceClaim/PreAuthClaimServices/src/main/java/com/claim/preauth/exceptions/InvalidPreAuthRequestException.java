package com.claim.preauth.exceptions;

public class InvalidPreAuthRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidPreAuthRequestException(String msg) {
		super(msg);
	}
}

