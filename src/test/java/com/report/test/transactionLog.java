package com.report.test;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.paysecure.base.baseClass;
import com.paysecure.loginPage.loginPage;
import com.paysecure.utilities.DataProviders;
import com.paysecure_Report.pages.bank_Transaction_Time;
import com.paysecure_Report.pages.transactionPage;
import com.paysecure_Report.pages.transaction_Log_page;

public class transactionLog extends baseClass {

	loginPage lp;
	transactionPage ts;
	transaction_Log_page tl;

	@BeforeMethod
	public void beforeMethod() throws IOException, Exception {

		lp = new loginPage(getDriver());
		lp.login("Suhas", "Nick@123");
		tl = new transaction_Log_page(getDriver());
		ts = new transactionPage(getDriver());
		tl.verifyUserIsOnTransactionLogPage(getDriver());
	}

	@Test
	public void verifyTransactionLog() throws InterruptedException {
	
		ts.selectDateRange(getDriver(), "Last 30 Days");
		tl.searchThroughPurchaseID(getDriver());
		tl.searchTransactionLog(getDriver());
		tl.click_downloadButton(getDriver());
		tl.verifyTransactionPurchaseDetails(getDriver());
	}

	
	@Test
	public void verify_purchaseIDJson() throws InterruptedException {
		
		ts.selectDateRange(getDriver(), "Last 30 Days");
		tl.searchThroughPurchaseID(getDriver());
		tl.searchTransactionLog(getDriver());
		
		tl.verifyPurchaseID(getDriver());
	}
	
	
	
	@Test(dataProvider ="Headers", dataProviderClass = DataProviders.class)
	public void verifyTableHeaders(List<String> expectedHeaders) {
	    
	    List<String> actualHeaders =tl.verifyHeaders(getDriver());

	    Assert.assertEquals(actualHeaders, expectedHeaders, "❌ Table headers do not match!");
	    System.out.println("✅ Headers match exactly!");
	}
	

}
