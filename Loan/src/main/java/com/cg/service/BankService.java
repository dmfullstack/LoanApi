package com.cg.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.dao.BankDao;
import com.cg.exception.InvalidTransactionException;
import com.cg.exception.LoanNotFoundException;
import com.cg.exception.RevokeLoanException;
import com.cg.exception.UserExistException;
import com.cg.exception.UserNotFoundException;
import com.cg.model.Authentication;
import com.cg.model.Customer;
import com.cg.model.Loan;
import com.cg.model.Transaction;

@Service("bankService")
public class BankService implements BankServiceInterface{
	
	@Autowired
	BankDao bankDao;
	
	private Logger logger=Logger.getLogger(BankService.class);
	
	@Override
	public String login(String email,String password) throws UserNotFoundException
	{
		Authentication auth=bankDao.userAuthentication(email);		// this can also throw UserNotFoundException
		if(auth==null || !auth.getPassword().equals(password))
		{
			logger.error("Invalid credentials");
			throw new UserNotFoundException();
		}
		logger.info("User exist and have id "+auth.getAccountId());
		return auth.getAccountId();
	}

	@Override
	public String registerUser(Authentication authentication) throws UserExistException{
		// TODO Auto-generated method stub
		String accId=LocalDate.now().getYear() + "" + LocalDate.now().getDayOfYear() + LocalTime.now().getHour()
					+ LocalTime.now().getMinute()+LocalTime.now().getSecond()+LocalTime.now().getNano();
		authentication.setAccountId(accId);
		bankDao.addUser(authentication);
		logger.info("Registered with id "+accId);
		return "You are registered Successfully";
	}
	
	@Override
	public String applyLoan(Customer customer) throws RevokeLoanException{
		Loan loan=customer.getLoan();
		// first calculate emi
		double emi=CalculationService.calculateEmi(loan.getLoan_amount()+"",loan.getType(),loan.getEmi_balance()+"");
		// now set emi and date to this Loan
		customer.getLoan().setEmi_amount(emi);
		customer.getLoan().setIssue_date(LocalDate.now()+"");
		customer.getLoan().setLast_payment(LocalDate.now()+"");
		bankDao.addCustomer(customer);
		logger.info("Loan details:- \n"+customer);
		return "Loan sanctioned Successfully";
	}
	
	@Override
	public String payEmi(String acc_no,String month) throws LoanNotFoundException,InvalidTransactionException{
		Transaction et = new Transaction(acc_no,Integer.parseInt(month));
		et.setPayment_time(LocalDate.now()+" "+LocalTime.now().getHour()+":"+LocalTime.now().getMinute());
		
		// needed to update
		Loan customer=bankDao.findLoan(acc_no);
		
		// comments code is the real world scenario.I have to show this application so payment can be done on a single day.
		
//		LocalDate lastDate=LocalDate.parse(customer.getLast_payment());
//		LocalDate today=LocalDate.now();
//		Period period=Period.between(lastDate, today);
//		int monthstillnow=period.getMonths();
//		int paymentfor=customer.getEmi_balance()+Integer.parseInt(month);
//		if(monthstillnow<paymentfor)
//			throw new InvalidTransactionException();

		int no_emi=et.getNo_of_month();							// no of emi you are paying
		int balance_emi=customer.getEmi_balance();				// no of emi need to pay
		if(no_emi>balance_emi)
		{
			logger.error("No of emi > balance emi");
			throw new InvalidTransactionException();
		}
		if(no_emi==balance_emi)									// it means it is last transaction. So now customer will be debt free.
		{
			foreCloseAccount(acc_no);
			return "Your loan is cleared today. Now , you don't have to pay anymore.";
		}
		
		LocalDate lastpayment=LocalDate.parse(customer.getLast_payment());
		customer.setEmi_balance(balance_emi-no_emi);			// updation in balance
		customer.setLast_payment(lastpayment.plusMonths(no_emi)+"");
		bankDao.addTransaction(et,customer);
		
		logger.info("Payed for "+month+" months");
		return "You payed for "+month+" months";
	}
	
	@Override
	public ArrayList<Transaction>getTransactions(String acc_no) {
		return bankDao.getTransactions(acc_no);
	}
	
	@Override
	public boolean foreCloseAccount(String acc_no) {
		bankDao.deleteCustomer(acc_no);
		logger.info("foreclosed account with ID "+acc_no);
		return true;
	}
	
	@Override
	public Map<String,String> loanStatus(String acc_no) throws LoanNotFoundException{
		
		Customer customer=bankDao.findCustomer(acc_no);
		Loan loan=customer.getLoan();
		// calculation
		int emi=loan.getEmi_balance();
		double emi_amount=loan.getEmi_amount();
		double amount=emi*emi_amount;
		double penalty = CalculationService.getForeclosePenalty(amount);
		
		// now binding data so that it can return as map
		Map<String,String> map=new HashMap();
		map.put("name",customer.getCustomer_name());
		map.put("emi",emi+"");
		map.put("emi_amount",emi_amount+"");
		map.put("penalty",penalty+"");
		
		logger.info("Loan Status :\n"+map);
		return map;
	}

}
