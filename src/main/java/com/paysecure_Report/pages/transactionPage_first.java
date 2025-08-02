package com.paysecure_Report.pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.paysecure.base.baseClass;

public class transactionPage_first {
	WebDriver driver = baseClass.getDriver();

	public String cardNumber = "";
	public String creditCardNumber = "";

	// email

	private By email = By.xpath("(//th[text()='Transaction ID'])[1]/ancestor::table//td[6]");

	// select time zone
	private By selectTimeZone = By.xpath("//ul[@id='select2-tZone-results']/li");
	@FindBy(xpath = "//ul[@id='select2-tZone-results']/li")
	private WebElement SELECTTIMEZONE;
	private By stz = By.xpath("//span[@aria-controls='select2-tZone-container']");

	private By lastStatus = By.xpath("(//th[text()='Transaction ID'])[1]/ancestor::table//td[11]");
	@FindBy(xpath = "(//th[text()='Transaction ID'])[1]/ancestor::table//td[11]")
	private WebElement LASTSTATUS;
	@FindBy(xpath = "(//th[text()='Transaction ID'])[1]/ancestor::table//td[7]")
	private WebElement CARDNUMBER;
	private By showing = By.xpath("//label[@id='dailypagtext']");

	// payment info
	private By paymentInfo = By.xpath("//td[contains(text(),'PaymentIn')]/span[1]/i");
	private By number = By.xpath("(//span[contains(text(),'umber')][1])[2]//following-sibling::span[2]");
	private By paymentInfoText = By.xpath("(//span[text()='paymentMethod'][1])/following-sibling::span[2]");

	// verifyLastStatusInHistory
	private By created = By.xpath("//td[text()='created']");
	private By pending_Execute = By.xpath("//td[text()='pending_execute']");
	private By payment_in_process = By.xpath("//td[text()='payment_in_process']");
	private By paid = By.xpath("//td[text()='paid']");
	private By viewed = By.xpath("//td[text()='viewed");
	private By refunded = By.xpath("//td[text()='refunded");
	
	private By last_Status=By.xpath("((//th[text()='Transaction ID'])[1]/ancestor::table//td[11])[1]");
	private By history = By.xpath("(//button[@title='History'])[1]");

	public String actualEmail;
	private ActionDriver actionDriver;
	// private WebDriver driver;

	// page factory constructor
	public transactionPage_first(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.actionDriver = new ActionDriver(driver);
	}

	public String verifyEmailOnTransactionAndEmailPage(String email_ID) {

		actualEmail = actionDriver.getText(email);

		actionDriver.click(email);
		Reporter.log("User click on email in a Transaction table", true);

		return actualEmail;

	}

//	public void verifyEmailID() {
//		 String expEmail = actionDriver.getText(riskEmailID);
//		
//		 Assert.assertTrue(actualEmail.startsWith(email_ID));
//		 
//	}
	public static String foundEmail = "";

	public void findAndClickEmail(String email_id) throws InterruptedException {
		WebDriver driver = baseClass.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		boolean emailFound = false;

		while (!emailFound) {
			List<WebElement> emailElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
					By.xpath("(//th[text()='Transaction ID'])[1]/ancestor::table//td[6]"))); // 6th column = EMAIL

			for (WebElement emailCell : emailElements) {
				String currentEmail = emailCell.getText().trim();

				if (currentEmail.equals(email_id)) {
					foundEmail = currentEmail;
					emailCell.click();
					emailFound = true;
					break;
				}
			}

			if (!emailFound) {
				By nextButtonBy = By.xpath("//li[contains(@class,'page-item next')]/a");

				if (driver.findElements(nextButtonBy).isEmpty()) {
					System.out.println("Next button not found");
					break;
				}

				WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(nextButtonBy));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextBtn);
				Thread.sleep(300);
				nextBtn.click();
			}
		}
	}

	// Method to get the risk email after click
	public String getRiskEmailAfterClick() {
		WebDriver driver = baseClass.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement riskEmail = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[@id='riskScoreEmail']")));
		return riskEmail.getText().trim();
	}

	public void verifySizeOfSelectTimeZone() {
		WebDriver driver = baseClass.getDriver();

		actionDriver.clickUsingJS(stz);

		WebElement dropdownTrigger = driver.findElement(By.xpath("//span[@id='select2-tZone-container']"));
		dropdownTrigger.click(); // ensures the <li> list becomes visible

		// Find all options
		List<WebElement> options = driver.findElements(By.xpath("//ul[@id='select2-tZone-results']/li"));

		// Assert the list is not empty
		Assert.assertTrue(options.size() > 0, "❌ No options found in the Select Time Zone dropdown.");
	}

	public void verifySpecificOptionsInSelectTimeZone() {
		actionDriver.clickUsingJS(stz);
		// Find all options
		List<WebElement> options = driver.findElements(By.xpath("//ul[@id='select2-tZone-results']/li"));
		boolean PacificGuadalcanal = options.stream()
				.anyMatch(el -> el.getText().equalsIgnoreCase("Pacific/Guadalcanal"));
		Assert.assertTrue(PacificGuadalcanal, "❌ Option 'India' not found.");
	}

	public void selectSpecificOptionSelectTimeZone() {
		actionDriver.clickUsingJS(stz);
		// Find all options
		List<WebElement> options = driver.findElements(By.xpath("//ul[@id='select2-tZone-results']/li"));
		for (WebElement option : options) {
			if (option.getText().equalsIgnoreCase("Atlantic/St_Helena")) {
				option.click();
				break;
			}
		}
	}

//public void verifyIfStatusIdPaidOrRefundedThenValidatedCardNumber() {
//	WebDriver driver = baseClass.getDriver();
//	// (//th[text()='Transaction ID'])[1]/ancestor::table//td[7]
//	List<WebElement> rows = driver.findElements(By.xpath("(//table[@class='table table-bordered'])[2]/tbody[2]/tr"));
//
//	for (WebElement ro : rows) {
//		try {
//			WebElement statusCell = driver
//					.findElement(By.xpath("(//th[text()='Transaction ID'])[1]/ancestor::table//td[11]"));
//			String status = statusCell.getText().trim();
//
//			// Get the Card Number cell (assuming it's the first cell)
//			WebElement cardCell = driver
//					.findElement(By.xpath("(//table[@class='table table-bordered'])[2]/tbody[2]/tr"));
//			String cardText = cardCell.getText().trim();
//
//			if (status.equalsIgnoreCase("paid") && cardText.startsWith("222100") || cardText.startsWith("411111")
//					|| cardText.startsWith("424242")) {
//				// Click on the card number link
//				cardCell.click();
//				break; // Exit after first match if needed
//			}
//		} catch (NoSuchElementException e) {
//			continue;
//		}
//	}

	public void clickOnCard(WebDriver driver, String targetCard) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		boolean card = false;

		while (true) {
			// Wait and get all rows in the current page
			List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
					By.xpath("(//th[text()='Transaction ID'])[1]/ancestor::table//td[7]")));

			for (WebElement row : rows) {

				actionDriver.scrollToElement(showing);

				List<WebElement> columns = row
						.findElements(By.xpath("(//th[text()='Transaction ID'])[1]/ancestor::table//td[7]"));

				for (WebElement cell : columns) {
					String cellText = cell.getText().trim();

					if (cellText.equalsIgnoreCase(targetCard)) {
						System.out.println("✅ Found card: " + cellText + " - Clicking on first occurrence.");

						try {
							// Try clicking the element (plain cell or anchor)
							if (cell.findElements(By.tagName("a")).size() > 0) {
								cell.findElement(By.tagName("a")).click(); // If it's a link
							} else {
								cell.click(); // Otherwise, click the cell itself
							}
						} catch (Exception e) {
							System.out.println("⚠️ Error clicking on card: " + e.getMessage());
						}

						card = true;
						break; // Exit inner loop
					}
				}

				if (card)
					break; // Exit row loop
			}

			if (card)
				break; // Exit page loop

			// Go to next page if email not found yet
			WebElement nextButtonContainer = driver.findElement(By.xpath("//li[@class='page-item next']"));

			String classAttr = nextButtonContainer.getAttribute("class");

			if (classAttr.contains("page-item next disabled")) {
				System.out.println("❌ Card not found in any page.");
				break;
			}

			// Click on Next button
			WebElement nextBtn = nextButtonContainer.findElement(By.tagName("a"));

			nextBtn.click();

			// Wait for table refresh
			// wait.until(ExpectedConditions.stalenessOf(rows.get(0)));
		}

		Assert.assertTrue(card, "❌ Card '" + targetCard + "' not found in any page.");

	}

	public void clickOnTransactionColumn(WebDriver driver) throws InterruptedException {

		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(15));
		boolean actionClicked = false;

		List<WebElement> rows = w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//table[@id='tabel22']//tbody[@id='analyticTransStats1']/tr")));

		for (WebElement r : rows) {
			// ✅ Get Last Status from the current row's 10th column
			String statusText = r.findElement(By.xpath("./td[10]")).getText().trim();

			if (!statusText.equalsIgnoreCase("EXPIRED")) {
				// ✅ Get Purchase ID from 3rd column of current row
				cardNumber = r.findElement(By.xpath("./td[6]")).getText().trim();
				Thread.sleep(1500);
//	        	merchantName=r.findElement(By.xpath("./td[4]")).getText().trim();
//	            System.out.println("✅ Found Purchase ID: " +clickedTxnId);

				// ✅ Click Action button from same row
				WebElement actionBtn = driver
						.findElement(By.xpath("//table[@id='tabel22']//tbody[@id='analyticTransStats1']/tr/td[3]/a"));
				System.out.println("🛠 Clicking Action for Purchase ID: " + cardNumber);
				actionBtn.click();

				actionClicked = true;
				break;
			}
		}

		// ✅ Assert outside loop
		Assert.assertTrue(actionClicked, "❌ No Action button clicked — all transactions were expired.");
	}

	public void verifyPaymentInfo_CardNumber() {

		actionDriver.scrollToElement(paymentInfo);

		actionDriver.click(paymentInfo);
		Reporter.log("User click on Payment Info", true);

		actionDriver.scrollToElement(number);

		String Card_Number = actionDriver.getText(number);
		String maskedNumber = Card_Number.replaceAll(".*?(\\d{8}\\*{4}\\d{4}).*", "$1");

		String format = maskedNumber.replace("\"", "");

		System.out.println("Got a refine paymentInfoText :- " + format);
		Assert.assertEquals(cardNumber, format);

	}

	public void verifyPaymentInfo_paymentInfo() {

		actionDriver.scrollToElement(paymentInfo);

		actionDriver.click(paymentInfo);
		Reporter.log("User click on Payment Info", true);

		actionDriver.scrollToElement(paymentInfoText);

		String Card_Number = actionDriver.getText(paymentInfoText);
		String maskedNumber = Card_Number.replaceAll(".*?(\\d{8}\\*{4}\\d{4}).*", "$1");
		String format = maskedNumber.replace("\"", "");

		System.out.println("Got a refine paymentInfoText :- " + format);
		Assert.assertEquals(cardNumber, format);

	}

    //verify Last Status In History
public String LastStatus="";
	public String getValueOfLastStatus() {

		
		LastStatus = actionDriver.getText(last_Status);
		Reporter.log("User get text of Last Status based on Filter :-"+LastStatus, true);
		
		
		return LastStatus;
		
		
	}
	
	
	public void checkverifyLastStatusInHistory() {
		

		actionDriver.clickUsingJS(history);
		Reporter.log("User click on Last Action Button", true);
		
		
		String status = actionDriver.getText(last_Status);
		Reporter.log("User get text of Last Status based on Filter :-"+LastStatus, true);
		
		Assert.assertEquals(LastStatus, status,"If Last Status and Status is not matched then our TC is should be Failed.");
		
	}
	
	
	
	
	
	
	
	
	

}