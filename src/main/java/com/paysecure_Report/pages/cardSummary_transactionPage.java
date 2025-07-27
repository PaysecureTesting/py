package com.paysecure_Report.pages;

import java.time.Duration;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.paysecure.actiondriver.ActionDriver;

public class cardSummary_transactionPage {

	public String clickedTxnId ="";
	public String merchantName ="";
	public static String clickedTxn___Id ="";

	// navigate upto Bank Transaction Time Report
	private By analytics = By.xpath("//span[text()='Analytics']");
	private By report = By.xpath("(//span[text()='Report'])[2]");
	private By showing = By.xpath("//label[@id='dailypagtext']");

	// card summary page

	private By TID = By.xpath("//h5[@id='purchaseInfo']");
	private By lastAction_ID=By.xpath("//span[@id='lAhistory']");
	private By mer_Name=By.xpath("//tbody[@id='lastActionList']/descendant::tr[2]/td[2]");

	// cancel button - transaction --card summary page
	private By cancelButton = By.xpath("//h5[@id='purchaseInfo']/following-sibling::button");
	
	//purchaseID
	private By PID=By.xpath("//span[@id='phistory']");
	private By PHID=By.xpath("//tbody[@id='analyticTransStats1']//button[contains(@onclick,'showStatusHistory')]");

	private ActionDriver actionDriver;

	// page factory constructor
	public cardSummary_transactionPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.actionDriver = new ActionDriver(driver);
	}

	public void clickOnCard(WebDriver driver, String targetCard) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		boolean card = false;

		while (true) {
			// Wait and get all rows in the current page
			List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
					By.xpath("(//th[text()='Transaction ID'])[1]/ancestor::table//td[7]")));

			for (WebElement row : rows) {

				actionDriver.scrollToElement(showing);

				List<WebElement> columns = row
						.findElements(By.xpath("(//th[text()='Transaction ID'])[1]/ancestor::table//td[7]"));

				for (WebElement cell : columns) {
					String cellText = cell.getText().trim();

					if (cellText.equalsIgnoreCase(targetCard)) {
						System.out.println("✅ Found card: " + cellText + " - Clicking on first occurrence.");

						try {
							// Try clicking the element (plain cell or anchor)
							if (cell.findElements(By.tagName("a")).size() > 0) {
								cell.findElement(By.tagName("a")).click(); // If it's a link
							} else {
								cell.click(); // Otherwise, click the cell itself
							}
						} catch (Exception e) {
							System.out.println("⚠️ Error clicking on card: " + e.getMessage());
						}

						card = true;
						break; // Exit inner loop
					}
				}

				if (card)
					break; // Exit row loop
			}

			if (card)
				break; // Exit page loop

			// Go to next page if email not found yet
			WebElement nextButtonContainer = driver.findElement(By.xpath("//li[@class='page-item next']"));

			String classAttr = nextButtonContainer.getAttribute("class");

			if (classAttr.contains("page-item next disabled")) {
				System.out.println("❌ Card not found in any page.");
				break;
			}

			// Click on Next button
			WebElement nextBtn = nextButtonContainer.findElement(By.tagName("a"));

			nextBtn.click();

			// Wait for table refresh
			// wait.until(ExpectedConditions.stalenessOf(rows.get(0)));
		}

		Assert.assertTrue(card, "❌ Card '" + targetCard + "' not found in any page.");

	}

	public void clickFirstValidTransaction(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		boolean clicked = false;

		while (true) {
			List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
					By.xpath("//table[@id='tabel22']/tbody[@id='analyticTransStats1']/tr")));

			for (WebElement row : rows) {
				// Get LastStatus text (10th column)
				String status = row.findElement(By.xpath("(//th[text()='Transaction ID'])[2]/ancestor::table//td[10]"))
						.getText().trim();

				// Skip EXPIRED rows
				if (status.equalsIgnoreCase("EXPIRED")) {
					continue;
				}

				// Get Transaction ID cell (3rd column)
				WebElement txnIdCell = row
						.findElement(By.xpath("(//th[text()='Transaction ID'])[2]/ancestor::table//td[3]"));
				List<WebElement> links = txnIdCell.findElements(By.tagName("a"));

				if (!links.isEmpty()) {
					WebElement txnLink = links.get(0);

					// Store Transaction ID text
					this.clickedTxnId = txnLink.getText().trim();

					// Print before clicking
					System.out.println("Clicking Transaction ID: " + clickedTxnId);
					txnLink.click();
					clicked = true;
					break;
				}
			}

			if (clicked) {
				break;
			}

			// Check if there's a next page
			WebElement nextButtonContainer = driver.findElement(By.xpath("//li[@class='page-item next']"));
			String classAttr = nextButtonContainer.getAttribute("class");

			if (classAttr.contains("disabled")) {
				System.out.println("❌ No valid transaction found.");
				break;
			}

			// Go to next page
			nextButtonContainer.findElement(By.tagName("a")).click();
		}

		Assert.assertTrue(clicked, "❌ No clickable Transaction ID with valid status found.");
	}

	public void verifyTransactionIN_CardPage() {

		String tran_ID = actionDriver.getText(TID);

		Assert.assertEquals(tran_ID, this.clickedTxnId, "If Both transaction ID is not match then our TC is fail");

		actionDriver.click(cancelButton);
	}

	 // Global to compare later

	public void clickOnActionColumn(WebDriver driver) throws InterruptedException {
	    WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(15));
	    boolean actionClicked = false;

	    List<WebElement> rows = w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
	        By.xpath("//table[@id='tabel22']//tbody[@id='analyticTransStats1']/tr")));

	    for (WebElement r : rows) {
	        // ✅ Get Last Status from the current row's 10th column
	        String statusText = r.findElement(By.xpath("./td[10]")).getText().trim();

	        if (!statusText.equalsIgnoreCase("EXPIRED")) {
	            // ✅ Get Purchase ID from 3rd column of current row
	        	clickedTxnId = r.findElement(By.xpath("./td[3]")).getText().trim();
	        	Thread.sleep(1500);
	        	merchantName=r.findElement(By.xpath("./td[4]")).getText().trim();
	            System.out.println("✅ Found Purchase ID: " +clickedTxnId);

	            // ✅ Click Action button from same row
	            WebElement actionBtn = r.findElement(By.xpath("./td[1]//button[@class='btn btn-white btnMargin11']/i"));
	            System.out.println("🛠 Clicking Action for Purchase ID: " +clickedTxnId);
	            actionBtn.click();

	            actionClicked = true;
	            break;
	        }
	    }

	    // ✅ Assert outside loop
	    Assert.assertTrue(actionClicked, "❌ No Action button clicked — all transactions were expired.");
	}



	
	public void verifyPurchaseIDOnActionPage() {

		
		
		String p= actionDriver.getText(lastAction_ID);
		String p_ID= p.split(":")[1].trim();
		
		System.out.println("p_ID:-"+p_ID);
		
		Assert.assertEquals(clickedTxnId,p_ID, "If Both purchase ID is not matching then our TC is Failed");

	}
	
	public void verifymercahntNameOnActionPage() {

		
		
		String p= actionDriver.getText(mer_Name);
	//	String p_ID= p.split(":")[1].trim();
		
		System.out.println("p_ID:-"+p);
		
		Assert.assertEquals(merchantName,p, "If Both purchase ID is not matching then our TC is Failed");

	}
	
	
	
	public void clickOnHistoryColumn(WebDriver driver) {
	    WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(15));
	    boolean actionClicked = false;

	    List<WebElement> rows = w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
	        By.xpath("//tbody[@id='analyticTransStats1']/tr")));

	    for (WebElement r : rows) {
	        // ✅ Get Last Status from the 10th column (relative to current row)
	        String statusText = r.findElement(By.xpath("./td[10]")).getText().trim();

	        if (!statusText.equalsIgnoreCase("EXPIRED")) {
	            // ✅ Get Purchase ID from 3rd column (Transaction ID)
	        	clickedTxn___Id = r.findElement(By.xpath("./td[3]")).getText().trim();
	            System.out.println("✅ Found clickedTxn___Id: " +clickedTxn___Id);

	            // ✅ Click History button (assuming 2nd button in 1st column)
	            WebElement historyBtn = r.findElement(By.xpath(".//button[@title='History']"));
	            System.out.println("🛠 Clicking History button for clickedTxn___ID: " +clickedTxn___Id);
	            historyBtn.click();

	            actionClicked = true;
	            break;
	        }
	    }

	    // ✅ Final assertion
	    Assert.assertTrue(actionClicked, "❌ No Action button clicked — all transactions were expired.");
	}


	
public void verifyPurchaseIDOnHistoryPage() {

	String p= actionDriver.getText(PID);
	String p_ID= p.split(":")[1].trim();
	
	System.out.println("p_ID:-"+p_ID);
	
	Assert.assertEquals(clickedTxn___Id,p_ID, "If Both purchase ID is not matching then our TC is Failed");

	}
	

}
