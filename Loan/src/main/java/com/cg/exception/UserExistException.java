package com.cg.exception;

public class UserExistException extends Exception {
	
	public UserExistException(){
		super("User already exist with these credentials");
	}
}
