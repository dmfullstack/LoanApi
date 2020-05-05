package com.cg;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.service.CalculationService;



@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
class CustomerServiceTest {

	@Test
	public void emiTest() {
		double ans=CalculationService.calculateEmi("50000", "home", "10");
		assertEquals(5450,ans,0.01);
	}
	
	@Test
	public void penaltyTest() {
		double ans=CalculationService.getForeclosePenalty(50000);
		assertEquals(ans,1500,0.01);
	}
	
}
