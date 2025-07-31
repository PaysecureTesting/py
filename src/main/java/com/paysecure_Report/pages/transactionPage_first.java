package com.paysecure_Report.pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

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
	
	//email

	private By email= By.xpath("(//th[text()='Transaction ID'])[1]/ancestor::table//td[6]");
	
	//select time zone
	private By selectTimeZone=By.xpath("//ul[@id='select2-tZone-results']/li");
	@FindBy(xpath="//ul[@id='select2-tZone-results']/li")private WebElement SELECTTIMEZONE;
	private By stz=By.xpath("//span[@aria-controls='select2-tZone-container']");
	
	
	
	public String actualEmail;
	private ActionDriver actionDriver;
	//private WebDriver driver;

	// page factory constructor
	public transactionPage_first(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.actionDriver = new ActionDriver(driver);
	}
	
	public  String verifyEmailOnTransactionAndEmailPage(String email_ID) {
		
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
            	By  nextButtonBy = By.xpath("//li[contains(@class,'page-item next')]/a");

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
        WebElement riskEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h5[@id='riskScoreEmail']")));
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
		boolean PacificGuadalcanal = options.stream().anyMatch(el -> el.getText().equalsIgnoreCase("Pacific/Guadalcanal"));
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
	}}
	
	public void verifyPartialSearchInSelectTimeZone() {
		
	}
			
	
	
	
	
	
	
	
}