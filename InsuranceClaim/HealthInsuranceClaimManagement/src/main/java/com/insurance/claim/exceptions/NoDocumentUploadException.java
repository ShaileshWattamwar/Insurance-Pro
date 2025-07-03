package com.insurance.claim.exceptions;


public class NoDocumentUploadException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public NoDocumentUploadException(String msg) {
		super(msg);
	}
	
}
