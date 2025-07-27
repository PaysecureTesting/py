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


public class download_page {

	// navigate upto Bank Transaction Time Report
		private By analytics = By.xpath("//span[text()='Analytics']");
		private By report = By.xpath("(//span[text()='Report'])[2]");
		private By download= By.xpath("//span[text()='Download']");
		
@FindBy (xpath="//h1[text()='Download']") private WebElement DOWNLOAD;


	// currency
		private By selectCurrency= By.xpath("//span[@id='select2-merCurr-container']");
		private By searchCurrencytextfield= By.xpath("//input[@class='select2-search__field']");
		private By listOfAllCurrencies= By.xpath("//ul[@id='select2-merCurr-results']/li");
		



	// download -- select class -- purchase or Bank Transactions
		private By downloadSelect = By.xpath("//select[@id='downloadType']");
		private By noRecordsFound= By.xpath("//h2[text()='No Records Found']");
		
		@FindBy(xpath="//h2[text()='No Records Found']") private WebElement NO_RECORDS_FOUND;
		
		private By OK= By.xpath("//button[text()='OK']");

	

		private ActionDriver actionDriver;
		
		
		
		// page factory constructor
		public download_page(WebDriver driver) {
			PageFactory.initElements(driver, this);
			this.actionDriver = new ActionDriver(driver);
		}
		

	public void verifyUserIsOnDownloadPage(WebDriver driver) throws InterruptedException {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		Thread.sleep(1800);
		// scroll to element
		actionDriver.scrollToElement(analytics);
		
	//	actionDriver.moveToElement(analytics);
		

	
		actionDriver.click(report);
		Reporter.log("Click on Report Module", true);
		
		//actionDriver.click(download);
		actionDriver.clickUsingJS(download);
		
		Reporter.log("Click on download Sub Module", true);

		Assert.assertTrue(DOWNLOAD.isDisplayed(), "user is not on the Api Report Report this page");
		Reporter.log("Verify user is on the Download page", true);
	}

	public void downloadPurchaseOrBanktransaction(String downloadType) {
		actionDriver.selectByVisibleText(downloadSelect, downloadType);
	
		Reporter.log("user select values " + downloadType, true);

	}

	public void selectCurrency(WebDriver driver, String partialNameCurrency, String Currency)
			throws InterruptedException {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		Thread.sleep(2000);
		
actionDriver.click(selectCurrency);
		Reporter.log("click on the select currency filed", true);
		Thread.sleep(2000);
		w.until(ExpectedConditions.elementToBeClickable(searchCurrencytextfield)).sendKeys(partialNameCurrency);
		
		
		Reporter.log("Enter currency name in a search currency functioanlity", true);

		Thread.sleep(2000);

		List<WebElement> suggestions = w.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id='select2-merCurr-results']/li")));

		for (WebElement s : suggestions) {
			if (Currency.equalsIgnoreCase(s.getText().trim())) {
				Thread.sleep(1000);
				s.click();
				Reporter.log("select merchant name :- " + Currency, true);
				break;

			}
		}
		Reporter.log("select Currency from all Currency", true);

	}
	
	
	
	public WebElement noRecordsFound(WebDriver driver) throws InterruptedException {
		
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		Thread.sleep(2000);
		WebElement NoRecordFound = w.until(ExpectedConditions.visibilityOf(NO_RECORDS_FOUND));

		if(NO_RECORDS_FOUND.isDisplayed()) {
			System.out.println("There is no records found on Download Page");
		}
		
		Reporter.log("click on the select currency filed", true);
		
		return NO_RECORDS_FOUND;
	}
	
	public void clickONOKButton(WebDriver driver) throws InterruptedException {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		actionDriver.click(OK);
		
        Thread.sleep(3000);
		Reporter.log("click on the OK button", true);
		
	}
	
	
	
	
	
	
	

}
