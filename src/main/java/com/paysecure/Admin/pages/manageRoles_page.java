package com.paysecure.Admin.pages;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.paysecure.actiondriver.ActionDriver;
import com.paysecure.utilities.testData_CreateRoll;

public class manageRoles_page {
	// Navigate - Manage Roles
	private By manageRoles = By.xpath("//span[text()='Manage Roles']");
	private By manage_Roles = By.xpath("//h1[text()='Manage Roles']");

	// search Functionality
	private By search = By.xpath("//input[@placeholder='Username or Merchant Name']");
	@FindBy(xpath="//input[@placeholder='Username or Merchant Name']") private WebElement SEARCH;
	private By searchButton = By.xpath("//input[@placeholder='Username or Merchant Name']/following-sibling::button");

	@FindBy(xpath = "//span[@name='fordeleterole']")
	private WebElement USERNAME;
	private By noRecordsFound = By.xpath("//td[text()='No Records Found']");

	// add role
	private By adddRole = By.xpath("//button[@title='Add Role']");
	private By userName = By.xpath("//input[@id='username1']");
	private By password = By.xpath("//input[@id='password1']");
	private By emailAddress = By.xpath("//input[@id='emailaddress1']");
	private By role = By.xpath("//select[@id='role_id1']");
	@FindBy(xpath="//select[@id='role_id1']") private WebElement ROLE;
	private By addRoleButton = By.xpath("(//button[text()='Add Role'])[2]");
	private By genPass = By.xpath("//button[text()='GenPass']");
	@FindBy(xpath = "//input[@id='password1']")
	private WebElement PASSWORD;

	private By emailError = By.xpath(
			"//input[@id='emailaddress1']/following-sibling::span[contains(normalize-space(),'field is required')]");
	private By email_Character = By
			.xpath("//input[@id='emailaddress1']/following-sibling::span[contains(normalize-space(),'Email address')]");

	private ActionDriver actionDriver;

// page factory constructor
	public manageRoles_page(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.actionDriver = new ActionDriver(driver);
	}

	public void navigateToManageRoles() {
		actionDriver.click(manageRoles);
		Reporter.log("User Click on Main Module - Manage Roles", true);

		Assert.assertTrue(actionDriver.isDisplayed(manage_Roles),
				"If Manage Roles is not displays on Manage Roles page then user is not on the Manage Roles page");
		Reporter.log("User is on the Manage Roles Main Module", true);

	}

	public void searchByUsername_Merchant(String username_Merchant) {
		actionDriver.enterText(search, username_Merchant);
		Reporter.log("User search username/Merchant :- " + username_Merchant, true);

		actionDriver.click(searchButton);
		Reporter.log("User Click on search button", true);

	}

	public void verifySearchRoleSameorNot(WebDriver driver, String username_Merchant) {
		actionDriver.enterText(search, username_Merchant);
		Reporter.log("User search username/Merchant :- " + username_Merchant, true);

		actionDriver.click(searchButton);
		Reporter.log("User Click on search button", true);

		String user_name = USERNAME.getAttribute("data-username");

		Assert.assertEquals(username_Merchant, user_name, "If both are not match then our TC is Fail");
		Reporter.log("Verify search username is present on this manage role page :- " + username_Merchant, true);

	}

	public void verifyIfUserEnterWrongUsername() {
		Assert.assertTrue(actionDriver.isDisplayed(noRecordsFound));
		Reporter.log("If user enter any wrong username or merchant name then No records found is successfully displays",
				true);
	}

	public void refreshSearchPage(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("location.reload();");

		Assert.assertTrue(driver.getTitle().contains("Manage Roles - Paysecure"));

	}

	public void clickOnAddRole() {
		actionDriver.click(adddRole);
		Reporter.log("User Click on add Roles", true);

	}

	public void verifyAllErrorsInAddRole() {
		actionDriver.click(adddRole);
		Reporter.log("User Click on add rolRoles", true);

		actionDriver.click(addRoleButton);
		Reporter.log("User Click on add roll button", true);

	}

	// username -enter some text
	public void enterUsername(String username) {
		actionDriver.clearText(userName);
		actionDriver.enterText(userName, username);

	}

	// username error message
	public String getErrorMessage_Username(WebDriver driver, String errorXpath) {
		By errorLocator = By.xpath(errorXpath);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(errorLocator));
		return driver.findElement(errorLocator).getText();
	}

	// username error message validate
	public void validateUsernameError(WebDriver driver, String input, String expectedError, String errorXpath) {
		enterUsername(input);
		String actualError = getErrorMessage_Username(driver, errorXpath);
		if (!actualError.equals(expectedError)) {
			throw new AssertionError("Expected: " + expectedError + ", but got: " + actualError);
		}
	}

	// password
	public void enterPassword(String pass) {
		actionDriver.clearText(password);
		actionDriver.enterText(password, pass);

	}

	// password error message
	public String getErrorMessage_password(WebDriver driver, String errorXpath) {
		By errorLocator = By.xpath(errorXpath);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(errorLocator));
		return driver.findElement(errorLocator).getText();
	}

	// password error message validate
	public void validatePasswordError(WebDriver driver, String input, String expectedError, String errorXpath) {
		enterPassword(input); // ✅ FIXED
		String actualError = getErrorMessage_password(driver, errorXpath);
		if (!actualError.equals(expectedError)) {
			throw new AssertionError("Expected: " + expectedError + ", but got: " + actualError);
		}
	}

	public void validate_genPass(WebDriver driver) {

		actionDriver.click(genPass);
		Reporter.log("User click on the Gen Pass Button", true);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String value = (String) js.executeScript("return arguments[0].value;", PASSWORD);
		System.out.println("Value via JS: " + value);

		if (value != null && !value.trim().isEmpty()) {
			System.err.println("JS says:- Field has value");

			Reporter.log("User clicks on the Gen Pass Button then actually some new password will be generated: '"
					+ value + "'", true);

		}

	}

	// email
	public void enterEmailID(String email) {
		actionDriver.clearText(emailAddress);
		actionDriver.enterText(emailAddress, email);

	}

	// email error message
	public String getErrorMessage_email(WebDriver driver, String errorXpath) {
		By errorLocator = By.xpath(errorXpath);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(errorLocator));
		return driver.findElement(errorLocator).getText();
	}

	// email error message validate
	public void validateEmailError(WebDriver driver, String input, String expectedError, String errorXpath) {
		enterEmailID(input); // ✅ FIXED
		String actualError = getErrorMessage_password(driver, errorXpath);
		if (!actualError.equals(expectedError)) {
			throw new AssertionError("Expected: " + expectedError + ", but got: " + actualError);
		}
	}

	// select ROLE
	public void selectRole(String roles) {
//		Select s=new Select(ROLE);
//          List<WebElement> rol = s.getOptions();
//      for(WebElement r:rol) {
//    	  System.err.println(r.getText());
//      }
          
		actionDriver.selectByVisibleText(role, roles);

	}
	
	
	 public Map<String, String> createRole() throws InterruptedException {
	        String username = testData_CreateRoll.generateRandomUsername();
	        String pass     = testData_CreateRoll.generateRandomPassword();
	        String email    = testData_CreateRoll.generateRandomEmail();
	        String roles    = "MERCHANT";

	        actionDriver.enterText(userName, username);
	        actionDriver.click(genPass);
	        actionDriver.enterText(emailAddress, email);
	      
	       actionDriver.selectByVisibleText(role, roles);
	       
	       actionDriver.click(addRoleButton);


	        Thread.sleep(1500);
	      

	        Map<String, String> data = new HashMap<>();
	        data.put("username", username);
	        data.put("password", pass);
	        data.put("role", roles);
	        return data;
	    }


	 public void searchRoleAfterCreating(WebDriver driver) throws InterruptedException {
		
		 manageRoles_page mr=new manageRoles_page(driver);

		// Call the method and store the returned data
		Map<String, String> userDetails1 = mr.createRole();

		// Access individual values
		String username1 = userDetails1.get("username");
		String password = userDetails1.get("password");
		String role = userDetails1.get("role");

		// Example use
		System.err.println("Created user: " + username1);
		System.err.println("Password: " + password);
		System.err.println("Role: " + role);
	       
	        
	        actionDriver.click(search);
	        actionDriver.enterText(search, username1);
	        actionDriver.click(searchButton);
	    }

	

}
