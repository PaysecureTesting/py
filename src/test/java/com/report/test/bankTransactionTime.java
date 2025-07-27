package com.report.test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.paysecure.base.baseClass;
import com.paysecure.loginPage.loginPage;
import com.paysecure.utilities.DataProviders;
import com.paysecure_Report.pages.bank_Transaction_Time;
import com.paysecure_Report.pages.transactionPage;

public class bankTransactionTime extends baseClass {
	loginPage lp;
	transactionPage ts;
	bank_Transaction_Time bank;

	@BeforeMethod
	public void beforeMethod() throws IOException, Exception {

		lp = new loginPage(getDriver());
		lp.login("Suhas", "Nick@123");

		ts = new transactionPage(getDriver());

		bank = new bank_Transaction_Time(getDriver());
		bank.navigateUptoBankTransactionTimeReport(getDriver());
	}

	@Test
	public void verifyBankTransactionTimeReport() throws InterruptedException {
	
		bank.convertDateFormat();
		bank.selectDate(getDriver());
		bank.applyButton(getDriver());
		ts.selectDashboardusinAllBank(getDriver());
		bank.enterTimeIn_Transaction_Time_more_than(getDriver(), "0");
		bank.searchButton(getDriver());

	}
	
	@Test
	public void verify_transaction_Id() throws InterruptedException {
		bank.verifyTransactionID_bankTransactionTime();

	}

	@Test
	public void handleAllData_Pagination() throws InterruptedException {
		bank.iterateAllTablePages(getDriver());
	}
	

	@Test(dataProvider = "expectedHeadersProvider", dataProviderClass = DataProviders.class)
	public void verifyTableHeaders(List<String> expectedHeaders) {
	    
	    List<String> actualHeaders =bank.getTableHeaders(getDriver());

	    Assert.assertEquals(actualHeaders, expectedHeaders, "❌ Table headers do not match!");
	    System.out.println("✅ Headers match exactly!");
	}








	

	
	
	
	
}
