package com.cg.exception;

public class RevokeLoanException extends Exception {
	
	public RevokeLoanException() {
		super("You have already took a loan");
	}
}
