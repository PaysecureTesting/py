package com.paysecure_Report.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.paysecure.actiondriver.ActionDriver;
import com.paysecure.bankPage.allBanks;
import com.paysecure.base.baseClass;

public class transactionPage_first {



	// select bank
	private By selectbankArrow = By.xpath("(//span[@class='select2-selection__arrow'])[4]");//// span[text()='Select Bank']              
	private By selectBank= By.xpath("// span[text()='Select Bank']");
	//search bank name -Bank
	private By searchBankName=By.xpath("//input[@placeholder='Search bank name']");
	private By searchButton=By.xpath("//button[@id='bank-search']");
	
	
	//search Bank - transaction
	private By searchBankName_Transaction=By.xpath("//input[@class='select2-search__field']");
	private By selectFirstMatch=By.xpath("(//ul[@id='select2-bnk-results']/li)[1]");
	private By getOptionsMID=By.xpath("//select[@id='midn']/option");
	private By selectMID=By.xpath("//select[@id='midn']");
	
	
	//view MID
	private By viewMID=By.xpath("(//button[@ng-click='mids(bank.id)'])[1]");
	private By getAllMids=By.xpath("//div[@class='bnk-sts']");
	@FindBy(xpath = "//div[@class='bnk-sts']")
	private List<WebElement> GETALLMIDS;

	private By bankNames = By.xpath("//select[@id='bnk']");
	@FindBy(xpath = "//select[@id='bnk']")
	private WebElement BANKNAMES;
	
	

	int quantity;

	private ActionDriver actionDriver;

	public transactionPage_first(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.actionDriver = new ActionDriver(driver);
	}

	public void getAllBankName(WebDriver driver) throws InterruptedException {

		actionDriver.click(selectBank);
		Thread.sleep(3000);
		actionDriver.click(selectBank);
		Select s = new Select(BANKNAMES);
		List<WebElement> name = s.getOptions();
		// System.out.println(name.size());
		for (WebElement bankName : name) {
			System.err.println("All bank name Is :- " + bankName.getText());

		}

		quantity = name.size();
		System.out.println("Total bank name Quantity :- " + quantity);
	}

	public void verifyQuantityOfBanks() {

		int totalQuantity = quantity - 1;
		System.err.println(totalQuantity);

		int finalQuantity = allBanks.num;

		System.out.println();

		Assert.assertEquals(totalQuantity, finalQuantity);

	}
	
	
	public void searchBank(String bank_Name) {
		
		actionDriver.enterText(searchBankName, bank_Name);
		Reporter.log("User Enter Bank name on the Search Bank name field :- "+bank_Name, true);
		actionDriver.click(searchButton);
		Reporter.log("User click on search button", true);
		
		
	}
	
	public List<String> viewMID(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
       actionDriver.click(viewMID);
        Reporter.log("User clicked on View MID button", true);

        // Wait until all MID elements are visible
        List<WebElement> midElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//div[@class='bnk-sts']")));

        List<String> mids = new ArrayList<>();

        // Extract and trim text from each element
        for (WebElement el : midElements) {
            mids.add(el.getText().trim());
        }

        // Debug log to inspect raw MID values
        for (String s : mids) {
            System.out.println("MID (raw): [" + s + "]");
        }

        // Remove "Select MID" entries (case-insensitive and whitespace-tolerant)
        mids.removeIf(s -> {
            String trimmed = s.trim();
            return trimmed.equalsIgnoreCase("Select MID");
        });

        // Final output
        System.err.println("Bank MIDs: " + mids);

        return mids;
    }



	
	
	
	public List<String> selectbank_Transaction(String bank_Name) throws InterruptedException {
	    WebDriver driver = baseClass.getDriver();

	    actionDriver.click(selectBank);
	    Thread.sleep(3000);

	    actionDriver.enterText(searchBankName_Transaction, bank_Name);
	    Reporter.log("User Enter bank name in transaction sub module  :- " + bank_Name, true);

	    actionDriver.click(selectFirstMatch);
	    Reporter.log("User click on first match Bank", true);

	    actionDriver.click(selectMID);
	    Reporter.log("User click on Select MID", true);
	    Thread.sleep(3000);

	    List<WebElement> mid = driver.findElements(By.xpath("(//div[@class='custom-select'])[2]/descendant::option"));
	    List<String> mids = new ArrayList<>();

	    for (WebElement el : mid) {
	        mids.add(el.getText().trim());
	    }

	    // 🔍 Optional debug to see exact values
	    for (String s : mids) {
	        System.out.println("MID (raw): [" + s + "]");
	    }

	    // ✅ Remove any "Select MID" values regardless of spaces/case
	    mids.removeIf(s -> s.trim().equalsIgnoreCase("Select MID"));

	    System.err.println("Transaction Mids :- " + mids);

	    return mids;
	}


	

	public void compareMidLists(List<String> list1, List<String> list2) {
	    // Sort both for order-insensitive comparison
	    Collections.sort(list1);
	    Collections.sort(list2);

	    // Log for debugging
	    System.out.println("View MIDs      : " + list1);
	    System.out.println("Transaction MIDs: " + list2);

	    // Assert both lists are equal
	    Assert.assertEquals(list1, list2, "Mismatch in MIDs between View and Transaction modules");
	}

	
	
	
	

}
