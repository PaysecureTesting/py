package com.paysecure.admin;

import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.paysecure.Admin.pages.manageRoles_page;
import com.paysecure.base.baseClass;
import com.paysecure.loginPage.loginPage;
import com.paysecure.utilities.DataProviders;

public class manageRoles extends baseClass {

	private loginPage lp;
	manageRoles_page mr;

	@BeforeMethod
	public void setupPages() {
		lp = new loginPage(getDriver());
		lp.login("Suhas", "Nick@123");
		mr = new manageRoles_page(getDriver());
		mr.navigateToManageRoles();
	}

	@Test
	public void verifySearchFunctionality() {
		mr.searchByUsername_Merchant("gouri_sharma");
		mr.verifySearchRoleSameorNot(getDriver(), "gouri_sharma");

	}

	@Test
	public void verifySearchFunctionalityIfUserEnterWrongMerchant_Username() {
		mr.searchByUsername_Merchant("(*&^%$#$%^&*hgvhf)(*&^%^&*");
		mr.verifyIfUserEnterWrongUsername();

	}

	@Test
	public void verifyRefreshPage_SearchFunctionalityIfUserEnterWrongMerchant_Username() {
		mr.searchByUsername_Merchant("gouri_sharma");
		mr.refreshSearchPage(getDriver());

	}

	@Test(dataProvider = "usernameTestData", dataProviderClass = DataProviders.class)
	public void validateUsernameErrorMessages(String inputText, String expectedError, String errorXpath) {
		mr.verifyAllErrorsInAddRole();
		mr.validateUsernameError(getDriver(), inputText, expectedError, errorXpath);
	}

	@Test(dataProvider = "passwordTestData", dataProviderClass = DataProviders.class)
	public void validatePassswordErrorMessages(String inputText, String expectedError, String errorXpath) {
		mr.verifyAllErrorsInAddRole(); // Optional: ensure form is reset
		mr.validatePasswordError(getDriver(), inputText, expectedError, errorXpath); // ✅ Correct now
	}

	@Test
	public void validateGenpassFunctionality() {

		mr.clickOnAddRole();
		mr.validate_genPass(getDriver());

	}

	@Test(dataProvider = "emailIDTestData", dataProviderClass = DataProviders.class)
	public void validateEmailFieldErrors(String emailInput, String expectedError, String errorXpath) {

		mr.verifyAllErrorsInAddRole();

		mr.validateEmailError(getDriver(), emailInput, expectedError, errorXpath);
	}
	
	@Test
	public void select_Role() {
		
		mr.clickOnAddRole();
		mr.selectRole("WHITELABEL");
		/*** select or pick up any one from it 
		 MERCHANT	P2PMERCHANT  AGENT  WHITELABEL
		P2PAGENT
		REPORTADMIN
		ADMIN
		MIDADMIN
		ACCOUNTADMIN
		FULLADMIN
		WHITELABELMERCHANT*/

	}
	
	
	@Test(invocationCount =2)
	public void create_Role() throws InterruptedException {
		mr.clickOnAddRole();
		mr.createRole();
		Thread.sleep(20000);
        mr.searchRoleAfterCreating(getDriver());;
		
	}
	
	
	
	//tomjerry_1a5
	
	
	
	
	
	
	
	

}
