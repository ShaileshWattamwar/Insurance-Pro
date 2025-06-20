package com.insurance.claim.exceptions;

public class PolicyValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PolicyValidationException(String msg) {
		super(msg);
	}
}
