package com.cg.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

// create table CustomerInfo(account_no varchar2(30), customer_name varchar2(20), phone varchar2(10), email varchar2(30), aadhaar varchar2(16) not null, disabilities varchar2(30), salary number(10,3), primary key(account_no), unique(aadhaar));

@Entity
public class Customer
{
	@Id
	private String account_no;
	private String customer_name,father_name,gender, phone, email, aadhaar;		// phone no is varchar because I'll do validation in angular.
	private double salary;
	
	// one customer can have one loan. Relation is unidirectional
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="account_no")
	private Loan loan;
	
	public Customer() {}
	
	@Override
	public String toString() {
		return "CustomerInfo [account_no=" + account_no + ", customer_name=" + customer_name + ", phone=" + phone
				+ ", email=" + email + ", aadhaar=" + aadhaar + ", salary="
				+ salary + ", loan=" + loan + "]";
	}


	public String getAccount_no() {
		return account_no;
	}


	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}


	public String getCustomer_name() {
		return customer_name;
	}


	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}


	public String getFather_name() {
		return father_name;
	}


	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAadhaar() {
		return aadhaar;
	}


	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}


	public double getSalary() {
		return salary;
	}


	public void setSalary(double salary) {
		this.salary = salary;
	}


	public Loan getLoan() {
		return loan;
	}


	public void setLoan(Loan loan) {
		this.loan = loan;
	}
	
	

}