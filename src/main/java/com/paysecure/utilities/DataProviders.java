package com.paysecure.utilities;

import java.util.Arrays;
import java.util.List;
//hsgd kkqx jjho zghz
import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "cardNames")
	public Object[][] getCardNames() {
		return new Object[][] {
				// { "MOBILEMONEY" },
//			{ "INTERAC" }, 
				{ "PIX" },
//			{ "50785078******7812" }, 
//			{ "PAYID" },
//				{ "40840840****4081" }, 
//				{ "VIRTUAL-ACCOUNT" }, 
//				{"42639826****9299"},
//				{"INTERAC-REQUEST-MONEY"},
//				{"SPEI-CLABE"},
//				{"42424242****4242"},
		};
	}

	@DataProvider(name = "EmailID")
	public Object[][] getEmailID() {
		return new Object[][] { { "pr************* ..." },
				 {"cu*****r@e****. ..."},
//			{ "al********o@b** ..." },
//			{ "ra**l@p******** ..." }, 
//			{ "cu*****r@e****. ..." },
			//	{ "go************0 ..." },
//			{ "bh***********r@ ..." },

		};
	}

	@DataProvider(name = "expectedHeadersProvider")
	public Object[][] expectedHeadersProvider() {
		return new Object[][] {
				{ Arrays.asList("DATE", "BANK NAME", "TRANSACTION ID", "MERCHANT NAME", "TRANSACTION PROCESS TIME") } };
	}

	@DataProvider(name = "HeadersProvider")
	public Object[][] expectedHeadersProvider_APILog() {
		return new Object[][] { { Arrays.asList("DATE", "API PARAMETER", "RESPONSE", "REDIRECT API") } };
	}

	@DataProvider(name = "Headers")
	public Object[][] expectedHeadersProvider_transactionLog() {
		return new Object[][] { { Arrays.asList("DATE", "PURCHASE ID", "TEXT") } };
	}

	@DataProvider(name = "usernameTestData")
	public Object[][] getUsernameTestData() {
		return new Object[][] { { "", "This field is required",
				"//input[@id='username1']/following-sibling::span[contains(normalize-space(), 'field is required')]" },
				{ "abc", "Username should be min 5 character", "(//span[@class='text-danger'])[1]" },
				{ "abc der", "Space not allowed",
						"//input[@id='username1']/following-sibling::span[contains(normalize-space(), 'not allowed')]" } };
	}

	@DataProvider(name = "passwordTestData")
	public Object[][] getPasswordTestData() {
		return new Object[][] { { "", "This field is required",
				"//input[@id='password1']/ancestor::div[@class='form-group col-md-12']/div/following-sibling::span" },
				{ "a", "At least one small and one capital letter, one digit, one special character",
						"//input[@id='password1']/ancestor::div[@class='form-group col-md-12']//span[contains(normalize-space(), 'one small and one capital letter')]" },
				{ "a  r", "Space not allowed", "(//span[@class='text-danger ng-scope'])[3]" } };

	}

	@DataProvider(name = "emailIDTestData")
	public Object[][] emailProvider() {

		String requiredLocator = "//input[@id='emailaddress1']/following-sibling::span"
				+ "[contains(normalize-space(),'field is required')]";
		String invalidLocator = "//input[@id='emailaddress1']/following-sibling::span"
				+ "[contains(normalize-space(),'Email address')]";

	List<String> invalidEmails = Arrays.asList(
			
//			"suhas@.com", "@gmail.com",
//				"suhas@", "suhas", "suhas@ gmail.com", "suhas @gmail.com", "suhas@gmail..com", "suhas@gmail.c",
//				"suhas@gmail,com", "suhas@#mail.com", "suhas@@gmail.com", ".suhas@gmail.com", "suhas.@gmail.com",
//				"suhas..patil@gmail.com", "suhas@-gmail.com", "suhas@gmail-.com", "suhas@.gmail.com",
				"suhas@g_mail.com");

		/* +1 row for the “required” test case */
		Object[][] data = new Object[invalidEmails.size() + 1][3];

		// row 0 → required‑field case
		data[0] = new Object[] { "", "This field is required", requiredLocator };

		// the rest → invalid formats
		for (int i = 0; i < invalidEmails.size(); i++) {
			data[i + 1] = new Object[] { invalidEmails.get(i), "Invalid Email address", invalidLocator };
		}
		return data;
	}

	@DataProvider(name = "status")
	public Object[][] getStatus() {
		return new Object[][] { { "Error" }, { "Expired" }, { "Created" }, { "Payment_in_process" },

		};
	}

	@DataProvider(name = "status_PRC")
	public Object[][] getStatus_Paid_Refunded_Chargeback() {
		return new Object[][] {

				{ "Paid" }, { "Refunded" }, { "Chargeback" } };
	}

	@DataProvider(name = "bankName") // ✅ matches Test
	public Object[][] getbankName() {
		return new Object[][] {
	        {"a55"},
	        {"a55pix"},
	        {"a55pixpayout"}
		};
	}

	@DataProvider(name = "cardName")
	public Object[][] getCreditCard() {
		return new Object[][] {

				// {"22210081****7736"},

				{ "42424242****4242" },

//			{"44444444****4448"},
//			
//			{"55555555****5557"},
//			
//			{"52000000****0015"},
//			
//			{"42424242****4242"}

		};
	}

	@DataProvider(name = "cardNameText")
	public Object[][] getCreditCardText() {
		return new Object[][] {

				{ "SPEI" },

				// {"PAYID"},

		};

	}

	@DataProvider(name = "statusAction")
	public Object[][] Laststatus() {
		return new Object[][] {

				{ "Created" },
//
//			{"Pendingexecute"},
//			{"Payment_in_process"},
//			{"Paid"},
//			{"Viewed"},
//			{"Refunded"},

		};

	}

	@DataProvider(name = "Roles") // ✅ matches Test
	public Object[][] displayByRole_AdminPage() {
		return new Object[][] { 
			{"SUPERADMIN"},
			{ "MERCHANT" }, 
//			{ "P2PMERCHANT" }, 
//			{ "AGENT" },
//			{ "WHITELABEL" },
//			{ "P2PAGENT" },
//			{ "P2PMERCHANT" }, 
//			{ "REPORTADMIN" }, 
	//		{ "ADMIN" }, 
//			{ "MIDADMIN" }, 
//		    { "ACCOUNTADMIN" },
//			{ "FULLADMIN" },
//			{ "WHITELABELMERCHANT" }

		};
	}
	

	
	
	
	
	
	
	
	
	
	
	

}
