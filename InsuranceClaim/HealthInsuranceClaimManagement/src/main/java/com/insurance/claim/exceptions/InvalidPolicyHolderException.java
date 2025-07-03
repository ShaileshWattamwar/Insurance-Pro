package com.insurance.claim.exceptions;

public class InvalidPolicyHolderException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InvalidPolicyHolderException(String msg) {
		super(msg);
	}
}
