package com.insurance.claim.exceptions;

public class InsufficientCoverageException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InsufficientCoverageException(String msg) {
		super(msg);
	}
}
