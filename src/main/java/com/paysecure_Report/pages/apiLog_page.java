package com.paysecure_Report.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.apache.poi.hpsf.Array;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.paysecure.actiondriver.ActionDriver;



public class apiLog_page {
	
	//navigate uptoAPI LOG
	private By analytics = By.xpath("//span[text()='Analytics']");
	private By report = By.xpath("(//span[text()='Report'])[2]");
	private By apiLog = By.xpath("//span[text()='Api Log']");
	private By apiReport = By.xpath("//h1[text()='Api Report']");
	
	//select merchant
	private By selectmerchant = By.xpath("//span[text()='Select Merchant']");
	private By searchmerchant = By.xpath("//input[@class='select2-search__field']");
	private By selectSearchmerchantCheckbox = By.xpath("(//input[@type='checkbox'])[2]");
	
	//select API
	private By selectAPI = By.xpath("//select[@id='sapi']");

	
	//search button
	private By searchButton= By.xpath("//button[text()='Search']");

	
	//Download Button
	private By downloadButton = By.xpath("(//button[text()='Download'])[2]");
	private By alertDownload= By.xpath("//div[text()='No records to download.']");
	

	
	//download API Report
	private By noRecordFound = By.xpath("//td[text()='No Records Found']");
	private By OK = By.xpath("//button[text()='ok']");
	
	
	//check status
	private By notRecordFount = By.xpath("//td[text()='No Records Found']");
	private By status = By.xpath("(//a[contains(@class,'colorpid lstStatusBtn')])[1]");

	String partialMerchantname = "AbhiMerc";
	String merchant = "AbhiMerchant";
	
	private ActionDriver actionDriver;
	
	
	
	// page factory constructor
	public apiLog_page(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.actionDriver = new ActionDriver(driver);
	}
	
	public void verifyUserIsOnApiReportPage(WebDriver driver) throws InterruptedException {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
        Thread.sleep(1800);
		// scroll to element
        WebElement analytics = w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Analytics']")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", analytics);
      //  analytics.click();
           actionDriver.click(report);
	
		
		//actionDriver.click(report);
	
           Reporter.log("Click on Report Module", true);
           
		actionDriver.click(apiLog);
		
		Reporter.log("Click on Api Log Sub Module", true);
		
		Assert.assertTrue(actionDriver.isDisplayed(apiReport),"user is not on the Api Report Report this page");
		Reporter.log("Verify user is on the Api Report page", true);
	}
	
	
	public void selectAPI(String api) {
		actionDriver.selectByVisibleText(selectAPI, api);
		
		Reporter.log("User select api :- "+api, true);

	}
	
	
	public  void click_SearchButton(WebDriver driver) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
	
		actionDriver.click(searchButton);
		Reporter.log("Click on Search Button", true);
		
	}
	
	
public void click_DownloadButton(WebDriver driver) {
	WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
	
	actionDriver.click(downloadButton);
	Reporter.log("Click on Download Button", true);
	}


public void  verify_AlertMessageForDownload(WebDriver driver){


		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		actionDriver.click(downloadButton);
		Reporter.log("Click on Download Button", true);
		
		String alertMessage = actionDriver.getText(alertDownload);
		Assert.assertEquals(alertMessage, "No records to download.", "Alert message mismatch.");
		Reporter.log("Verify 'No records to download.' this alert message verified succesfully", true);
	
	
		actionDriver.click(OK);
		Reporter.log("Click on OK Button", true);
	
}



public void selectMecrchant(WebDriver driver) throws InterruptedException {
	WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
	Thread.sleep(2000);
	w.until(ExpectedConditions.elementToBeClickable(selectmerchant)).click();

	Reporter.log("click on the select merchant filed", true);
	Thread.sleep(2000);
	w.until(ExpectedConditions.elementToBeClickable(searchmerchant)).sendKeys(partialMerchantname);
	Reporter.log("Enter merchant name in a search merchant functioanlity", true);

	Thread.sleep(2000);

	List<WebElement> suggestions = w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
			By.xpath("//*[@class='select2-results']/ul/li")));

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

public void checkTransactionStatus(WebDriver driver) {
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

       try {
           // Try to get table rows
           List<WebElement> tableRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
               By.xpath("//tbody[@id='filterTransStats']/tr[1]")
           ));

           if (!tableRows.isEmpty()) {
               System.out.println("✅ Table has rows: " + tableRows.size());
               // Optional: Log row data
           } else {
               System.out.println("⚠️ Table is present but no rows found.");
           }

       } catch (Exception e) {
           // If table rows not found, check for 'No records found' message
           try {
               WebElement noRecordElement = driver.findElement(By.xpath("//td[text()='No Records Found']"));
               if (noRecordElement.isDisplayed()) {
                   System.out.println("✅ 'No records found' message is displayed.");
               } else {
                   System.out.println("⚠️ Neither rows nor 'No records found' message is displayed.");
               }
           } catch (NoSuchElementException ex) {
               System.out.println("❌ Neither table rows nor 'No records found' message were found.");
           }}


}

public List<String> verifyHeaders(WebDriver driver) {
	
	List<WebElement> headerElement = driver.findElements(By.xpath("(//table[@class='table table-bordered'])[2]/descendant::th"));
	
	  
	List<String> actualHeaders = new ArrayList<>();

	for (WebElement header : headerElement) {
		actualHeaders.add(header.getText().trim());
	}
	return actualHeaders;
	
}



}
