package com.paysecure.bankPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.paysecure.actiondriver.ActionDriver;

public class allBanks {

	//navigate upto bank
	private By bank = By.xpath("//span[text()='Banks']");
	private By allBank = By.xpath("//span[text()='All Banks']");
	private By banks = By.xpath("//h1[text()='Banks']");
	private By bankQuantity = By.xpath("//span[@id='llb3']");
	
	//select MID
	private By mid= By.xpath("(//button[@class='btn btn-primary btn-sm ng-scope'])[1]");
	private By searchBank=By.xpath("//input[@placeholder='Search bank name']");
	
	
	public static int num;
	
	private ActionDriver actionDriver;

	// page factory constructor
	public allBanks(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.actionDriver = new ActionDriver(driver);
	}
	
	
	public void navigateUptoAllBanks() {
		
		actionDriver.click(bank);
		//Reporter.log("", true);
		Reporter.log("User Click on Bank Module", true);
		
		actionDriver.click(allBank);
		Reporter.log("User Click on All Bank Sub Module", true);
		
		Assert.assertTrue(actionDriver.isDisplayed(banks), "If Banks is not displays then Our TC is in Failed condition");                                                                                                                                                                               
		Reporter.log("User is on the Banks Page", true);
	}
	
	
	public int getQuantityOfAllBank() {
		
		actionDriver.scrollToElement(bankQuantity);
		Reporter.log("User navigate upto bank of quantity", true);
		
		 String quantity = actionDriver.getText(bankQuantity);
		
		 num = Integer.parseInt(quantity);
		System.err.println("Total banks quantity :- "+ num);
		
		return num;
	}
	
	
	public void viewMIDOnAllBanks() {
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
