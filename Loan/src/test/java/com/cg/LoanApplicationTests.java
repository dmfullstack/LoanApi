package com.cg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.exception.InvalidTransactionException;
import com.cg.exception.LoanNotFoundException;
import com.cg.exception.RevokeLoanException;
import com.cg.exception.UserExistException;
import com.cg.exception.UserNotFoundException;
import com.cg.model.Authentication;
import com.cg.model.Customer;
import com.cg.model.Loan;
import com.cg.service.BankService;


@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
class LoanApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	BankService bankService;

	@Test
	@Order(1)
	public void registerTest() throws UserExistException {
		Authentication auth=new Authentication("s@gmail.com","sachin");
		assertEquals("You are registered Successfully",bankService.registerUser(auth));
	}
	
	@Test
	@Order(2)
	public void invalidLoginTest() throws UserNotFoundException {		
		assertThrows(UserNotFoundException.class,()->bankService.login("fdsjfj", "fdsfds"),"It should throw error.");
	}
	
	
	@Test
	@Order(3)
	public void applyLoanTest() throws RevokeLoanException {
		Customer customer=new Customer();
		customer.setAadhaar("1234567891234567");
		customer.setAccount_no("2244422");
		customer.setCustomer_name("sachin");
		customer.setEmail("s@gmail.com");
		customer.setFather_name("lalit");
		customer.setGender("male");
		customer.setPhone("9720894091");
		customer.setSalary(60000);
		
		Loan loan=new Loan("car",50000,10);
		customer.setLoan(loan);
		
		assertEquals("Loan sanctioned Successfully",bankService.applyLoan(customer));
	}
	
	@Test
	@Order(4)
	public void payEmiTest() throws LoanNotFoundException, InvalidTransactionException {
		// In the last applyLoanTest , I applied for loan , so that one I'm testing;
		String month="2";
		String acc_no="2244422";
		assertEquals("You payed for "+month+" months",bankService.payEmi(acc_no, month));
	}
	
	@Test
	@Order(5)
	public void invalidEmiTest() throws LoanNotFoundException, InvalidTransactionException {
		String month="12";
		String acc_no="2244422";
		assertThrows(InvalidTransactionException.class,()->bankService.payEmi(acc_no, month),"it should throw an error");
	}
	
	// foreclose doesn't need to check with wrong id because this cause won't happend in my algorithm
	@Test
	@Order(6)
	public void foreCloseTest() {
		String acc_no="2244422";
		assertEquals(true,bankService.foreCloseAccount(acc_no));
	}
}
