package com.report.test;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.paysecure.base.baseClass;
import com.paysecure.loginPage.loginPage;
import com.paysecure_Report.pages.apiLog_page;
import com.paysecure_Report.pages.bank_Transaction_Time;
import com.paysecure_Report.pages.download_page;
import com.paysecure_Report.pages.transactionPage;
import com.paysecure_Report.pages.transaction_Log_page;


public class download extends baseClass {
  
	
	loginPage lp;
	transactionPage ts;
	bank_Transaction_Time bank;
	apiLog_page api;
	download_page download;
	transaction_Log_page tl;
	

	@BeforeMethod
	public void beforeMethod() throws IOException, Exception {

		lp = new loginPage(getDriver());
		lp.login("Suhas", "Nick@123");

		ts = new transactionPage(getDriver());

		download=new download_page(getDriver());
	
			tl=new transaction_Log_page(getDriver())	;
			

	}
	
	
	
	
	@Test
  public void downloadTransaction() throws InterruptedException {
		download.verifyUserIsOnDownloadPage(getDriver());
		ts.filterTransactionThroughSelectStatus("Error");
		ts.selectDashboardusinAllMerchant(getDriver());
		ts.selectDashboardusinAllBank(getDriver());
		ts.selectMID();
		ts.selectDateRange(getDriver(), "Yesterday");
		ts.selectTimeZone(getDriver());
		download.selectCurrency(getDriver(),"I", "INR");
		download.downloadPurchaseOrBanktransaction("Bank Transactions");
		tl.click_downloadButton(getDriver());
		download.noRecordsFound(getDriver());
		download.clickONOKButton(getDriver());

  }
}
