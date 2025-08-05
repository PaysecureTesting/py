package com.paysecure.admin;

import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.paysecure.Admin.pages.manageRoles_page;
import com.paysecure.base.baseClass;
import com.paysecure.loginPage.loginPage;
import com.paysecure.utilities.DataProviders;
import com.paysecure_Report.pages.transactionPage;

public class manageRoles extends baseClass {

	private loginPage lp;
	manageRoles_page mr;
	transactionPage ts;

	@BeforeMethod
	public void setupPages() {
		lp = new loginPage(getDriver());
		lp.login();
		mr = new manageRoles_page(getDriver());
		mr.navigateToManageRoles();
		ts=new transactionPage(getDriver());
	}

	@Test(priority = 0)
	public void verifySearchFunctionality() {
		mr.searchByUsername_Merchant("gouri_sharma");
		mr.verifySearchRoleSameorNot(getDriver(), "gouri_sharma");

	}

	@Test(priority = 1)
	public void verifySearchFunctionalityIfUserEnterWrongMerchant_Username() {
		mr.searchByUsername_Merchant("(*&^%$#$%^&*hgvhf)(*&^%^&*");
		mr.verifyIfUserEnterWrongUsername();

	}

	@Test(priority = 2)
	public void verifyRefreshPage_SearchFunctionalityIfUserEnterWrongMerchant_Username() {
		mr.searchByUsername_Merchant("gouri_sharma");
		mr.refreshSearchPage(getDriver());

	}

	@Test(dataProvider = "usernameTestData", dataProviderClass = DataProviders.class,priority = 3)
	public void validateUsernameErrorMessages(String inputText, String expectedError, String errorXpath) {
		mr.verifyAllErrorsInAddRole();
		mr.validateUsernameError(getDriver(), inputText, expectedError, errorXpath);
	}

	@Test(dataProvider = "passwordTestData", dataProviderClass = DataProviders.class,priority = 4)
	public void validatePassswordErrorMessages(String inputText, String expectedError, String errorXpath) {
		mr.verifyAllErrorsInAddRole(); // Optional: ensure form is reset
		mr.validatePasswordError(getDriver(), inputText, expectedError, errorXpath); // ✅ Correct now
	}

	@Test(priority = 5)
	public void validateGenpassFunctionality() {

		mr.clickOnAddRole();
		mr.validate_genPass(getDriver());

	}

	@Test(dataProvider = "emailIDTestData", dataProviderClass = DataProviders.class,priority = 6)
	public void validateEmailFieldErrors(String emailInput, String expectedError, String errorXpath) {

		mr.verifyAllErrorsInAddRole();

		mr.validateEmailError(getDriver(), emailInput, expectedError, errorXpath);
	}
	
	@Test(priority = 7)
	public void select_Role() {
		
		mr.clickOnAddRole();
		mr.selectRole("ADMIN");
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
	
	
	@Test(priority = 8)
	public void create_Role() throws InterruptedException {
		mr.clickOnAddRole();
		mr.createRole();
		
        mr.searchRoleAfterCreating(getDriver());
		
	}
	
	@Test(priority = 9)
	public void verifyShowAllButton() {
		mr.verifyShowAllFunctionality();
		mr.searchByUsername_Merchant("(*&^%$#$%^&*hgvhf)(*&^%^&*");
		mr.verifyIfUserEnterWrongUsername();
		mr.ClickOnshowAllButton();
	}
	
	
	@Test(priority = 10)
	public void verifyUsernameOnDeletePopUp() {
		mr.searchRoleViaUsernameOrMercahntName("tomjerry");
		mr.validateUsernameWhileDeletePopUp();
		mr.validateDeleteRoleOnDeletePopUp();

	}
	
	@Test(priority = 11)
	public void deleteMercahnt() {
		
		mr.searchRoleViaUsernameOrMercahntName("tomjerry");
		mr.validateUsernameWhileDeletePopUp();
		mr.validateDeleteRoleOnDeletePopUp();
		mr.cancelButton();
		mr.validateUsernameWhileDeletePopUp();
		mr.deletePopUpButton();
		
	}
	
	
	@Test(dataProvider = "Roles", dataProviderClass = DataProviders.class,priority =12)
	public void ifRollIsMerchantThenCantDisplaysThatRollInAnotherRoll(String role) throws InterruptedException {
		mr.searchRoleViaUsernameOrMercahntName("wlnitendra");
		mr.getUsernameAndRolenameAfterSearchingRole();
		mr.refreshSearchPage(getDriver());
		mr.searchRoleViaGetUsernameOrmerchantName();
		Thread.sleep(2500);
		mr.displayByRoleDropdown(role);
		mr.checkTransactionStatus(getDriver());
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
	
	@Test
	public void verifyUserIDInResetPasswordFunctionality() throws InterruptedException {
		
		mr.searchRoleViaUsernameOrMercahntName("tomjerry");
		mr.getUsernameAndRolenameAfterSearchingRole();
		mr.verifyUserIDInResetPassword();
		mr.verifyUserIDSameOnAfterSearchingAndResetPopUp();

	
		mr.verifyResetPasswordMessage();
	//	mr.clickOnUpdateButton();
		
		
	}
	
	
	
	
	

}
