package com.paysecure_Report.pages;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.paysecure.actiondriver.ActionDriver;
import com.paysecure.base.baseClass;

public class transactionPage {

	// scrolling point of view
	private By analytics = By.xpath("//span[text()='Analytics']");
	private By lastStatus = By.xpath("//th[text()='Last Status']");
	
	// report
	private By report = By.xpath("(//span[text()='Report'])[2]");

	private By transactions = By.xpath("//span[text()='Transactions']");

	// filter transaction thrugh select status
	private By selectStatus = By.xpath("//select[@id='status']");

	@FindBy(xpath = "//select[@id='status']")
	private WebElement SELECT_STATUS;

	private By status = By.xpath("(//a[contains(@class,'colorpid lstStatusBtn')])[1]");

	// search button
	private By searchButton = By.xpath("//button[text()='Search']");

	// filter through select merchant
	private By selectmerchant = By.xpath("//span[text()='Select Merchant']");
	private By searchmerchant = By.xpath("//input[@placeholder='Search Merchant']");
	private By selectSearchmerchantCheckbox = By.xpath("(//input[@type='checkbox'])[2]");

	// filter through select Bank
	private By selectbank = By.xpath("//span[text()='Select Bank']");
	private By searchFieldBank = By.xpath("//input[@class='select2-search__field']");
	private By selectBankFromSearchFiled = By.xpath("//ul[@id='select2-bnk-results']/li");

	// select MID
	private By selectMID = By.xpath("//select[@id='midn']");
	private By date = By.xpath("//div[@id='reportrange']");
	private By dateRanges = By.xpath("//div[@class='ranges']/descendant::li");

	private By dateFrom = By.xpath("(//td[@data-title='r1c2'])[1]");
	private By dateTo = By.xpath("(//td[@data-title='r2c5'])[1]");
	private By notRecordFound = By.xpath("//td[text()='No Records Found']");
	
	@FindBy(xpath="//td[text()='No Records Found']")private WebElement nrf;
	
	
	private By applyButton = By.xpath("//button[text()='Apply']");

	// select timezone
	private By title = By.xpath("(//td[@data-title='r1c2'])[1]");
	private By selectTimeZone = By.xpath("//select[@id='tZone']");
	private By timeZones = By.xpath("//ul[@id='select2-tZone-results']/li");
	private By SearchFiledtimeZones = By.xpath("//input[@role='searchbox']");
	private By TimeZoneRollBox = By.xpath("//select[@id='tZone']");

	// select currency
	private By selectCurrency = By.xpath("//select[@id='merCurr']");
	private By getCurrencyFromCurrencyField = By.xpath("//span[@id='select2-merCurr-container']");
	private By getCurrencyFromTable = By.xpath("//*[@id=\\\"filterTransStats\\\"]/tr[1]/td[9]");

	// Last actions in a Transaction page table
	private By lastAction = By.xpath("(//button[@title='Last Action'])[1]");
	private By transactionID = By.xpath("//span[@id='lAhistory']");

	@FindBy(xpath = "(//button[@title='Last Action'])[1]")
	private WebElement TRANSACTION_ID;

	private By appProperties = By.xpath("(//table[@class='table table-bordered'])[5]/descendant::th");
	private By cancelButtonOnLastAction = By.xpath("//h4[text()='Last Action']/following-sibling::button");

	// Action - Download invoice
	private By downloadInvoice = By.xpath("(//button[@title='Download invoice'])[3]");
	private By generate = By.xpath("//button[text()='Generate']");
	private By errorMessageForEmailImage = By.xpath("//span[text()='Please fill logo Image, company Email.']");
	private By addProduct = By.xpath("//button[@class='btn btn-primary addnewproductbtn']");
	
	private By companyName = By.xpath("//input[@id='companyName']");
	private By companyAddress = By.xpath("//input[@id='companyAddress']");
	private By companyEmail = By.xpath("//input[@id='companyEmail']");
	private By companyLogo = By.xpath("//input[@id='logoImageforUpload']");
	private By resizeImage = By.xpath("//button[text()='Resize']");
	private By tableErrorMessage = By.xpath("//span[@id='tableError']");
	
	private By decsription = By.xpath("//input[@class='form-control description']");
	private By quantity = By.xpath("//input[@class='form-control quantity']");
	private By unitPrice = By.xpath("//input[@class='form-control unitPrice']");
	private By amount = By.xpath("//input[@class='form-control amount']");
	
	@FindBy(xpath="//input[@class='form-control amount']")private WebElement total_amount;
	@FindBy(xpath="//input[@class='form-control quantity']")private WebElement totalQuantity;
	@FindBy(xpath="//input[@class='form-control unitPrice']")private WebElement totalUnitPrice;
	
	

	// History button in Transaction Table
	private By history = By.xpath("(//button[@title='History'])[1]");
	private By statusHistory = By.xpath("//h4[text()='Status History']");
	@FindBy(xpath = "//h4[text()='Status History']")
	private WebElement STATUS_HISTORY;
	private By historyTransaction = By.xpath("//span[@id='phistory']");
	private By cancelButtonOnHistory = By.xpath("//h4[text()='Status History']/following-sibling::button");

	// Remarks button in Transaction table
	private By remarks = By.xpath("(//button[@title='Remarks'])[1]");
	private By remarksTransactionId = By.xpath("//input[@id='Transaction_Id']");

	@FindBy(xpath = "//input[@id='Transaction_Id']")
	private WebElement TRANSACTION_ID_;

	private By sendMessageButton = By.xpath("(//button[@class='btn btn-primary'])[2]");
	private By errorMessage = By.xpath("//div[text()='All input fields must be completed before submitting.']");

	@FindBy(xpath = "//div[text()='All input fields must be completed before submitting.']")
	private WebElement ERROR_MESSAGE;

	private By canCel_Remarks = By.xpath("(//h5[@id='exampleModalLabel'])[2]/following-sibling::button");
	private By message = By.xpath("(//label[text()='Message'])[2]");
	private By OK = By.xpath("//button[text()='OK']");
	private By userType = By.xpath("//select[@id='usertype-select']");

	@FindBy(xpath = "//select[@id='usertype-select']")
	private WebElement USERTYPE;

	private By selectUserType = By.xpath("(//span[@aria-disabled='false'])[2]");

	@FindBy(xpath = "(//span[@aria-disabled='false'])[2]")
	private WebElement SELECT_USERTYPE;

	private By users = By.xpath("//select[@id='recipient-select']");

	private By selectError = By.xpath("//select[@id='recipient-Error']");

	@FindBy(xpath = "//select[@id='recipient-Error']")
	private WebElement SELECT_ERROR;

	private By error_Message = By.xpath("//textarea[@id='message-text']");
	private By successsMessage = By.xpath("//div[text()='Message sent successfully']");

	// nextButton
	private By nextBtn = By.xpath("//a[text()='Next']");

	// transaction table - element

	@FindBy(xpath = "((//th[text()='Transaction ID'])[1]/ancestor::table//td[4])[1]")
	private WebElement Transaction_;
	@FindBy(xpath = "((//th[text()='Transaction ID'])[1]/ancestor::table//td[8])[1]")
	private WebElement Amount_;
	@FindBy(xpath = "((//th[text()='Transaction ID'])[1]/ancestor::table//td[9])[1]")
	private WebElement Currency_;
	@FindBy(xpath = "//td[text()='OrderId']/following-sibling::td[@class='propValue'][1]")
	private WebElement orderID;
	@FindBy(xpath = "//td[text()='Currency']/following-sibling::td[1]")
	private WebElement Curr;
	@FindBy(xpath = "//td[text()='Amt']/following-sibling::td[1]")
	private WebElement amt;

	private By TXNBtn = By.xpath("((//th[text()='Transaction ID'])[1]/ancestor::table//td[4])[1]");
	
	
	//verify if Last  status is created then Paid On is NA
	private By lastStatus_Created=By.xpath("(//th[text()='Transaction ID'])[1]/ancestor::table//td[11]");                                                                                                               
	private By lastStatus_paidOn=By.xpath("(//th[text()='Transaction ID'])[1]/ancestor::table//td[3]"); 
	private By lastStatus_Error=By.xpath("(//th[text()='Transaction ID'])[1]/ancestor::table//td[11]");
	private By lastStatus_Expired=By.xpath("(//th[text()='Transaction ID'])[1]/ancestor::table//td[11]");
	
	private By nextButton=By.xpath("//li[contains(@class,'page-item n')]");
	@FindBy(xpath="//li[contains(@class,'page-item n')]") private WebElement NEXTBUTTON;
	
	
	
	
	
	
	
	
	private WebDriver driver;
	

	// variable directory
	String partialMerchantname = "M";
	String merchant = "mukesh1";
	String bankName = "Delhi";
	String finalBankName="Delhi Lo";

	// variable for change status
	final static String status1 = "Error";

	// variable for SELECT MID
	final static String MID = "dvxv";

	// Date Range -- please select from it
	/*
	 * Today Yesterday Last 7 Days This Week Last 30 Days This Month Last Month
	 * Custom Range
	 */
	final static String dateRange = "Last 7 Days";
//	final static String date_To="10";
//	final static String date_From="20";

//  select - time zone
	final static String time_ZOne = "Asia/Hong_Kong";

	// select - currency
	final static String currency = "USD";

	// Transaction remarks success message
	final static String expectedSuccessMessage = "Message sent successfully";

	private ActionDriver actionDriver;

	// page factory constructor
	public transactionPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.actionDriver = new ActionDriver(driver);
	}

	// code

	public void navigateUptoTransaction(WebDriver driver) throws InterruptedException {
		//WebDriver driver = baseClass.getDriver();
		
		actionDriver.scrollToElement(analytics);
		actionDriver.click(report);
		
	//	System.out.println( driver.getPageSource());

		actionDriver.click(transactions);
		
		 
	//	  System.out.println( driver.getPageSource());
	

	}

	public void filterTransactionThroughSelectStatus(String SelectStatus) throws InterruptedException {

		actionDriver.selectByVisibleText(selectStatus, SelectStatus);

	}
	
	public void selectStatus(String Select_Status) {
		actionDriver.selectByVisibleText(selectStatus, Select_Status);
	}

	public void clickOnSearchButton(WebDriver driver) throws InterruptedException {
		actionDriver.click(searchButton);
		Reporter.log("User click on Search Buttton", true);
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



	public void selectDashboardusinAllMerchant(WebDriver driver) throws InterruptedException {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		Thread.sleep(2000);
		actionDriver.click(selectmerchant);
		

		Reporter.log("click on the select merchant filed", true);
		Thread.sleep(2000);
		w.until(ExpectedConditions.elementToBeClickable(searchmerchant)).sendKeys(partialMerchantname);
		Reporter.log("Enter merchant name in a search merchant functioanlity", true);

		Thread.sleep(2000);

		List<WebElement> suggestions = w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//*[text()='Select Merchant']/../following-sibling::ul/li[not(@class)]")));

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

	public void selectDashboardusinAllBank(WebDriver driver) throws InterruptedException {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		actionDriver.click(selectbank);

		org.testng.Reporter.log("click on Select Bank", true);

		// actionDriver.sendKeysWithActions(searchFieldBank,bankname);

		actionDriver.enterText(searchFieldBank, bankName);

		Reporter.log("Enter Merchnat bank name in a Search Bank name text field", true);
		List<WebElement> suggestions = w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//ul[@id='select2-bnk-results']/li")));

		for (WebElement s : suggestions) {
			if (finalBankName.equalsIgnoreCase(s.getText().trim())) {
				Thread.sleep(1000);
				s.click();
				Reporter.log("select merchant name :- " +bankName, true);
				break;

			}
		}
		Reporter.log("select Bank from all Bank", true);

		Reporter.log("select Merchant Bank from all Merchant bank", true);
	}

	public void selectMID() throws InterruptedException {
		Thread.sleep(2000);
		actionDriver.selectByVisibleText(selectMID, MID);

		Reporter.log("Select MID from MID dropdown", true);

	}

	public void selectDateRange(WebDriver driver, String date_Range) throws InterruptedException {

		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		Thread.sleep(2000);
		actionDriver.click(date);
		// utility.scrollToElement(driver, applyButton);
		Reporter.log("Click on the Date range", true);
		List<WebElement> suggestions = w.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='ranges']/descendant::li")));

		for (WebElement s : suggestions) {
			if (date_Range.equalsIgnoreCase(s.getText().trim())) {
				Thread.sleep(1000);
				s.click();
				Reporter.log("select date range :- " + date_Range, true);
				break;

			}
		}
	}

	public void selectDate_To(WebDriver driver) throws InterruptedException {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
//		actionDriver.click(dateTo);
//		actionDriver.click(dateFrom);
//		
//				w.until(ExpectedConditions.elementToBeClickable(dateTo)).click();
//				w.until(ExpectedConditions.elementToBeClickable(dateFrom)).click();

		Thread.sleep(2000);
		actionDriver.clickUsingJS(dateFrom);
		Reporter.log("select date range :- " + dateFrom, true);
		Thread.sleep(4000);
		actionDriver.clickUsingJS(dateTo);
		Reporter.log("select date range :- " + dateTo, true);
		Thread.sleep(4000);
//				Actions a=new Actions(driver);
//				a.click(ERROR_MESSAGE)
//				a.click(dateTo).build().perform();

	}

	public void clickOnApplyButton(WebDriver driver) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		// w.until(ExpectedConditions.elementToBeClickable(applyButton)).click();
		actionDriver.clickUsingJS(applyButton);
	}

	public void selectTimeZone(WebDriver driver) throws InterruptedException {

		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		Thread.sleep(4000);

		actionDriver.selectByVisibleText(selectTimeZone, time_ZOne);
		Thread.sleep(4000);
		Reporter.log("Select Time Zone :- " + time_ZOne, true);

		WebElement tm1 = w.until(ExpectedConditions.presenceOfElementLocated(By.id("select2-tZone-container")));

		String tm = tm1.getAttribute("title");

		// String tm = TimeZoneRollBox.getAttribute("role");
		Assert.assertEquals(time_ZOne, tm, "if this time zone is not match then our TC is should be fail");
		Thread.sleep(4000);
	}

	public void selectCurrency(WebDriver driver) throws InterruptedException {

		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		Thread.sleep(4000);

		actionDriver.selectByVisibleText(selectCurrency, currency);

		Thread.sleep(4000);
		Reporter.log("Select currency from currency dropdown :- " + currency, true);

		WebElement tm1 = w.until(ExpectedConditions.presenceOfElementLocated(By.id("select2-merCurr-container")));

		String tm = tm1.getAttribute("title");

		Assert.assertEquals(currency, tm, "if this time zone is not match then our TC is should be fail");
		Thread.sleep(4000);
		Reporter.log("Assertion passed: selected currency matches", true);



	}

	public void testSelectFilters(WebDriver driver) throws InterruptedException {
		
	}

	public void lastActionOnTransactionTable(WebDriver driver) throws InterruptedException {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));

		actionDriver.click(lastAction);

		Reporter.log("Click on the Last Action Button in transaction Table", true);

		Thread.sleep(2500);
		Assert.assertTrue(actionDriver.isDisplayed(transactionID));

		Reporter.log("Verified transaction id test is present on Last action", true);

		WebElement transactionId = w.until(ExpectedConditions.visibilityOf(TRANSACTION_ID));
		if (actionDriver.isDisplayed(transactionID)) {

			System.out.println("Transaction ID is displays on Last Action");

		}
		Thread.sleep(2000);
		List<WebElement> appPropertys = w.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tbody[@id='lastActionList']/tr")));
		for (WebElement app : appPropertys) {
			System.out.println(app.getText());
		}

		actionDriver.click(cancelButtonOnLastAction);

		Reporter.log("Click on the Cancel  Button", true);

	}

	public void historyOnTransaction(WebDriver driver) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));

		actionDriver.click(history);

		Reporter.log("Click on the History Button in transaction Table", true);

		WebElement status_History = w.until(ExpectedConditions.visibilityOf(STATUS_HISTORY));
		Assert.assertTrue(actionDriver.isDisplayed(statusHistory), "element is not displays");

		// check headers in history tab

		// expected headers in History tab
		// List<String> expectedValues = Arrays.asList("DATE", "STATUS", "UPDATED BY");

		// List<WebElement> actualValues = w.until(
		// ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//th[@class='text-center
		// tdwith11']")));

		// Assert.assertEquals(actualValues, expectedValues, "Mismatch in element list
		// values");

		// w.until(ExpectedConditions.elementToBeClickable(cancelButtonOnHistory)).click();

		actionDriver.click(cancelButtonOnHistory);

		Reporter.log("Click on the Cancel  Button", true);

	}

	public void remarksOnTransactiontable(WebDriver driver) {

		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));

		actionDriver.click(remarks);

		Reporter.log("Click on the Remarks Button in transaction Table", true);

		WebElement remarks_TransactionID = w.until(ExpectedConditions.visibilityOf(TRANSACTION_ID_));

		// Assert.assertTrue(!remarks_TransactionID.isEnabled(), "this field is
		// enable");
		Assert.assertFalse(!actionDriver.isDisplayed(remarksTransactionId));

		Reporter.log("Veirifies Transaction ID on Remarks page is disabled", true);

		actionDriver.click(canCel_Remarks);

		Reporter.log("Click on the Cancel  Button", true);

	}

	public void verifyErrorInRemarksField(WebDriver driver) {

		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));

		actionDriver.click(remarks);

		Reporter.log("Click on the Remarks Button in transaction Table", true);
		// utility.scrollToElement(driver, message);
		actionDriver.scrollToElement(message);

		actionDriver.click(sendMessageButton);
		Reporter.log("Click on the send message Button", true);

		String actualErrorMessage = "All input fields must be completed before submitting.";

		WebElement expectedErrormessage = w.until(ExpectedConditions.visibilityOf(ERROR_MESSAGE));

		Assert.assertEquals(actualErrorMessage, expectedErrormessage.getText(), "Error message is not same as Figma");
		Reporter.log("Verify error message ", true);

		actionDriver.clickUsingJS(canCel_Remarks);

		actionDriver.click(OK);

		Reporter.log("Click on the OK  Button", true);
	}

	public void verifyElementsInUserType(WebDriver driver) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// Wait for Remarks button and click
		// wait.until(ExpectedConditions.elementToBeClickable(remarks)).click();

		actionDriver.click(remarks);
		Reporter.log("Click on the Remarks Button in transaction Table", true);

		// Scroll to the message element (custom utility)

		actionDriver.scrollToElement(message);

		// Wait until dropdown is present and visible
		wait.until(ExpectedConditions.visibilityOf(USERTYPE));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='usertype-select']"))); // Replace
																												// with
																									// if
																												// needed

		// Wrap the dropdown
		Select dropdown = new Select(USERTYPE);

		// Wait until options are loaded (dropdown must have more than 1 value)
		wait.until(driver1 -> dropdown.getOptions().size() > 1);

		// Expected values
		String[] expectedArray = { "Select a Users", "ADMIN", "FULLADMIN", "SUPERADMIN", "REPORTADMIN", "ACCOUNTADMIN",
				"MERCHANT", "AGENT" };
		List<String> expectedValues = Arrays.asList(expectedArray);

		// Get actual dropdown values
		List<String> actualValues = new ArrayList<>();
		for (WebElement option : dropdown.getOptions()) {
			actualValues.add(option.getText().trim());
		}

		// Log values
		System.out.println("Expected: " + expectedValues);
		System.out.println("Actual:   " + actualValues);

		// Assertions
		Assert.assertEquals(actualValues.size(), expectedValues.size(), "Mismatch in number of dropdown options.");
		for (String expected : expectedValues) {
			Assert.assertTrue(actualValues.contains(expected), "Missing dropdown value: " + expected);
		}
		System.out.println("✅ User type Dropdown values validated successfully.");

		// Wait and click cancel remarks button
		// wait.until(ExpectedConditions.elementToBeClickable(canCel_Remarks)).click();
		actionDriver.click(canCel_Remarks);
		Reporter.log("Click on the Cancel Button", true);

	}

	public void verifyUsers(WebDriver driver) throws InterruptedException {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));

		// w.until(ExpectedConditions.elementToBeClickable(remarks)).click();
		actionDriver.click(remarks);
		Reporter.log("Click on the Remarks Button in transaction Table", true);
		// utility.scrollToElement(driver, message);
		actionDriver.scrollToElement(message);
		Thread.sleep(3500);

		Assert.assertFalse(!SELECT_USERTYPE.isEnabled(), "select user type field is enabled");
		Reporter.log("when user click on Remarks then at initial stage select user type field is disabled", true);
		Thread.sleep(3500);
		actionDriver.selectByVisibleText(userType, "ADMIN");
		// utility.selectByVisibleText(userType, "ADMIN");

		Reporter.log("User select values from user type dropdown", true);
		Thread.sleep(3500);
		Assert.assertTrue(SELECT_USERTYPE.isEnabled(), "select user type field is enabled");

		Reporter.log("After selecting user type values then Select user type field is enabled", true);

		// utility.selectByVisibleText(users, "harshitaadmin");
		actionDriver.selectByVisibleText(users, "harshitaadmin");

	}

	public void selectError(WebDriver driver) {
		// utility.selectByVisibleText(selectError, "Expired card");
		actionDriver.selectByVisibleText(selectError, "Expired card");
		Reporter.log("User select a error", true);
	}

	public void checkAll_SelectError(WebDriver driver) throws InterruptedException {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));

		// w.until(ExpectedConditions.elementToBeClickable(remarks)).click();
		// Reporter.log("Click on the Remarks Button in transaction Table", true);
		actionDriver.scrollToElement(message);
		// utility.scrollToElement(driver, message);
		Select dropdown = new Select(SELECT_ERROR);

//	        List<WebElement> opt = dropdown.getOptions();
//	        Thread.sleep(2500);
//	        for(WebElement o:opt) {
//	        	System.out.println(o.getText());
//	        }

		// Using array and converting to list
		String[] expectedArray = { "Select a Error", "Invalid Phone Number", "Bank Decline", "Blocked Payment",
				"Insufficient Funds", "Expired card", "Payment gateway errors", "Security concerns", "Unknown errors" };
		List<String> expectedValues = Arrays.asList(expectedArray);

		// Getting actual values
		List<String> actualValues = new ArrayList<>();
		for (WebElement option : dropdown.getOptions()) {
			actualValues.add(option.getText().trim());
		}

		// Logging
		System.out.println("Expected: " + expectedValues);
		System.out.println("Actual:   " + actualValues);

		// Assertions
		Assert.assertEquals(actualValues.size(), expectedValues.size(), "Mismatch in number of dropdown options.");
		for (String expected : expectedValues) {
			Assert.assertTrue(actualValues.contains(expected), "Missing dropdown value: " + expected);
		}

	}

	public void enterMessage(WebDriver driver) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));

		w.until(ExpectedConditions.elementToBeClickable(error_Message)).sendKeys("Your card hasb been expired");

		actionDriver.sendKeysWithActions(error_Message, "Your card hasb been expired");

		Reporter.log("User enter Error message is Message Field", true);
	}

	public void clickOnSubmitButton(WebDriver driver) {

		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		// w.until(ExpectedConditions.elementToBeClickable(sendMessageButton)).click();
		actionDriver.click(sendMessageButton);
		Reporter.log("User click on send message button", true);
	}

	public void verifySuccessmessage(WebDriver driver) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement messageElement = w.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Message sent successfully']")));

		String actualMessage = messageElement.getText().trim();
		System.out.println("Displayed Message: " + actualMessage);

		// Assertion
		Assert.assertEquals(actualMessage, expectedSuccessMessage, "❌ Message mismatch");

		Reporter.log("Message sent successfully is verified", true);

		w.until(ExpectedConditions.elementToBeClickable(OK)).click();

		Reporter.log("Click on OK button on success message", true);

	}

//	public void validateTransactiondataWithCSV(WebDriver driver) {
//		String csvPath = "C:\\Users\\LENOVO\\eclipse-workspace\\paySecure\\src\\test\\resources\\csv\\Transaction Report.csv";
//		List<Map<String, String>> csvData = CSVUtils.readCSV(csvPath);
//
//		for (int i = 0; i < csvData.size(); i++) {
//			Map<String, String> row = csvData.get(i);
//			String expectedTxnId = row.get("Transaction ID");
//			String expectedAmount = row.get("Amount");
//			String expectedStatus = row.get("Last Status");
//
//			// Example XPath — modify per your actual table row/column locators
//			String xpathBase = "//tbody[@id='filterTransStats']/tr";
//
//			String uiTxnId = driver.findElement(By.xpath(xpathBase + "/td[4]")).getText().trim();
//			String uiAmount = driver.findElement(By.xpath(xpathBase + "/td[8]")).getText().trim();
//			String uiStatus = driver.findElement(By.xpath(xpathBase + "/td[10]")).getText().trim();
//
//			System.out.println(uiTxnId);
//			System.out.println(expectedTxnId);
//
//			System.out.println(uiAmount);
//			System.out.println(expectedAmount);
//
//			// Assertions
//			Assert.assertEquals(uiTxnId, expectedTxnId, "Mismatch in Transaction ID at row: ");
//			Assert.assertEquals(uiAmount, expectedAmount, "Mismatch in Amount at row: ");
//			Assert.assertEquals(uiStatus.toUpperCase(), expectedStatus.toUpperCase(),
//					"Mismatch in Status at row: " + (i + 2));
//		}

	public void iterateAllTablePages(WebDriver driver) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    int totalRowCount = 0;

	    while (true) {
	        // Wait and fetch all rows on current page
	        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
	                By.xpath("(//table[@class='table table-bordered'])[2]/tbody[2]/tr")));

	        int currentPageRowCount = rows.size();
	        totalRowCount += currentPageRowCount;

	        System.out.println("Rows on this page: " + currentPageRowCount);

	        // Loop through rows
	        for (WebElement row : rows) {
	            System.out.println("Row Data: " + row.getText());
	        }

	        // Check if 'Next' button is disabled
	        WebElement nextButtonContainer = driver.findElement(By.xpath("//li[contains(@class,'page-item next')]"));
	        String classAttr = nextButtonContainer.getAttribute("class");

	        if (classAttr.contains("disabled")) {
	            System.out.println("Reached last page.");
	            break;
	        }

	        actionDriver.clickUsingJS(nextBtn); // Ensure nextBtn is globally defined and accessible
	    }

	    // ✅ Final total row count print
	    System.out.println("✅ Total rows across all pages: " + totalRowCount);
	}


	public void iterateAllTablePages1(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 int totalRowCount = 0;
		while (true) {
			// Wait and fetch all rows on current page
			List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
					By.xpath("(//table[@class='table table-bordered'])[2]/tbody[2]/tr")));

			   int currentPageRowCount = rows.size();
		        totalRowCount += currentPageRowCount;

		        System.out.println("Rows on this page: " + currentPageRowCount);

			// Loop through rows (you can extract/validate each row as needed)
			for (WebElement row : rows) {
				String rowText = row.getText();
				System.out.println("Row Data: " + rowText);
			}

			// Check if Next button is disabled
			WebElement nextButtonContainer = driver.findElement(By.xpath("//li[contains(@class,'page-item next')]"));

			String classAttr = nextButtonContainer.getAttribute("class");

			if (classAttr.contains("page-item next disabled")) {
				System.out.println("Reached last page.");
				break;
			}

			actionDriver.clickUsingJS(nextBtn);

			// wait for some indicator that new page has loaded
		//	wait.until(ExpectedConditions.stalenessOf(rows.get(0)));

		}
		 System.out.println("🚀 Total rows across all pages: " + totalRowCount);
	}

	public void verifyDownloadInvoice(String Status) {
		Select s = new Select(SELECT_STATUS);
		s.selectByVisibleText( Status);
		Reporter.log("Verify user select status like "+Status, true);

		String st = s.getFirstSelectedOption().getText();

		if (st == "Paid") {
			System.out.println("Verify user selected status like :- " + st);
			Reporter.log("Verify status is selected ", true);
		}

		actionDriver.click(downloadInvoice);
		Reporter.log("User click on Download Invoice button", true);
	}
	
	public void verifyErrorInDownloadInvoice_LogoAndComapanyEmail() {
		
		actionDriver.click(generate);
		Reporter.log("User click on generate button", true);
		
		actionDriver.scrollToElement(addProduct);
		
		String text = actionDriver.getText(errorMessageForEmailImage);
		String act="Please fill logo Image, company Email.";
		
		Assert.assertEquals(text, act);
		Reporter.log("Verify error message for company Logo and Email", true);
		
	}
	
	public void verifyErrorInDownloadInvoice_addProduct() throws InterruptedException {
		
		actionDriver.enterText(companyName, "Paysecure");
		Reporter.log("User Enter Company name", true);
		actionDriver.enterText(companyEmail, "paysecure@gmail.com");
		Reporter.log("User Enter Company Email address", true);
		actionDriver.enterText(companyAddress, "ganga Heights, jaipur");
		Reporter.log("User Enter Company address", true);
		actionDriver.uploadFile(companyLogo,"C:\\Users\\LENOVO\\Downloads\\yyy123\\pay\\src\\main\\resources\\Logo.jpg");
		Reporter.log("User Enter Company Logo", true);
		
		Thread.sleep(1500);
		
		if(actionDriver.isDisplayed(resizeImage)) {
		actionDriver.click(resizeImage);
		}
		
		
		actionDriver.click(addProduct);
		Reporter.log("User click on add product button", true);
		
		
		actionDriver.click(generate);
		Reporter.log("User click on generate button", true);
		

		String text = actionDriver.getText(tableErrorMessage);
		String act="Please fill all fields correctly in row 1. Quantity and Unit Price must be greater than 0.";
		
		Assert.assertEquals(text, act);
		Reporter.log("Verify error message for table product", true);
	}
	
	
	public void verifyTotalAmountInDownloadInvoice(int qty, int unitPrice) {

	    // Enter text and values
	    actionDriver.enterText(decsription, "paysecure");
	    totalQuantity.sendKeys(String.valueOf(qty));
	    totalUnitPrice.sendKeys(String.valueOf(unitPrice));

	    String totalAmountText = actionDriver.getText(amount).trim();
	    Reporter.log("Fetched amount text: '" + totalAmountText + "'", true);

	    if (totalAmountText.isEmpty()) {
	        Reporter.log("❌ totalAmountText is empty! Skipping parsing.", true);
	        throw new IllegalStateException("Total amount not visible or not populated on UI");
	    }

	    String cleaned = totalAmountText.replaceAll("[^\\d.]", ""); // Remove ₹ or commas

	    double actualAmount = 0;
	    try {
	        actualAmount = Double.parseDouble(cleaned);
	    } catch (NumberFormatException e) {
	        Reporter.log("❌ Parsing failed for amount: '" + cleaned + "'", true);
	        throw e;
	    }


	    // Convert to double
	 //   double actualAmount = Double.parseDouble(totalAmountText);

	    // Calculate expected amount
	    double expectedAmount = qty * unitPrice;

	    // Compare actual vs expected with decimal tolerance
	    if (Math.abs(actualAmount - expectedAmount) < 0.01) {
	        String passMsg = "✅ PASS: Amount matched. Expected: " + expectedAmount + ", Actual: " + actualAmount;
	        System.out.println(passMsg);
	        Reporter.log(passMsg, true); // Logs to console and HTML report
	    } else {
	        String failMsg = "❌ FAIL: Amount mismatch! Expected: " + expectedAmount + ", Actual: " + actualAmount;
	        System.out.println(failMsg);
	        Reporter.log(failMsg, true); // Logs to console and HTML report
	    }
	}
	
	public static String Trn___Id ="";
	public static String transactionStatus = "";
	private By paid = By.xpath("(//a[text()='PAID'])[1]");
	private By t_ID = By.xpath("(//div[@id='viewRisk1']/descendant::span[@class='json-string'])[1]");
	private By TRN_Status=By.xpath("(//div[@id='viewRisk1']/descendant::span[@class='json-string'])[3]");
	public void verifyPaidTransaction(WebDriver driver) {

		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(15));
		boolean actionClicked = false;

		List<WebElement> rows = w.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tbody[@id='filterTransStats']/tr")));

		for (WebElement r : rows) {
			// ✅ Get Last Status from the 10th column (relative to current row)
			String statusText = r.findElement(By.xpath("./td[11]")).getText().trim();

			if (statusText.equalsIgnoreCase("PAID")) {
				// ✅ Get Purchase ID from 3rd column (Transaction ID)
				Trn___Id= r.findElement(By.xpath("./td[4]")).getText().trim();
				System.out.println("✅ Found Trn___Id:    " +   Trn___Id);

				transactionStatus = r.findElement(By.xpath("./td[11]")).getText().trim();
				System.out.println("✅ Found transaction Status:- " + transactionStatus);

				// ✅ Click History button (assuming 2nd button in 1st column)
				
				System.out.println("🛠 Clicking History button for clickedTxn___ID: " + Trn___Id);
				actionDriver.click(paid);
				Reporter.log("User Click on Paid", true);

				actionClicked = true;
				break;
			}
		}

	}

	public static String purchaseId;
	public String extractPurchaseIdFromSpanFormat(WebDriver driver) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement pre = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("pre")));

	    List<WebElement> keys = pre.findElements(By.cssSelector("span.json-key"));

	    for (WebElement key : keys) {
	        if (key.getText().trim().equals("purchaseId")) {
	            WebElement purchaseIdSpan = key.findElement(By.xpath("following-sibling::span[@class='json-string'][1]"));
	            purchaseId = purchaseIdSpan.getText().replaceAll("\"", "").trim();
	            System.out.println("✅ Purchase ID: " + purchaseId);
	            return purchaseId;
	        }
	    }
	    return "Not found";
	}
	
	
	public static String jsonTransactionStatus;
	String keyName ="transactionStatus";

	public String getJsonSpanValue(WebDriver driver) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement preElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("pre")));
	    List<WebElement> keys = preElement.findElements(By.cssSelector("span.json-key"));

	    for (WebElement key : keys) {
	        if (key.getText().trim().equals(keyName)) {
	            try {
	                WebElement valueSpan = key.findElement(By.xpath("following-sibling::span[@class='json-string'][1]"));
	                return valueSpan.getText().replaceAll("\"", "").trim();
	            } catch (Exception e) {
	                System.out.println("❌ Could not find value for key: " +keyName);
	                return "Not found";
	            }
	        }
	    }

	    System.out.println("❌ Key not found: " +keyName);
	    return "Not found";
	}

	public static String jsonTransactionStatus1;
	public void verifyStatus_Paid(WebDriver driver) {
	    jsonTransactionStatus = getJsonSpanValue(driver);

	    System.out.println("🔍 Expected: " + transactionStatus);
	    System.out.println("🔍 Actual from JSON: " + jsonTransactionStatus);

	    Assert.assertEquals(jsonTransactionStatus.toLowerCase(), transactionStatus, "Mismatch in transaction status");
	}
	
	public void verifyTRNIDIn_Paid() {
		
		Assert.assertEquals(Trn___Id,purchaseId, "Mismatch in transaction ID");
	

	}
	
	///Last status - created 
	public void verifyLastStatus_Created() {
		
		if(actionDriver.isDisplayed(lastStatus_Created) || actionDriver.isDisplayed(lastStatus_Error) ||  actionDriver.isDisplayed(lastStatus_Expired) ) {
			if(actionDriver.isDisplayed(lastStatus_paidOn)) {
				System.err.println("Paid On  is should be 'NA' ");
				
			}
		}else {
			System.out.println("Last status is not appear on this page");
		}
		
		
		
	}
	
	
	public void verifyPaidOn_Created_Error_Expired(WebDriver driver) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    SoftAssert softAssert = new SoftAssert();

	    boolean hasNextPage = true;
	    int page = 1;

	    while (hasNextPage) {
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@id='filterTransStats']/descendant::tr")));

	        List<WebElement> rows = driver.findElements(By.xpath("//tbody[@id='filterTransStats']/descendant::tr"));

	        for (int i = 0; i < rows.size(); i++) {
	            WebElement row = rows.get(i);
	            String paidOnText = row.findElement(By.xpath("./td[3]")).getText().trim();
	            String lastStatusText = row.findElement(By.xpath("./td[11]")).getText().trim();

	            System.out.println("Page " + page + " | Row " + (i + 1) + ": Last Status = " + lastStatusText + " | Paid On = " + paidOnText);

	            if (lastStatusText.equalsIgnoreCase("created") ||
	                lastStatusText.equalsIgnoreCase("error") ||
	                lastStatusText.equalsIgnoreCase("expired")) {

	                softAssert.assertEquals(
	                    paidOnText,
	                    "NA",
	                    "❌ Page " + page + ", Row " + (i + 1) + ": 'Paid On' should be 'NA' when 'Last Status' is '" + lastStatusText + "'"
	                );
	            }
	        }

	        try {
	         //   WebElement nextButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Next']")));
	            actionDriver.scrollToElement(nextButton);

	            if (!actionDriver.isEnabled(nextButton) ||NEXTBUTTON.getAttribute("class").contains("page-item next disabled")) {
	                hasNextPage = false;
	            } else {
	                actionDriver.click(nextButton);
	                wait.until(ExpectedConditions.stalenessOf(rows.get(0)));
	                page++;
	            }
	        } catch (Exception e) {
	            hasNextPage = false;
	        }
	    }

	    softAssert.assertAll();
	}
	
//	public void verifyPaidOn(WebDriver driver) {
//	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//	    SoftAssert softAssert = new SoftAssert();
//
//	    boolean hasNextPage = true;
//	    int page = 1;
//
//	    while (hasNextPage) {
//	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@id='filterTransStats']/tr")));
//
//	        List<WebElement> rows = driver.findElements(By.xpath("//tbody[@id='filterTransStats']/tr"));
//
//	        for (int i = 0; i < rows.size(); i++) {
//	            WebElement row = rows.get(i);
//	            String paidOnText = row.findElement(By.xpath("./td[3]")).getText().trim();
//	            String lastStatusText = row.findElement(By.xpath("./td[11]")).getText().trim();
//
//	            System.out.println("Page " + page + " | Row " + (i + 1) + ": Last Status = " + lastStatusText + " | Paid On = " + paidOnText);
//
//	            if (lastStatusText.equalsIgnoreCase("created") ||
//	                lastStatusText.equalsIgnoreCase("error") ||
//	                lastStatusText.equalsIgnoreCase("expired")) {
//
//	                softAssert.assertEquals(
//	                    paidOnText,
//	                    "NA",
//	                    "❌ Page " + page + ", Row " + (i + 1) + ": 'Paid On' should be 'NA' when 'Last Status' is '" + lastStatusText + "'"
//	                );
//	            }
//	        }
//
//	        // ✅ Check for pagination
//	   List<WebElement> nextButtons = driver.findElements(By.xpath("//a[text()='Next']"));
//	   if (nextButtons.isEmpty()) {
//		    hasNextPage = false;
//		} else {
//		    WebElement nextButton = nextButtons.get(0);
//		    if (!nextButton.isEnabled() || nextButton.getAttribute("class").contains("page-item next disabled")) {
//		        hasNextPage = false;
//		    } else {
//		        nextButton.click();
//		        wait.until(ExpectedConditions.stalenessOf(rows.get(0)));
//		    }
//		}
//	    }
//
//	    softAssert.assertAll();
//	}

	
	
	public void verifyPaidFor_Paid_Refunded(WebDriver driver) throws TimeoutException {
//	    try {
//	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
//	         wait.until(ExpectedConditions.visibilityOf(nrf)).isDisplayed();
//	    } catch (NoSuchElementException e) {
//	        return;
//	    }
	
//		   // Wait for table to load
//		if (actionDriver.isDisplayed(notRecordFound)) {
//		    System.err.println("❌ No records found.");
//		    return; // Exit early since there's nothing to process
//		} else {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		    SoftAssert softAssert = new SoftAssert();
		    boolean hasNextPage = true;

		    while (hasNextPage) {

		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@id='filterTransStats']/tr")));
		        List<WebElement> rows = driver.findElements(By.xpath("//tbody[@id='filterTransStats']/tr"));

		        for (int i = 0; i < rows.size(); i++) {
		            WebElement row = rows.get(i);

		            String createdText = row.findElement(By.xpath("./td[2]")).getText().trim();
		            String paidOnText = row.findElement(By.xpath("./td[3]")).getText().trim();
		            String transactionId = row.findElement(By.xpath("./td[4]")).getText().trim();

		            if (!paidOnText.equalsIgnoreCase("NA")) {
		                try {
		                    LocalDateTime createdDate = LocalDateTime.parse(createdText, formatter);
		                    LocalDateTime paidOnDate = LocalDateTime.parse(paidOnText, formatter);

		                    if (paidOnDate.isBefore(createdDate)) {
		                        softAssert.fail("❌ Row " + (i + 1) + " - Transaction ID: " + transactionId +
		                            " ➤ 'PAID ON' (" + paidOnDate + ") is BEFORE 'DATE' (" + createdDate + ")");
		                    } else {
		                        System.out.println("✅ Row " + (i + 1) + " ➤ PAID ON is valid.");
		                    }
		                } catch (Exception e) {
		                    softAssert.fail("❌ Row " + (i + 1) + " ➤ Date parsing issue: " + e.getMessage());
		                }
		            }
		        }

		        // Pagination handling
		        try {
		            if (actionDriver.isDisplayed(nextButton)) {
		                String classAttr = NEXTBUTTON.getAttribute("class");

		                if (classAttr.contains("page-item next disabled")) {
		                    hasNextPage = false;
		                } else {
		                    actionDriver.scrollToElement(nextButton);
		                    actionDriver.click(nextButton);
		                    wait.until(ExpectedConditions.stalenessOf(rows.get(0)));
		                }
		            } else {
		                hasNextPage = false;
		            }

		        } catch (NoSuchElementException e) {
		            hasNextPage = false;
		        }
		    }

		    softAssert.assertAll();
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	}
