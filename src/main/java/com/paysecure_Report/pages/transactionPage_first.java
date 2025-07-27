package com.paysecure_Report.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.paysecure.actiondriver.ActionDriver;
import com.paysecure.bankPage.allBanks;

public class transactionPage_first {

	
	private WebDriver driver;
		
		
		//select bank
		private By selectbank = By.xpath("//span[@id='select2-bnk-container']");////span[text()='Select Bank']
		private By searchFieldBank = By.xpath("//input[@class='select2-search__field']");
		private By selectBankFromSearchFiled = By.xpath("//ul[@id='select2-bnk-results']/li");
		
		private By bankNames=By.xpath("//select[@id='bnk']");
		@FindBy(xpath="//select[@id='bnk']") private WebElement BANKNAMES;
		
	
		
		
		
		
		
		
		int quantity;
	
		private ActionDriver actionDriver;
		public transactionPage_first(WebDriver driver) {
			PageFactory.initElements(driver, this);
			this.actionDriver = new ActionDriver(driver);
		}
	
	
	
	public void getAllBankName(WebDriver driver) throws InterruptedException {
		
		actionDriver.click(selectbank);
		Thread.sleep(3000);
		actionDriver.click(selectbank);
	Select s=new Select(BANKNAMES);
	List<WebElement> name = s.getOptions();
	//System.out.println(name.size());
		for(WebElement bankName:name) {
			System.err.println("All bank name Is :- " + bankName.getText());
			
		}
		
		 quantity = name.size();
		 System.out.println("Total bank name Quantity :- " +quantity);
	}
	
	
	public void verifyQuantityOfBanks() {
		
		int totalQuantity = quantity-1;
		System.err.println(totalQuantity);
		
	int finalQuantity = allBanks.num;
		
	System.out.println();
		
	
		
		Assert.assertEquals(totalQuantity,finalQuantity);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
