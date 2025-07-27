package com.report.test;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.paysecure.base.baseClass;
import com.paysecure.loginPage.loginPage;
import com.paysecure_Report.pages.apiLog_page;
import com.paysecure_Report.pages.bank_Transaction_Time;
import com.paysecure_Report.pages.errorLog_Page;
import com.paysecure_Report.pages.transactionPage;

public class errorLog extends baseClass {

	loginPage lp;
	transactionPage ts;
	errorLog_Page error;

	@BeforeMethod
	public void beforeMethod() throws IOException, Exception {

		lp = new loginPage(getDriver());
		lp.login("Suhas", "Nick@123");
		error = new errorLog_Page(getDriver());

		ts = new transactionPage(getDriver());
		error.navigateUptoErrorLog();
	}

	@Test
	public void f() throws InterruptedException {

		ts.selectDateRange(getDriver(),"Last 30 Days");
		error.selectDashboardusinAllMerchant(getDriver(),"M","mukesh1");
		error.search_Merchant_Ref_Id();
		error.search_Purchase_Id();
		error.searchButton();

	}
}
