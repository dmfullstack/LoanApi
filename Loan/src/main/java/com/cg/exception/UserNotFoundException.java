package com.cg.exception;

public class UserNotFoundException extends Exception {

	public UserNotFoundException() {
		super("Please use right credentials");
	}
}
