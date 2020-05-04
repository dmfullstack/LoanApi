package com.cg.model;


//create table LoanInfo( account_no varchar2(30), type varchar2(10),loan_amount number(10,3), emi_amount number(10,3), balance number(10,3), issue_date varchar2(30), foreign key(account_no) references CustomerInfo(account_no) );

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Loan
{

	@Id
	@Column(name="account_no")
	private String account_no;			// making this as foreign key will not allow any holder to take loan again.
	private String type;
	private String issue_date;
	private String last_payment;
	private double loan_amount;
	private double emi_amount;
	private int emi_balance;
	
	public Loan() {
	}

	public Loan(String type, double loan_amount, int emi_balance) {
		super();
		this.type = type;
		this.loan_amount = loan_amount;
		this.emi_balance = emi_balance;
	}


	@Override
	public String toString() {
		return "LoanInfo [account_no=" + account_no + ", type=" + type + ", loan_amount=" + loan_amount
				+ ", emi_amount=" + emi_amount + ", emi_balance=" + emi_balance + ", issue_date=" + issue_date + "]";
	}

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(String issue_date) {
		this.issue_date = issue_date;
	}

	public String getLast_payment() {
		return last_payment;
	}

	public void setLast_payment(String last_payment) {
		this.last_payment = last_payment;
	}

	public double getLoan_amount() {
		return loan_amount;
	}

	public void setLoan_amount(double loan_amount) {
		this.loan_amount = loan_amount;
	}

	public double getEmi_amount() {
		return emi_amount;
	}

	public void setEmi_amount(double emi_amount) {
		this.emi_amount = emi_amount;
	}

	public int getEmi_balance() {
		return emi_balance;
	}

	public void setEmi_balance(int emi_balance) {
		this.emi_balance = emi_balance;
	}
	
	
	
}