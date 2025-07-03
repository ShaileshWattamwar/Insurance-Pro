package com.insurance.claim.exceptions;

public class PolicyInactiveException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public PolicyInactiveException(String msg) {
		super(msg);
	}
}
