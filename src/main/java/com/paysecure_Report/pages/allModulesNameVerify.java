package com.paysecure_Report.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.paysecure.actiondriver.ActionDriver;
import com.paysecure.base.baseClass;

public class allModulesNameVerify {

	
	private By moduleName = By.xpath("//div[contains(@class,'tPad56')]/descendant::span");
	/*
	 * //div[@class='d-flex flex-column flex-shrink-0  bg-body-tertiary leftFirstPad56']/ul/li[@class='nav-item']/a/span/ancestor::div[2]/descendant::a
	 */
	
	private ActionDriver actionDriver;
	// page factory constructor
	public allModulesNameVerify(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.actionDriver = new ActionDriver(driver);
	}
	
	
	public void getAllModuleName() {
		
		WebDriver driver = baseClass.getDriver();
		List<WebElement> module_Name = driver.findElements(moduleName);
		
		for(WebElement m:module_Name) {
			System.err.println(m.getText());
		}
		
		
	}
	
	

}
