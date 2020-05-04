package com.cg.dao;

import java.util.ArrayList;

import com.cg.exception.LoanNotFoundException;
import com.cg.exception.RevokeLoanException;
import com.cg.exception.UserExistException;
import com.cg.exception.UserNotFoundException;
import com.cg.model.Authentication;
import com.cg.model.Customer;
import com.cg.model.Loan;
import com.cg.model.Transaction;

public interface BankDaoInterface {
	
	// for register
	public void addUser(Authentication authentication) throws UserExistException;
	
	//for login
	public Authentication userAuthentication(String email) throws UserNotFoundException;

	// It add customer details.
	public void addCustomer(Customer customer) throws RevokeLoanException;

	public Customer findCustomer(String accNo) throws LoanNotFoundException;
	
	public Loan findLoan(String acc_no) throws LoanNotFoundException;
	
	// useful while paying emi
	public void addTransaction(Transaction et,Loan c);
	
	public ArrayList<Transaction> getTransactions(String acc_no);
	
	public void deleteCustomer(String acc_no);
}
