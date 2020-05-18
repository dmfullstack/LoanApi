package com.cg.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.exception.InvalidTransactionException;
import com.cg.exception.LoanNotFoundException;
import com.cg.exception.RevokeLoanException;
import com.cg.exception.UserExistException;
import com.cg.exception.UserNotFoundException;
import com.cg.model.Authentication;
import com.cg.model.Customer;
import com.cg.model.Loan;
import com.cg.model.Transaction;
import com.cg.service.BankService;
import com.cg.service.CalculationService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/LoanBackEnd/loan")
public class LoanController {
	
	@Autowired
	BankService bankService;
	
	@GetMapping("/login/{email}/{password}")
	public ResponseEntity<String> checkUser(@PathVariable("email") String email,@PathVariable("password") String password) throws UserNotFoundException {
		String val=bankService.login(email,password);
		return new ResponseEntity<String>(val,HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String>registerUser(@RequestBody Authentication authentication) throws UserExistException{		
		String response=bankService.registerUser(authentication);
		return new ResponseEntity<String>(response,HttpStatus.OK);
	}
	
	@PostMapping("/issue")
	public ResponseEntity<String> applyLoan(@RequestBody Customer customer) throws RevokeLoanException{
		System.out.println(customer);
		String response=bankService.applyLoan(customer);
		return new ResponseEntity<String>(response,HttpStatus.OK);
	}
	
	@PostMapping("/pay")
	public ResponseEntity<String> payEmi(@RequestBody Map<String, String> data) throws LoanNotFoundException,InvalidTransactionException{
		String response=bankService.payEmi(data.get("accountId"),data.get("emi"));
		return new ResponseEntity<String>(response,HttpStatus.OK);
	}
	
	@GetMapping("/transaction/{id}")
	public ArrayList<Transaction> getTransactions(@PathVariable String id) {
		return bankService.getTransactions(id);
	}
	 
	// useful for checking the loan details. Used for foreclose and  balance emi
	@GetMapping("/loanstatus/{id}")
	public ResponseEntity<Map<String, String>> loanStatus(@PathVariable String id) throws LoanNotFoundException{
		System.out.println(id);
		Map<String,String> response=bankService.loanStatus(id);
		
		return new ResponseEntity<Map<String,String>>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/foreclose/{id}")
	public ResponseEntity<String> foreClose(@PathVariable String id) {
		bankService.foreCloseAccount(id);
		return new ResponseEntity<String>("foreclosed",HttpStatus.OK);
	}
	
	@GetMapping("calculateemi/{amount}/{type}/{month}")
	public ResponseEntity<String>calculateEmi(@PathVariable("amount") String amount,@PathVariable("type") String type,@PathVariable("month") String month) {
		String response=CalculationService.calculateEmi(amount,type,month)+"";
		return new ResponseEntity<String>(response,HttpStatus.OK);
	}
	
}

