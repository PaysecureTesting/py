package com.paysecure_Report.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;


import com.paysecure.actiondriver.ActionDriver;

public class transaction_Log_page {
	// Navigate upto transaction log
	private By analytics = By.xpath("//span[text()='Analytics']");
	private By report = By.xpath("(//span[text()='Report'])[2]");
	private By Transaction_Log = By.xpath("//span[text()='Transaction Log']");
	private By Transaction_Log1 = By.xpath("//span[text()='Transaction Log']");

	// search transaction log through purchase ID
	private By searchFiled_purchaseID = By.xpath("//input[@id='purchaseId']");
	private By purchaseID = By.xpath("(//a[contains(@onclick,'getBank')])[1]");
	private By closeButton = By.xpath("(//button[@class='btn-close'])[5]");

//verify json
	private By json = By.xpath("(//a[contains(@onclick,'showTText')])[1]");

	@FindBy(xpath = "(//a[contains(@onclick,'showTText')])[1]")
	private WebElement JSON;

	// search button
	private By searchButton = By.xpath("//button[text()='Search']");

	// Download Button
	private By downloadButton = By.xpath("//button[@title='Download']");

	// verify transaction details
	private By purchaseInfo = By.xpath("//span[@id='purchaseInfo']");

	private ActionDriver actionDriver;

	// page factory constructor
	public transaction_Log_page(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.actionDriver = new ActionDriver(driver);
	}

	// navigate upto transaction log

	public void verifyUserIsOnTransactionLogPage(WebDriver driver) throws InterruptedException {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		Thread.sleep(1800);
		// scroll to element
		actionDriver.scrollToElement(analytics);

		actionDriver.click(report);
		Reporter.log("Click on Report Module", true);

		actionDriver.click(Transaction_Log);

		Reporter.log("Click on Transaction Log Sub Module", true);

		Assert.assertTrue(actionDriver.isDisplayed(Transaction_Log1), "user is not on the Api Report Report this page");
		Reporter.log("Verify user is on the Transaction Log page", true);
	}

	public void searchThroughPurchaseID(WebDriver driver) throws InterruptedException {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		Thread.sleep(2800);

		String PID = actionDriver.getText(purchaseID);

		w.until(ExpectedConditions.elementToBeClickable(searchFiled_purchaseID)).sendKeys(PID);
		Reporter.log("User enter purchase ID in Purchase ID text Field", true);

	}

	public void searchTransactionLog(WebDriver driver) throws InterruptedException {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		Thread.sleep(1800);

		actionDriver.click(searchButton);
		Reporter.log("Click on Search Button", true);

	}

	public void click_downloadButton(WebDriver driver) throws InterruptedException {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		Thread.sleep(1800);

		actionDriver.click(downloadButton);
		Reporter.log("Click on Download Button", true);

	}

	public void verifyTransactionPurchaseDetails(WebDriver driver) throws InterruptedException {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		Thread.sleep(1800);

		String PID = actionDriver.getText(purchaseID);

		System.out.println(PID);

		actionDriver.click(purchaseID);
		Reporter.log("Click on Purchase ID Button", true);

		Thread.sleep(2800);
		String purchase_ID = actionDriver.getText(purchaseInfo);
		Assert.assertEquals(PID, purchase_ID,
				"If purchase ID in Info page is not same then OUR TC is should be failed");
		Reporter.log("Verified same purchase ID is occure Transactional details page", true);

		actionDriver.click(closeButton);
		Reporter.log("Click on close Button in transactional Details page", true);

	}

	public void verifyPurchaseID(WebDriver driver) {
		String PID = actionDriver.getText(purchaseID);

		System.out.println(PID);

		if (JSON.isEnabled()) {
			System.out.println("Enable for clicking");
			actionDriver.click(json);
		}

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Wait for the <pre> element that contains the JSON
		WebElement preElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("pre")));

		// Get the full text from the <pre> tag
		String fullJsonText = preElement.getText();

		// Optional: Print full JSON to verify
		System.out.println("Full JSON:\n" + fullJsonText);

		// Extract the purchaseId using simple string operations or regex
		String purchaseId = extractPurchaseId(fullJsonText);

		System.out.println("✅ Extracted purchaseId: " + purchaseId);

		// Optionally validate
		Assert.assertEquals(purchaseId, PID);
		Reporter.log("Verified both purchase ID is Matches", true);
	}

	private String extractPurchaseId(String jsonText) {
		// Use regex to extract value of "purchaseId"
		String regex = "\"purchaseId\"\\s*:\\s*\"([^\"]+)\"";
		java.util.regex.Matcher matcher = java.util.regex.Pattern.compile(regex).matcher(jsonText);
		if (matcher.find()) {
			return matcher.group(1); // group(1) is the value inside quotes
		}
		return "Not found";
	}
	
	
	
	public List<String> verifyHeaders(WebDriver driver) {
		
		List<WebElement> headerElement = driver.findElements(By.xpath("//th[@scope='col']"));
		
		  
		List<String> actualHeaders = new ArrayList<>();

		for (WebElement header : headerElement) {
			actualHeaders.add(header.getText().trim());
		}
		return actualHeaders;
		
	}
	
	
	
	
	
	
	
	

}
