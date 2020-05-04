package com.cg.service;

import java.util.ArrayList;
import java.util.Map;

import com.cg.exception.InvalidTransactionException;
import com.cg.exception.LoanNotFoundException;
import com.cg.exception.RevokeLoanException;
import com.cg.exception.UserExistException;
import com.cg.exception.UserNotFoundException;
import com.cg.model.Authentication;
import com.cg.model.Customer;
import com.cg.model.Transaction;

public interface BankServiceInterface {

	public String login(String email,String password) throws UserNotFoundException;
	
	public String registerUser(Authentication authentication) throws UserExistException;
	
	public String applyLoan(Customer customer) throws RevokeLoanException;
	
	public String payEmi(String acc_no,String month) throws LoanNotFoundException,InvalidTransactionException;
	
	public ArrayList<Transaction>getTransactions(String acc_no);
	
	public boolean foreCloseAccount(String acc_no);
	
	public Map<String,String> loanStatus(String acc_no) throws LoanNotFoundException;
}
