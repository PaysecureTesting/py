package com.paysecure_Report.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.paysecure.actiondriver.ActionDriver;

public class errorLog_Page {

	//navigate uptoAPI LOG
	private By analytics = By.xpath("//span[text()='Analytics']");
	private By report = By.xpath("(//span[text()='Report'])[2]");
	private By errorLog = By.xpath("//span[text()='Error Log']");
	private By ERRORLOG = By.xpath("//h1[text()='Error Log']");
	
	
	// filter through select merchant
	private By selectmerchant = By.xpath("//span[text()='Select Merchant']");
	private By searchmerchant = By.xpath("//input[@class='select2-search__field']");
	
	//search through - Merchant Ref Id
	private By merchant_Ref_Id = By.xpath("//input[@id='merRefId']");
	
	
	//search through - Purchase Id
		private By purchase_Id = By.xpath("//input[@id='purchaseId']");
	
		//search button
		private By search= By.xpath("//button[text()='Search']");
	
	 private ActionDriver actionDriver;
	// page factory constructor
	public errorLog_Page(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.actionDriver = new ActionDriver(driver);
	}
	
	public void navigateUptoErrorLog() {
		
		actionDriver.scrollToElement(analytics);
		Reporter.log("User navigate upto Analytics", true);
		
		actionDriver.click(report);
		Reporter.log("User click on Report Main Module", true);
		
		
		actionDriver.click(errorLog);
		Reporter.log("User click on Error Log Sub Module", true);
		
		Assert.assertTrue(actionDriver.isDisplayed(ERRORLOG),"If Error Log is not displays on Error log page then user is not on the Error log page");                                              
		Reporter.log("User is on the Error Log Sub Module", true);
		
	}
	
	public void selectDashboardusinAllMerchant(WebDriver driver,String partialMerchantname,String merchant ) throws InterruptedException {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		Thread.sleep(2000);
		actionDriver.click(selectmerchant);
		

		Reporter.log("click on the select merchant filed", true);
		Thread.sleep(2000);
		w.until(ExpectedConditions.elementToBeClickable(searchmerchant)).sendKeys(partialMerchantname);
		Reporter.log("Enter merchant name in a search merchant functioanlity", true);

		Thread.sleep(2000);

		List<WebElement> suggestions = w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//ul[@id='select2-merchantList-results']/li")));

		for (WebElement s : suggestions) {
			if (merchant.equalsIgnoreCase(s.getText().trim())) {
				Thread.sleep(1000);
				s.click();
				Reporter.log("select merchant name :- " + merchant, true);
				break;

			}
		}
		Reporter.log("select merchant from all merchnat", true);

	}
	
	
	
	public void search_Merchant_Ref_Id() {
		actionDriver.enterText(merchant_Ref_Id, "686cc3549e34dc5662676bf0");
		Reporter.log("User enter Merchant Ref ID in merchant ref ID Text Field", true);
		
	}
	
	public void search_Purchase_Id() {
		actionDriver.enterText(purchase_Id, "686cc3549e34dc5662676bf0");
		Reporter.log("User enter Purchase Id in Purchase Id Text Field", true);
		
	}
	
	public void searchButton() {
		actionDriver.click(search);
		Reporter.log("User click on search button", true);
		
	}
	
	
}
