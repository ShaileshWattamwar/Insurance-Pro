package com.claim.preauth.exceptions;

public class OperationNotAllowedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public OperationNotAllowedException(String msg) {
		super(msg);
	}
}
