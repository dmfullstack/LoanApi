package com.cg.service;

public class CalculationService {

	public static double calculateEmi(String sum,String type,String noOfMonth)
	{
		double rate=0.0;
		double sum1=Double.parseDouble(sum);
		double noOfMonth1=Double.parseDouble(noOfMonth);
		
		if(type.equalsIgnoreCase("home"))
			rate=9;
		else if(type.equalsIgnoreCase("education"))
			rate=6.8;
		else
			rate=10.0;
		
		double interest=(rate/100)*(sum1/noOfMonth1);
		double emi=(sum1/noOfMonth1)+interest;
		return emi;
	}

	public static double getForeclosePenalty(double balance) {
		// TODO Auto-generated method stub
		return (3.0/100)*balance;
	}
}
