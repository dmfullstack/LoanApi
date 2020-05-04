package com.cg.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.exception.InvalidTransactionException;
import com.cg.exception.LoanNotFoundException;
import com.cg.exception.RevokeLoanException;
import com.cg.exception.UserExistException;
import com.cg.exception.UserNotFoundException;

@RestControllerAdvice
public class ExceptionController {
	
	
	@ExceptionHandler(UserExistException.class)
	public ResponseEntity<String> handleError1(UserExistException e) {
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleError2(UserNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(LoanNotFoundException.class)
	public ResponseEntity<String> handleError2(LoanNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(RevokeLoanException.class)
	public ResponseEntity<String> handleError3(RevokeLoanException e) {
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(InvalidTransactionException.class)
	public ResponseEntity<String> handleError4(InvalidTransactionException e) {
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.UNAUTHORIZED);
	}
	
}
