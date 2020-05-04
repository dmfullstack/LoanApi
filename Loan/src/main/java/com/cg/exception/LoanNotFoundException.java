package com.cg.exception;

public class LoanNotFoundException extends Exception {

	public LoanNotFoundException() {
		super("You didn't have any loan");
	}
}
