package com.paysecure.test;



import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.paysecure.base.baseClass;
import com.paysecure.loginPage.loginPage;
import com.paysecure.utilities.ExtentManager;



public class Login extends baseClass {
	
	
	
	
	private loginPage lp;
	
	@BeforeMethod
	public void setupPages() {
		lp= new loginPage(getDriver());
		lp.login("Suhas","Nick@123");
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void verifyValidLoginTest() {
		
		//ExtentManager.startTest("Valid Login Test"); --This has been implemented in TestListener
		System.out.println("Running testMethod1 on thread: " + Thread.currentThread().getId());
		ExtentManager.logStep("Navigating to Login Page entering username and password");
		lp.login("Suhas","Nick@123");
		ExtentManager.logStep("Verifying homepage is visible or not");
	
		ExtentManager.logStep("Validation Successful");

	}
	
//	@Test()
//	public void inValidLoginTest(String username, String password) {
//		//ExtentManager.startTest("In-valid Login Test!"); --This has been implemented in TestListener
//		System.out.println("Running testMethod2 on thread: " + Thread.currentThread().getId());
//		ExtentManager.logStep("Navigating to Login Page entering username and password");
//		loginPage.login(username, password);
//		String expectedErrorMessage = "Invalid credentials";
//		Assert.assertTrue(loginPage.verifyErrorMessage(expectedErrorMessage),"Test Failed: Invalid error message");
//		ExtentManager.logStep("Validation Successful");
//	}
}
