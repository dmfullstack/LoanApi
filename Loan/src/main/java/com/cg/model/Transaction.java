package com.cg.model;


//create table TransactionInfo(account_no varchar2(30), no_of_month number(2), payment_time varchar2(30), foreign key(account_no) references CustomerInfo(account_no) );

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transaction
{
	
	private String account_no;
	private int no_of_month;
	@Id
	private String payment_time;
	
	public Transaction() {}

	public Transaction(String id, int noOfmonth) {
		super();
		this.account_no=id;
		this.no_of_month = noOfmonth;
	}
	
	// printing transaction
		@Override
		public String toString() {
			return "Emi Transaction for id=" + account_no + ", noOfmonth=" + no_of_month + ", paymentTime=" + payment_time + "";
		}	
	
	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public int getNo_of_month() {
		return no_of_month;
	}

	public void setNo_of_month(int no_of_month) {
		this.no_of_month = no_of_month;
	}

	public String getPayment_time() {
		return payment_time;
	}

	public void setPayment_time(String payment_time) {
		this.payment_time = payment_time;
	}

	
}