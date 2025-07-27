package com.paysecure_Report.pages;

import java.time.Duration;
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

public class email_transactionPage {

	// scrolling point of view
	private By analytics = By.xpath("//span[text()='Analytics']");

	// report
	private By report = By.xpath("(//span[text()='Report'])[2]");

	private By transactions = By.xpath("//span[text()='Transactions']");

	private By email = By.xpath("((//th[text()='Transaction ID'])[1]/ancestor::table//td[6])[2]");

	// verify day frequency
	private By dayFrequency = By.xpath("//a[contains(@onclick,'dayFrequenc')]");
	private By totalDayFrequency = By.xpath("//div[@id='mdlinfo']/span");
	private By cancelDayfrequncy = By.xpath("//h5[text()='Day Frequency']/following-sibling::button");

	// verify monthly frequency
	private By monthFrequency = By.xpath("//a[contains(@onclick,'monthFrequen')]");
	private By totalMonthFrequency = By.xpath("//div[@id='mdlinfo']/span");
	private By cancelMonthfrequncy = By.xpath("//h5[text()='Month Frequency']/following-sibling::button");

	// verify Week frequency
	private By weekFrequency = By.xpath("//a[contains(@onclick,'weakFreaquenc')]");
	private By cancelweekfrequncy = By.xpath("//h5[text()='Week Frequency']/following-sibling::button");

	// verify year frequency
	private By yearFrequency = By.xpath("//a[contains(@onclick,'yearFrequenc')]");
	private By cancelYearfrequncy = By.xpath("//h5[text()='Year Frequency']/following-sibling::button");
	
	// verify customer frequency
	private By CustomersMerchant = By.xpath("//a[contains(@onclick,'merchantAssociated')]");
	private By cancelButton = By.xpath("//h5[@id='mdltitle']/following-sibling::button");

	// verify customer card
	private By CustomersCards = By.xpath("//a[contains(@onclick,'cardAssociat')]");
	
   //verify  Customer's Countries
	private By CustomersCountries = By.xpath("//a[contains(@onclick,'cardAssociat')]");	
		
	   //verify  Customer's IPS
		private By CustomersIPS = By.xpath("//a[contains(@onclick,'ipCountry')]");	
	
   //verify  Customer's IPS
	private By amount = By.xpath("//a[contains(@onclick,'denominatio')]");	
	
	
	  //verify  Customer's IPS
		private By userDevice = By.xpath("//a[contains(@onclick,'userDevice')]");	
		
		//Verify total 
		private By total = By.xpath("//a[contains(@onclick,'emailHistory')]");
		@FindBy(xpath="//a[contains(@onclick,'emailHistory')]") private WebElement TOTAL;
		private By totalFrequency=By.xpath("//label[@id='pagtext1']//following-sibling::span[3]");
		
		@FindBy(xpath="//label[@id='pagtext1']//following-sibling::span[3]") private WebElement TOTAL_fREQ;
		private By canceEmaillButton = By.xpath("//h5[text()='Email Summary']/following-sibling::button");  
		
		private By showing=By.xpath("//label[text()='Showing ']");
		private By showing_1=By.xpath("(//label[text()='Showing '])[2]");
		
		
		//email summery page
		private By transactionId=By.xpath("((//th[text()='Transaction ID'])[2]/ancestor::table//td[3])[1]");                                                                                       
		private By purchaseInfo=By.xpath("//h5[@id='purchaseInfo']");
		private By canButtonpurchaseInfo=By.xpath("//h5[@id='purchaseInfo']/following-sibling::button");
		
		//email summary action 
		
		private By action=By.xpath("((//th[text()='Transaction ID'])[2]/ancestor::table//td[1])[2]");
		private By tran_ID=By.xpath("//span[@id='lAhistory']");
		
		
		private By nextBtn=By.xpath("(//a[text()='Next'])[2]");
		@FindBy(xpath="(//a[text()='Next'])[2]") private WebElement NEXT_BTN;
		
		
		
		
		
		
		
		
	private ActionDriver actionDriver;

	// page factory constructor
	public email_transactionPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.actionDriver = new ActionDriver(driver);
	}

//	public void navigateUptoTransaction(WebDriver driver) throws InterruptedException {
//
//		actionDriver.scrollToElement(analytics);
//		Reporter.log("User navigate upto Analytics", true);
//		actionDriver.click(report);
//		Reporter.log("User click on Report Module", true);
//		actionDriver.click(transactions);
//		Reporter.log("User Click on Transaction sub module", true);
//	}

	public void clickOnEmail() {

		actionDriver.click(email);
		Reporter.log("User Click on email field in a Transaction table", true);

	}

	public void verifydayFrequesncy(WebDriver driver) {

		String day = actionDriver.getText(dayFrequency);
		System.out.println("Total Month frequency :- " + day);

		Reporter.log(" day frequency :- " + day, true);

		actionDriver.click(dayFrequency);
		Reporter.log("User click on Day frequency", true);

		// String totalDay = actionDriver.getText(totalDayFrequency);
		List<WebElement> allSpans = driver.findElements(By.xpath("//div[@id='mdlinfo']/span"));
		int count = allSpans.size();
		String totalDay = String.valueOf(count);
		System.out.println("Total day frequency :- " + totalDay);

		Reporter.log("Total day frequency :- " + totalDay, true);

		Assert.assertEquals(day, totalDay);

		Reporter.log("Verify total days are perfectly matching", true);

		actionDriver.click(cancelDayfrequncy);
		Reporter.log("user click on cancel button on day frequency page", true);
	}

	public void verifyMonthFrequesncy(WebDriver driver) {

		String month = actionDriver.getText(monthFrequency);
		System.out.println("Total day frequency :- " + month);

		Reporter.log("Month frequency :- " + month, true);

		actionDriver.click(monthFrequency);
		Reporter.log("User click on Month frequency", true);

		// String totalDay = actionDriver.getText(totalDayFrequency);
		List<WebElement> allSpans = driver.findElements(By.xpath("//div[@id='mdlinfo']/span"));
		int count = allSpans.size();
		String totalMonth = String.valueOf(count);
		System.out.println("Total month frequency :- " + totalMonth);

		Reporter.log("Total month frequency :- " + totalMonth, true);

		Assert.assertEquals(month, totalMonth);

		Reporter.log("Verify total month are perfectly matching", true);

		actionDriver.click(cancelMonthfrequncy);
		Reporter.log("user click on cancel button on month frequency page", true);
	}

	public void verifyWeekFrequesncy(WebDriver driver) {

		String week = actionDriver.getText(weekFrequency);
		System.out.println("Total week frequency :- " + week);

		Reporter.log("week frequency :- " + week, true);

		actionDriver.click(weekFrequency);
		Reporter.log("User click on week frequency", true);

		// String totalDay = actionDriver.getText(totalDayFrequency);
		List<WebElement> allSpans = driver.findElements(By.xpath("//div[@id='mdlinfo']/span"));
		int count = allSpans.size();
		String totalweek = String.valueOf(count);
		System.out.println("Total week frequency :- " + totalweek);

		Reporter.log("Total week frequency :- " + totalweek, true);

		Assert.assertEquals(week, totalweek);

		Reporter.log("Verify total week are perfectly matching", true);

		actionDriver.click(cancelweekfrequncy);
		Reporter.log("user click on cancel button on week frequency page", true);
	}

	public void verifyYearFrequesncy(WebDriver driver) {

		String year = actionDriver.getText(yearFrequency);
		System.out.println("Total year frequency :- " + year);

		Reporter.log("year frequency :- " + year, true);

		actionDriver.click(yearFrequency);
		Reporter.log("User click on year frequency", true);

		// String totalDay = actionDriver.getText(totalDayFrequency);
		List<WebElement> allSpans = driver.findElements(By.xpath("//div[@id='mdlinfo']/span"));
		int count = allSpans.size();
		String totalyear = String.valueOf(count);
		System.out.println("Total year frequency :- " + totalyear);

		Reporter.log("Total year frequency :- " + totalyear, true);

		Assert.assertEquals(year, totalyear);

		Reporter.log("Verify total year are perfectly matching", true);

		actionDriver.click(cancelYearfrequncy);
		Reporter.log("user click on cancel button on year frequency page", true);
	}
	
	public void verifyCustomerMerchantFrequency(WebDriver driver) {

		String Customers_Merchant = actionDriver.getText(CustomersMerchant);
		System.out.println("Total Customers_Cards frequency :- " +Customers_Merchant);

		Reporter.log("Customers_Merchant :- " +Customers_Merchant, true);

		actionDriver.click(CustomersMerchant);
		Reporter.log("User click on Customers_Merchant frequency", true);

		// String totalDay = actionDriver.getText(totalDayFrequency);
		List<WebElement> allSpans = driver.findElements(By.xpath("//div[@id='mdlinfo']/span"));
		int count = allSpans.size();
		String Customers_merchant_Frequency = String.valueOf(count);
		System.out.println("Customers_Cards frequency :- " +Customers_merchant_Frequency);

		Reporter.log("Customers_merchant_Frequency frequency :- " +Customers_merchant_Frequency, true);

		Assert.assertEquals(Customers_Merchant,Customers_merchant_Frequency);

		Reporter.log("Verify Customers_merchant_Frequency are perfectly matching", true);

		actionDriver.click(cancelButton);
		Reporter.log("user click on cancel button on Customers_Merchant frequency page", true);
	}
	
	public void verifyCustomerCardFrequency(WebDriver driver) {

		String Customers_Cards = actionDriver.getText(CustomersCards);
		System.out.println("Total Customers_Cards frequency :- " + Customers_Cards);

		Reporter.log("Customers_Cards frequency :- " +Customers_Cards, true);

		actionDriver.click(CustomersCards);
		Reporter.log("User click on Customers_Cards frequency", true);

		// String totalDay = actionDriver.getText(totalDayFrequency);
		List<WebElement> allSpans = driver.findElements(By.xpath("//div[@id='mdlinfo']/span"));
		int count = allSpans.size();
		String Customers_Cards_Frequency = String.valueOf(count);
		System.out.println("Customers_Cards frequency :- " +Customers_Cards_Frequency);

		Reporter.log(" Customers_Cards_Frequency frequency :- " +Customers_Cards_Frequency, true);

		Assert.assertEquals(Customers_Cards,Customers_Cards_Frequency);

		Reporter.log("Verify Customers_Cards_Frequency are perfectly matching", true);

		actionDriver.click(cancelButton);
		Reporter.log("user click on cancel button on Customers Cards page", true);
	}
	
	public void verifyCustomerCountriesFrequency(WebDriver driver) {

		String Customers_Countries = actionDriver.getText(CustomersCountries);
		System.out.println("Total Customers_Countries frequency :- " + Customers_Countries);

		Reporter.log("Customers_Countries frequency :- " +Customers_Countries, true);

		actionDriver.click(CustomersCountries);
		Reporter.log("User click on Customers_Countries frequency", true);

		// String totalDay = actionDriver.getText(totalDayFrequency);
		List<WebElement> allSpans = driver.findElements(By.xpath("//div[@id='mdlinfo']/span"));
		int count = allSpans.size();
		String Customers_Countries_Frequency = String.valueOf(count);
		System.out.println("Customers_Countries_Frequency:- " +Customers_Countries_Frequency);

		Reporter.log("Customers_Countries_Frequency frequency :- " +Customers_Countries_Frequency, true);

		Assert.assertEquals(Customers_Countries,Customers_Countries_Frequency);

		Reporter.log("Verify Customers_Countries_Frequency are perfectly matching", true);

		actionDriver.click(cancelButton);
		Reporter.log("user click on cancel button on Customers countries page", true);
	}

	
	public void verifyCustomerIPSFrequency(WebDriver driver) {

		String Customers_IPS = actionDriver.getText(CustomersIPS);
		System.out.println("Total Customers_Countries frequency :- " + Customers_IPS);

		Reporter.log("Customers_IPS frequency :- " +Customers_IPS, true);

		actionDriver.click(CustomersIPS);
		Reporter.log("User click on Customers_IPS frequency", true);

		// String totalDay = actionDriver.getText(totalDayFrequency);
		List<WebElement> allSpans = driver.findElements(By.xpath("//div[@id='mdlinfo']/span"));
		int count = allSpans.size();
		String Customers_IPS_Frequency = String.valueOf(count);
		System.out.println("Customers_IPS_Frequency:- " +Customers_IPS_Frequency);

		Reporter.log("Customers_Countries_Frequency frequency :- " +Customers_IPS_Frequency, true);

		Assert.assertEquals(Customers_IPS,Customers_IPS_Frequency);

		Reporter.log("Verify Customers_IPS_Frequency are perfectly matching", true);

		actionDriver.click(cancelButton);
		Reporter.log("user click on cancel button on Customers IPS page", true);
	}
	
	public void verifyAmountFrequency(WebDriver driver) {

		String amount_Frequency = actionDriver.getText(amount);
		System.out.println("amount_Frequency :- " +amount_Frequency);

		Reporter.log("amount_Frequency :- " +amount_Frequency, true);

		actionDriver.click(amount);
		Reporter.log("User click on amount_Frequency", true);

		// String totalDay = actionDriver.getText(totalDayFrequency);
		List<WebElement> allSpans = driver.findElements(By.xpath("//div[@id='mdlinfo']/span"));
		int count = allSpans.size();
		String Amount_Frequency = String.valueOf(count);
		System.out.println("Amount_Frequency:- " +Amount_Frequency);

		Reporter.log("Customers_Countries_Frequency frequency :- " +Amount_Frequency, true);

		Assert.assertEquals(amount_Frequency,Amount_Frequency);

		Reporter.log("Verify Amount_Frequency are perfectly matching", true);

		actionDriver.click(cancelButton);
		Reporter.log("user click on cancel button on Amount_Frequency page", true);
	}
	
	public void verifyCustomerDeviceID(WebDriver driver) {

		String user_Device= actionDriver.getText(userDevice);
		System.out.println("CustomerDeviceID:- " +user_Device);

		Reporter.log("CustomerDeviceID " +user_Device, true);

		actionDriver.click(userDevice);
		Reporter.log("User click on CustomerDeviceID", true);

		// String totalDay = actionDriver.getText(totalDayFrequency);
		List<WebElement> allSpans = driver.findElements(By.xpath("//div[@id='mdlinfo']/span"));
		int count = allSpans.size();
		String Customer_Device_ID_Frequency= String.valueOf(count);
		System.out.println("user_Device_Frequency:- " +Customer_Device_ID_Frequency);

		Reporter.log("Customer_Device_ID_Frequency :- " +Customer_Device_ID_Frequency, true);

		Assert.assertEquals(user_Device,Customer_Device_ID_Frequency);

		Reporter.log("Verify user_Device_Frequency are perfectly matching", true);

		actionDriver.click(cancelButton);
		Reporter.log("user click on cancel button on user_Device_Frequency page", true);
	}
	
	public void verifyTotal(WebDriver driver) {

		String tota=actionDriver.getText(total);
		System.out.println("total:- " +tota);

		Reporter.log("total :- " +tota, true);

		actionDriver.click(total);
		Reporter.log("User click on Total", true);


		
		//String total_Frequency = actionDriver.getText(totalFrequency);
		actionDriver.scrollToElement(showing_1);
		
		 String TOTAL_FREQ =TOTAL_fREQ.getText();
		Assert.assertEquals(tota,TOTAL_FREQ );

		Reporter.log("Verify TotalFrequency are perfectly matching", true);
		

		actionDriver.click(canceEmaillButton);
		Reporter.log("user click on cancel button on Total page", true);
	}
	
	
	public void clickOnTotal() {
		

		actionDriver.click(total);
		Reporter.log("User click on Total", true);
		
		
	}
	
	public void verifyTransactionIDOnEmailSummeryPage() {
		//get transaction ID
		String Transaction_Id = actionDriver.getText(transactionId);
		System.out.println("get transaction id :-"+Transaction_Id);
		Reporter.log("get transaction id :-"+Transaction_Id, true);
		
		
		
		if(actionDriver.isEnabled(transactionId)) {
		//click on transaction ID
		actionDriver.click(transactionId);
		Reporter.log("Click on transaction id", true);
		//get text from purchase info page - email summary
				String purchase_info = actionDriver.getText(purchaseInfo);
				
				System.out.println(purchase_info);
				
				Assert.assertEquals(Transaction_Id,purchase_info,"if Purchase ID doesn't match then our TC is should be failed");
				
				Reporter.log("verify  transaction id/ purchase ID is successfully matching", true);
				
				actionDriver.click(canButtonpurchaseInfo);
				Reporter.log("user click on cancel button in purchase Info page", true);
		}else {
			
			
		System.out.println("This transaction ID is not clickable");
		}
		
		
	}
	
	
	public void validatePurchaseIDWithActions() {
		
		//get transaction ID
		String Transaction_Id = actionDriver.getText(transactionId);
		System.out.println("get transaction id :-"+Transaction_Id);
		Reporter.log("get transaction id :-"+Transaction_Id, true);
		
		//click on transaction ID
				actionDriver.click(action);
				Reporter.log("Click on Action", true);
		
				
				//get text from tansaction ID
				
				String Tr_Id = actionDriver.getText(tran_ID);
				System.out.println("get transaction id :-"+Tr_Id);
				Reporter.log("get Tr_Id :-"+Tr_Id, true);		
		
	}
	
	
	
	public void iterateAllTablePages(WebDriver driver) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    int totalRowCount = 0;

	    while (true) {
	        // Wait and fetch all rows on the current page
	        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
	                By.xpath("(//table[@class='table table-bordered'])[3]/tbody[2]/tr")));

	        int currentPageRowCount = rows.size();
	        totalRowCount += currentPageRowCount;

	        System.out.println("Rows on this page: " + currentPageRowCount);

	        // Process each row
	        for (WebElement row : rows) {
	            String rowText = row.getText();
	            System.out.println("Row Data: " + rowText);
	        }

	        // Check if 'Next' is disabled
	        WebElement nextButtonContainer = driver.findElement(By.xpath("(//li[contains(@class,'page-item next')])[2]"));
	        String classAttr = nextButtonContainer.getAttribute("class");

	        if (classAttr.contains("page-item next disabled")) {
	            System.out.println("✅ Reached last page.");
	            break;
	        }

	        // Click the next button using JS
	        WebElement nextBtn = nextButtonContainer.findElement(By.tagName("a"));
	        wait.until(ExpectedConditions.elementToBeClickable(NEXT_BTN)).click();

	        // Wait for table to refresh (avoid stale elements)
	        wait.until(ExpectedConditions.stalenessOf(rows.get(0)));
	    }

	    // Final output
	    System.out.println("🚀 Total rows across all pages: " + totalRowCount);
	}


	
	public void clickOnSpecificEmail(WebDriver driver, String targetEmail) {
		
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    boolean emailClicked = false;

		    while (true) {
		        // Wait and get all rows in the current page
		        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
		                By.xpath("(//th[text()='Transaction ID'])[1]/ancestor::table//td[6]")));

		        for (WebElement row : rows) {
		        	
		        	actionDriver.scrollToElement(showing);
		        	
		            List<WebElement> columns = row.findElements(By.xpath("(//th[text()='Transaction ID'])[1]/ancestor::table//td[6]"));

		            for (WebElement cell : columns) {
		                String cellText = cell.getText().trim();

		                if (cellText.equalsIgnoreCase(targetEmail)) {
		                    System.out.println("✅ Found email: " + cellText + " - Clicking on first occurrence.");

		                    try {
		                        // Try clicking the element (plain cell or anchor)
		                        if (cell.findElements(By.tagName("a")).size() > 0) {
		                            cell.findElement(By.tagName("a")).click(); // If it's a link
		                        } else {
		                            cell.click(); // Otherwise, click the cell itself
		                        }
		                    } catch (Exception e) {
		                        System.out.println("⚠️ Error clicking on email: " + e.getMessage());                                                                                                                                                                
		                    }

		                    emailClicked = true;
		                    break; // Exit inner loop
		                }
		            }

		            if (emailClicked) break; // Exit row loop
		        }

		        if (emailClicked) break; // Exit page loop

		        // Go to next page if email not found yet
		        WebElement nextButtonContainer = driver.findElement(By.xpath("//li[@class='page-item next']"));
		        String classAttr = nextButtonContainer.getAttribute("class");

		        if (classAttr.contains("page-item next disabled")) {
		            System.out.println("❌ Email not found in any page.");
		            break;
		        }

		        // Click on Next button
		        WebElement nextBtn = nextButtonContainer.findElement(By.tagName("a"));
		        
		        nextBtn.click();

		        // Wait for table refresh
		   //     wait.until(ExpectedConditions.stalenessOf(rows.get(0)));
		    }

		    Assert.assertTrue(emailClicked, "❌ Email '" + targetEmail + "' not found in any page.");
		}

}
