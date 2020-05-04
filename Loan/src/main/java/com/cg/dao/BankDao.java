package com.cg.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.cg.exception.LoanNotFoundException;
import com.cg.exception.RevokeLoanException;
import com.cg.exception.UserExistException;
import com.cg.exception.UserNotFoundException;
import com.cg.model.Authentication;
import com.cg.model.Customer;
import com.cg.model.Loan;
import com.cg.model.Transaction;

@Repository("bankDao")
@Transactional
public class BankDao implements BankDaoInterface{
	
	@PersistenceContext
	EntityManager em;
	
	// for register User
	@Override
	public void addUser(Authentication authentication) throws UserExistException {
		try{
			em.persist(authentication);
		}
		catch(RollbackException e){
			throw new UserExistException();
		}
	}
	
	// for login
	@Override
	public Authentication userAuthentication(String email) throws UserNotFoundException{
			// TODO Auto-generated method stub
			Authentication auth=em.find(Authentication.class, email);
			if(auth==null)
				throw new UserNotFoundException();
			return auth;
		}

	// It add customer details and loan details like emi while sanctioning any loan.
	@Override
	public void addCustomer(Customer customer) throws RevokeLoanException
	{
		try {
			em.persist(customer);
		}
		catch(RollbackException e){
			throw new RevokeLoanException();
		}
	}

	// for customer details
	@Override
	public Customer findCustomer(String accNo) throws LoanNotFoundException{
		
		Customer customer=em.find(Customer.class, accNo);
		if(customer==null)
			throw new LoanNotFoundException();
		return customer;
	}
	
	// useful while paying emi
	@Override
	public Loan findLoan(String acc_no) throws LoanNotFoundException{
		// TODO Auto-generated method stub
		Loan loan=em.find(Loan.class, acc_no);
		if(loan==null)
			throw new LoanNotFoundException();
		return loan;
	}
	
	// useful while paying emi
	@Override
	public void addTransaction(Transaction et,Loan c) {
		em.merge(c);		// loan is updated means no of emi will be updated
		em.persist(et);		// transaction is added.
	}
	
	// to get transaction of particular user
	@Override
	public ArrayList<Transaction> getTransactions(String acc_no){
		
		String str = "select t from Transaction t where t.account_no like :id";
		TypedQuery<Transaction> query = em.createQuery(str, Transaction.class);
		query.setParameter("id", "%" + acc_no + "%");
		return (ArrayList<Transaction>) query.getResultList();
	}
	
	@Override
	public void deleteCustomer(String acc_no) {
		Customer customer=em.find(Customer.class, acc_no);
		em.remove(customer);
		String str="delete from Transaction t where t.account_no = :id";
		Query query=em.createQuery(str);
		query.setParameter("id", acc_no).executeUpdate();	
	}
	
}
