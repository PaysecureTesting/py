package com.paysecure.banks;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.paysecure.Admin.pages.manageRoles_page;
import com.paysecure.bankPage.allBanks;
import com.paysecure.base.baseClass;
import com.paysecure.loginPage.loginPage;
import com.paysecure_Report.pages.transactionPage;

public class allbanks extends baseClass {

	private loginPage lp;
	manageRoles_page mr;
	transactionPage ts;
	allBanks banks;


	@BeforeMethod
	public void setupPages() {
		lp = new loginPage(getDriver());
		lp.login();

		banks = new allBanks(getDriver());
		banks.navigateUptoAllBanks();
		ts = new transactionPage(getDriver());
		mr=new manageRoles_page(getDriver());
		
	}

	@Test(priority = 0)
	public void verifyAddbank() {
		banks.VerifyAddBank();
	
	}
	
	@Test (priority = 1)
	public void clearTheSearchFieldAndCheckIt() throws InterruptedException {
		banks.searchBank("SA3434434");
		banks.checkBankStatusAfterSearchingBank();
		banks.clearTheSearchFieldAndCheck();
		banks.checkBankStatusAfterSearchingBank();
	}
	
	@Test (priority = 2)
	public void afterSearchingRefreshThePage() {
		banks.searchBank("CA");
		banks.checkBankStatusAfterSearchingBank();
		mr.refreshSearchPage(getDriver());
		banks.checkBankStatusAfterSearchingBank();
	}
	
	@Test (priority = 3)
	public void validateShowAllButtonIsAlreadySelected() throws InterruptedException {
		banks.verifyTableHeaderInAllBanks();
		banks.showAllBanks();

	}
	
	@Test (priority = 4)
	public void checkActiveStatusActiveAllBanks() throws InterruptedException {
		banks.verifyTableHeaderInAllBanks();
		banks.verifyActiveStatusForActivePage();
	}
	
	@Test (priority = 5)
   public void checkInactiveStatusInactiveInAllBanks() throws InterruptedException {
		banks.verifyTableHeaderInAllBanks();
		banks.verifyInActiveStatusForAllInActivePage();
	}
	
	@Test (priority = 6)
	public void verifyTableHeaderInAllbanks() {
		
		banks.verifyTableHeaderInAllBanks();
	}
	
	@Test (priority = 7)
	public void validateForActiveAndInactiveBank() {
		banks.showAllBanks();
		banks.checkForActiveButton();
		banks.checkForInactiveButton();
		banks.totalbanksInAllBanks();
		
	}
	
	@Test(enabled =false)
	public void get_All_Bank_Name() throws InterruptedException {
		banks.getAllBankName(getDriver());
		banks.navigateUptoAllBanks();
		banks.getQuantityOfAllBank();
		banks.verifyQuantityOfBanks();

	}
	
}
