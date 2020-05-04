package com.cg.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Authentication 
{
	@Id
	private String email;
	private String password;
	private String accountId;
	
	public Authentication(){}
	public Authentication(String email,String password) 
	{
		this.email=email;
		this.password=password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	

}
