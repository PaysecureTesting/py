package com.paysecure.loginPage;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.paysecure.actiondriver.ActionDriver;
import com.paysecure.base.baseClass;

public class loginPage {

	private ActionDriver actionDriver;

	private By loginButton = By.xpath("//button[@type='submit']");
	private By id = By.xpath("//input[@name='username']");
	private By pass = By.xpath("//input[@id='floatingPassword']");

	public loginPage(WebDriver driver) {
		this.actionDriver = baseClass.getActionDriver();
	}

	// Method to perform login
	public void login(String userName, String password) {
	//	actionDriver.sendKeysWithActions(id,userName);
		actionDriver.enterText(id,userName);
		Reporter.log("User enter ID in Username field", true);
		actionDriver.enterText(pass,password);
		Reporter.log("User Enter Password in password field", true);
		actionDriver.click(loginButton);
		Reporter.log("Click on submit button in login page", true);

}}