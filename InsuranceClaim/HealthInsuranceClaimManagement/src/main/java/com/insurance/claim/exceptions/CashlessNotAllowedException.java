package com.insurance.claim.exceptions;

public class CashlessNotAllowedException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CashlessNotAllowedException(String msg) {
		super(msg);
	}
}
