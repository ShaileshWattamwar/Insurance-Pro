package com.insurance.claim.exceptions;

public class ReimbursementNotAllowedException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ReimbursementNotAllowedException(String msg) {
		super(msg);
	}
	
}
