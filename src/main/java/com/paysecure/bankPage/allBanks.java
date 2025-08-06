package com.paysecure.bankPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import com.paysecure.base.baseClass;

public class allBanks {

	// navigate upto bank
	private By bank = By.xpath("//span[text()='Banks']");
	private By allBank = By.xpath("//span[text()='All Banks']");
	private By banks = By.xpath("//h1[text()='Banks']");
	private By bankQuantity = By.xpath("//span[@id='llb3']");

	// select MID
	private By mid = By.xpath("(//button[@class='btn btn-primary btn-sm ng-scope'])[1]");
	private By searchBank = By.xpath("//input[@placeholder='Search bank name']");

	// select bank
	private By selectbankArrow = By.xpath("(//span[@class='select2-selection__arrow'])[4]");//// span[text()='Select
																							//// Bank']
	private By selectBank = By.xpath("// span[text()='Select Bank']");
	// search bank name -Bank
	private By searchBankName = By.xpath("//input[@placeholder='Search bank name']");
	private By searchButton = By.xpath("//button[@id='bank-search']");

	// search Bank - transaction
	private By searchBankName_Transaction = By.xpath("//input[@class='select2-search__field']");
	private By selectFirstMatch = By.xpath("(//ul[@id='select2-bnk-results']/li)[1]");
	private By getOptionsMID = By.xpath("//select[@id='midn']/option");
	private By selectMID = By.xpath("//select[@id='midn']");

	// view MID
	private By viewMID = By.xpath("//button[@class='btn btn-primary btn-sm ng-scope']");
	private By getAllMids = By.xpath("//div[@class='bnk-sts']");
	@FindBy(xpath = "//div[@class='bnk-sts']")
	private List<WebElement> GETALLMIDS;

	private By bankNames = By.xpath("//select[@id='bnk']");
	@FindBy(xpath = "//select[@id='bnk']")
	private WebElement BANKNAMES;

	// create MID
	private By createMID = By.xpath("//span[text()='Add MID']");
	private By midName = By.xpath("//input[@id='mid']");
	private By allotedMID = By.xpath("//input[@id='mid_desc']");
	private By authKey = By.xpath("//input[@id='mid_auth_key']");

	// allowed currency
	private By selectCurrency = By.xpath("//button[@title='Select currency']");
	private By searchCurrency = By.xpath("(//input[@class='form-control multiselect-search'][1])");
	private By selectAllCurrency = By.xpath("(//label[normalize-space()='Select all']/input[1])[1]");

	// allowed cards
	private By allowedCards = By.xpath("(//button[@title='ALL'])[1]");
	private By selectAllCards = By.xpath("(//a[@class='multiselect-all'])[2]");
	private By submit = By.xpath("//span[text()='Submit']");

	private By authKeyText = By.xpath("//label[text()='Auth Key']");
	
	//show all button
	private By showAll=By.xpath("//span[text()='Show all ']");
	private By activebank=By.xpath("//span[text()='Active ']");
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public static int num;
	int quantity;

	private ActionDriver actionDriver;
	private WebDriver driver;

	// page factory constructor
	public allBanks(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.actionDriver = new ActionDriver(driver);
	}

	public void navigateUptoAllBanks() {

		actionDriver.click(bank);
		// Reporter.log("", true);
		Reporter.log("User Click on Bank Module", true);

		actionDriver.click(allBank);
		Reporter.log("User Click on All Bank Sub Module", true);

		Assert.assertTrue(actionDriver.isDisplayed(banks),
				"If Banks is not displays then Our TC is in Failed condition");
		Reporter.log("User is on the Banks Page", true);
	}

	public int getQuantityOfAllBank() {

		actionDriver.scrollToElement(bankQuantity);
		Reporter.log("User navigate upto bank of quantity", true);

		String quantity = actionDriver.getText(bankQuantity);

		num = Integer.parseInt(quantity);
		System.err.println("Total banks quantity :- " + num);

		return num;
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
		Reporter.log("User Enter Bank name on the Search Bank name field :- " + bank_Name, true);
		actionDriver.click(searchButton);
		Reporter.log("User click on search button", true);

	}

	public List<String> mids_bank;


	public List<String> viewMID(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		actionDriver.click(viewMID);
		Reporter.log("User clicked on View MID button", true);

		// Wait until all MID elements are visible
		List<WebElement> midElements = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='bnk-sts']")));

		mids_bank = new ArrayList<>();

		// Extract and trim text from each element
		for (WebElement el : midElements) {
			mids_bank.add(el.getText().trim());
		}

		// Debug log to inspect raw MID values
		for (String s : mids_bank) {
			System.out.println("MID (raw): [" + s + "]");
		}

		mids_bank.removeIf(s -> {
			String trimmed = s.trim();
			return trimmed.equalsIgnoreCase("Select MID");
		});

		// Final output
		System.err.println("Bank MIDs: " + mids_bank);
		System.err.println("mids_Transaction" + mids_Transaction);
		System.err.println("mids_bank" + mids_bank);
		return mids_bank;
	}
	
	public List<String> mids_Transaction;

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
		mids_Transaction = new ArrayList<>();

		for (WebElement el : mid) {
			mids_Transaction.add(el.getText().trim());
		}

		// 🔍 Optional debug to see exact values
		for (String s : mids_Transaction) {
			System.out.println("MID (raw): [" + s + "]");
		}

		// ✅ Remove any "Select MID" values regardless of spaces/case
		mids_Transaction.removeIf(s -> s.trim().equalsIgnoreCase("Select MID"));

		System.err.println("Transaction Mids :- " + mids_Transaction);

		return mids_Transaction;
	}

	public boolean compareBankAndTransactionMIDs() throws InterruptedException {
		Set<String> setMids = new HashSet<>(mids_bank);

		for (String mid : mids_Transaction) {
			if (setMids.contains(mid) == false)
				return false;
		}

		return true;

	}

	public void addMidToBank(String midname, String allotedmid, String authkey) {

		actionDriver.clearText(createMID);
		Reporter.log("User click on Add Mid Button", true);

		actionDriver.enterText(midName, midname);
		Reporter.log("User enter mid name in mid name textfield" + midname, true);

		actionDriver.enterText(allotedMID, allotedmid);
		Reporter.log("User enter Alloted mid name in Alloted mid name textfield" + allotedmid, true);

		actionDriver.enterText(authKey, authkey);
		Reporter.log("User enter auth key in auth key text field" + authkey, true);

	}

	public void allowedCurrencyForcreateMID(String currency) throws InterruptedException {

//		actionDriver.scrollToElement(authKeyText);
		Thread.sleep(4000);

		// actionDriver.scrollToElement(selectCurrency);
		actionDriver.click(selectCurrency);
		Reporter.log("User click on select currency ", true);

		actionDriver.enterText(searchCurrency, currency);
		Reporter.log("User enter currency in search currency textfield" + currency, true);

		// actionDriver.click(selectAllCurrency);
		actionDriver.clickUsingJS(selectAllCurrency);
		Reporter.log("User click on select all currency checkbox", true);
	}

	public void allowedCardsForCreateMID() {

		actionDriver.clickUsingJS(allowedCards);

		// actionDriver.click(allowedCards);
		Reporter.log("User click on allowed card  ", true);

		actionDriver.clickUsingJS(selectAllCards);

		// actionDriver.click(selectAllCards);
		Reporter.log("User click on select all card checkbox", true);

	}

	public void submitMID() throws InterruptedException {

		actionDriver.scrollToElement(submit);
		Thread.sleep(1200);

		actionDriver.click(submit);
		Reporter.log("User click on submit button", true);
	}

	public void clickOnAddMid() {
		actionDriver.click(createMID);
		Reporter.log("User click on Add MID Button", true);
	}

	private By viewBankDetails = By.xpath("//button[@class='btn btn-primary btn-sm ng-scope']");

	public void view_Bank_Details() throws InterruptedException {
		actionDriver.scrollToElement(viewBankDetails);
		Thread.sleep(1200);
		actionDriver.click(viewBankDetails);
		Reporter.log("User click on Add MID Button", true);
	}
	
	List<String> bankMid;
	public List<String> getAllMidFromBank() {
		WebDriver driver=baseClass.getDriver();
		
		List<WebElement> md = driver.findElements(getAllMids);
		
		 bankMid = new ArrayList<>();
		for(WebElement m:md) {
			bankMid.add(m.getText().trim());
		}
		for(String ba:bankMid) {
			System.err.println(ba);
		}
		return bankMid;

	}
	
	
	public boolean compare_BankAndTransactionMIDs() throws InterruptedException {
		Set<String> setMids = new HashSet<>(bankMid);

		for (String mid : mids_Transaction) {
			if (setMids.contains(mid) == false)
				return false;
		}

		return true;

	}
	
	
	//=============================================================================================================================================================================================================================
	
	
	

}
