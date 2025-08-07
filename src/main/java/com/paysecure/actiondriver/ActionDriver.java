package com.paysecure.actiondriver;








import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.paysecure.base.baseClass;
import com.paysecure.utilities.ExtentManager;


public class ActionDriver {

	private WebDriver driver;
	private WebDriverWait wait;
	public static final Logger logger = baseClass.logger;

	public ActionDriver(WebDriver driver) {
		this.driver = driver;
		int explicitWait = Integer.parseInt(baseClass.getProp().getProperty("explicitWait"));
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));
		logger.info("WebDriver instance is created.");
	}

	public void click(By by) {
	    String elementDescription = getElementDescription(by);
	    try {
	        applyBorder(by, "green");

	        // Explicit wait for the element to be clickable
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));

	        element.click();

	        ExtentManager.logStep("Clicked an element: " + elementDescription);
	        logger.info("Clicked an element --> " + elementDescription);
	    } catch (Exception e) {
	        applyBorder(by, "red");
	        System.out.println("Unable to click element: " + e.getMessage());
	        ExtentManager.logFailure(baseClass.getDriver(), "Unable to click element:", elementDescription + " - unable to click");
	        logger.error("Unable to click element --> " + elementDescription, e);
	    }
	}



	// Method to enter text into an input field --Avoid Code Duplication - fix the
	// multiple calling method
	public void enterText(By by, String textToEnter) {
	    String elementDescription = getElementDescription(by);
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));

	        applyBorder(by, "green");

	        element.clear();
	        element.sendKeys(textToEnter);

	        logger.info("Entered text '" + textToEnter + "' on " + elementDescription);
	    } catch (Exception e) {
	        applyBorder(by, "red");
	        logger.error("Unable to enter text on " + elementDescription + ": " + e.getMessage(), e);
	    }
	}


	

	// Method to get text from an input field
	public String getText(By by) {
	    String elementDescription = getElementDescription(by);
	    try {
	        // Explicit wait for visibility of the element
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));

	        applyBorder(by, "green");

	        String text = element.getText();
	        logger.info("Fetched text from " + elementDescription + " --> " + text);
	        return text;
	    } catch (Exception e) {
	        applyBorder(by, "red");
	        logger.error("Unable to get the text from " + elementDescription + ": " + e.getMessage(), e);
	        return "";
	    }
	}


	// Method to compare Two text -- changed the return type
	public boolean compareText(By by, String expectedText) {
	    String elementDescription = getElementDescription(by);
	    try {
	        // Explicit wait for the element to be visible
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));

	        String actualText = element.getText().trim(); // Trimming to avoid false mismatches due to whitespace

	        if (expectedText.equals(actualText)) {
	            applyBorder(by, "green");
	            logger.info("Texts are matching: " + actualText + " equals " + expectedText);
	            ExtentManager.logStepWithScreenshot(baseClass.getDriver(), "Compare Text", 
	                "Text Verified Successfully! " + actualText + " equals " + expectedText);
	            return true;
	        } else {
	            applyBorder(by, "red");
	            logger.error("Texts are not matching: " + actualText + " not equals " + expectedText);
	            ExtentManager.logFailure(baseClass.getDriver(), "Text Comparison Failed!", 
	                "Text Comparison Failed! " + actualText + " not equals " + expectedText);
	            return false;
	        }
	    } catch (Exception e) {
	        applyBorder(by, "red");
	        logger.error("Unable to compare texts for " + elementDescription + ": " + e.getMessage(), e);
	        return false;
	    }
	}


	/*
	 * Method to check if an element is displayed public boolean isDisplayed(By by)
	 * { try { waitForElementToBeVisible(by); boolean isDisplayed =
	 * driver.findElement(by).isDisplayed(); if (isDisplayed) {
	 * System.out.println("Element is Displayed"); return isDisplayed; } else {
	 * return isDisplayed; } } catch (Exception e) {
	 * System.out.println("Element is not displayed:"+e.getMessage()); return false;
	 * } }
	 */

	// Simplified the method and remove redundant conditions
	public boolean isDisplayed(By by) {
	    String elementDescription = getElementDescription(by);
	    try {
	        // Wait explicitly for the element to be visible
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
	        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));

	        applyBorder(by, "green");
	        logger.info("Element is displayed: " + elementDescription);
	        ExtentManager.logStep("Element is displayed: " + elementDescription);
	        ExtentManager.logStepWithScreenshot(baseClass.getDriver(), "Element is displayed:", "Element is displayed: " + elementDescription);

	        return element.isDisplayed(); // Safe to call after visibility wait
	    } catch (Exception e) {
	        applyBorder(by, "red");
	        logger.error("Element is not displayed: " + e.getMessage(), e);
	        ExtentManager.logFailure(baseClass.getDriver(), "Element is not displayed:", "Element is not displayed: " + elementDescription);
	        return false;
	    }
	}
	
	
	//is enabled method
	public boolean isEnabled(By by) {
	    String elementDescription = getElementDescription(by);
	    try {
	        // Wait for the element to be present and visible
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));

	        boolean isEnabled = element.isEnabled();

	        if (isEnabled) {
	            applyBorder(by, "green");
	            logger.info("Element is enabled: " + elementDescription);
	            ExtentManager.logStep("Element is enabled: " + elementDescription);
	            ExtentManager.logStepWithScreenshot(baseClass.getDriver(), "Element is enabled", "Element is enabled: " + elementDescription);
	        } else {
	            applyBorder(by, "red");
	            logger.warn("Element is **disabled**: " + elementDescription);
	                                                             
	        }

	        return isEnabled;

	    } catch (Exception e) {
	        applyBorder(by, "red");
	        logger.error("Unable to determine if element is enabled: " + e.getMessage(), e);
	        ExtentManager.logFailure(baseClass.getDriver(), "Element is not enabled", "Element is not enabled: " + elementDescription);
	        return false;
	    }
	}

//is selected method 
	public boolean isSelected(By by) {
	    String elementDescription = getElementDescription(by);
	    try {
	        // Wait explicitly for element to be present in DOM (not necessarily visible)
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
	        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));

	        applyBorder(by, "blue"); // Different color to indicate selection check
	        boolean selected = element.isSelected();

	        if (selected) {
	            logger.info("Element is selected: " + elementDescription);
	            ExtentManager.logStep("Element is selected: " + elementDescription);
	            ExtentManager.logStepWithScreenshot(baseClass.getDriver(), "Element is selected", "Element is selected: " + elementDescription);
	        } else {
	            logger.warn("Element is NOT selected: " + elementDescription);
	        
	        }

	        return selected;
	    } catch (Exception e) {
	        applyBorder(by, "red");
	        logger.error("Unable to determine selection state: " + e.getMessage(), e);
	        ExtentManager.logFailure(baseClass.getDriver(), "Selection check failed", "Error checking isSelected for: " + elementDescription);
	        return false;
	    }
	}


	// Wait for the page to load
	public void waitForPageLoad(int timeOutInSec) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSec));
	        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
	                .executeScript("return document.readyState").equals("complete"));

	        logger.info("Page loaded successfully.");
	    } catch (Exception e) {
	        logger.error("Page did not load within " + timeOutInSec + " seconds. Exception: " + e.getMessage(), e);
	    }
	}


	// Scroll to an element -- Added a semicolon ; at the end of the script string
	public void scrollToElement(By by) {
	    String elementDescription = getElementDescription(by);
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by)); // 1

	        // Ensure element is in view — especially important in headless
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);// 2

	        // Wait again for visibility after scroll
	        wait.until(ExpectedConditions.visibilityOf(element)); // 

	        applyBorder(by, "green");
	        logger.info("Scrolled to element: " + elementDescription);
	    } catch (Exception e) {
	        applyBorder(by, "red");
	        logger.error("Unable to scroll to element: " + elementDescription + ". Exception: " + e.getMessage(), e);
	    }
	}



	// Wait for Element to be clickable
	private void waitForElementToBeClickable(WebDriver driver, By by, int timeoutInSeconds) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	        wait.until(ExpectedConditions.elementToBeClickable(by));
	    } catch (Exception e) {
	        logger.error("Element is not clickable within " + timeoutInSeconds + " seconds: " + e.getMessage());
	    }
	}


	// Wait for Element to be Visible
	private void waitForElementToBeVisible(WebDriver driver, By by, int timeoutInSeconds) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	    } catch (Exception e) {
	        logger.error("Element is not visible within " + timeoutInSeconds + " seconds: " + e.getMessage());
	    }
	}


	// Method to get the description of an element using By locator
	public String getElementDescription(By locator) {
	    if (driver == null) {
	        return "Driver is not initialized.";
	    }
	    if (locator == null) {
	        return "Locator is null.";
	    }

	    try {
	        // Wait for the element to be visible
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	        // Get element attributes
	        String name = element.getDomProperty("name");
	        String id = element.getDomProperty("id");
	        String text = element.getText();
	        String className = element.getDomProperty("class");
	        String placeholder = element.getDomProperty("placeholder");

	        // Return a description based on available attributes
	        if (isNotEmpty(name)) {
	            return "Element with name: " + name;
	        } else if (isNotEmpty(id)) {
	            return "Element with ID: " + id;
	        } else if (isNotEmpty(text)) {
	            return "Element with text: " + truncate(text, 50);
	        } else if (isNotEmpty(className)) {
	            return "Element with class: " + className;
	        } else if (isNotEmpty(placeholder)) {
	            return "Element with placeholder: " + placeholder;
	        } else {
	            return "Element located using: " + locator.toString();
	        }

	    } catch (TimeoutException e) {
	        return "Element not visible after waiting " + 10 + " seconds.";
	    } catch (Exception e) {
	        e.printStackTrace(); // Replace with logger
	        return "Unable to describe element due to error: " + e.getMessage();
	    }
	}


	// Utility method to check if a string is not null or empty
	private boolean isNotEmpty(String value) {
		return value != null && !value.isEmpty();
	}

	// Utility method to truncate long strings
	private String truncate(String value, int maxLength) {
		if (value == null || value.length() <= maxLength) {
			return value;
		}
		return value.substring(0, maxLength) + "...";
	}
	
	//Utility Method to Border an element
	public void applyBorder(By by,String color) {
		try {
			//Locate the element
			WebElement element = driver.findElement(by);
			//Apply the border
			String script = "arguments[0].style.border='3px solid "+color+"'";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(script, element);
			logger.info("Applied the border with color "+color+ " to element: "+getElementDescription(by));
		} catch (Exception e) {
			logger.warn("Failed to apply the border to an element: "+getElementDescription(by),e);
		}
	}
	
	 // ===================== Select Methods =====================
	
    // Method to select a dropdown by visible text
	public void selectByVisibleText(By by, String value) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by)); // Wait until dropdown is ready

	        new Select(element).selectByVisibleText(value);
	        applyBorder(by, "green");
	        logger.info("Selected dropdown value: " + value);
	    } catch (Exception e) {
	        applyBorder(by, "red");
	        logger.error("Unable to select dropdown value: " + value, e);
	    }
	}

    
    // Method to select a dropdown by value
	public void selectByValue(By by, String value) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by)); // Ensures it's visible and enabled

	        new Select(element).selectByValue(value);
	        applyBorder(by, "green");
	        logger.info("Selected dropdown value by actual value: " + value);
	    } catch (Exception e) {
	        applyBorder(by, "red");
	        logger.error("Unable to select dropdown by value: " + value, e);
	    }
	}

    
    // Method to select a dropdown by index
	public void selectByIndex(By by, int index) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by)); // Wait until dropdown is ready

	        new Select(element).selectByIndex(index);
	        applyBorder(by, "green");
	        logger.info("Selected dropdown value by index: " + index);
	    } catch (Exception e) {
	        applyBorder(by, "red");
	        logger.error("Unable to select dropdown by index: " + index, e);
	    }
	}

    
 // Method to get all options from a dropdown
	public List<String> getDropdownOptions(By by) {
	    List<String> optionsList = new ArrayList<>();
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(by));

	        Select select = new Select(dropdownElement);
	        for (WebElement option : select.getOptions()) {
	            optionsList.add(option.getText().trim());
	        }

	        applyBorder(by, "green");
	        logger.info("Retrieved dropdown options for " + getElementDescription(by));
	    } catch (Exception e) {
	        applyBorder(by, "red");
	        logger.error("Unable to get dropdown options: " + e.getMessage());
	    }
	    return optionsList;
	}

	public String getFirstSelectedOption(By by) {
	    String selectedOptionText = "";
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(by));

	        Select select = new Select(dropdownElement);
	        WebElement selectedOption = select.getFirstSelectedOption();
	        selectedOptionText = selectedOption.getText().trim();

	        applyBorder(by, "green");
	        logger.info("Selected option for " + getElementDescription(by) + ": " + selectedOptionText);
	    } catch (Exception e) {
	        applyBorder(by, "red");
	        logger.error("Unable to get selected dropdown option: " + e.getMessage());
	    }
	    return selectedOptionText;
	}

    
    // ===================== JavaScript Utility Methods =====================
    
	public void clickUsingJS(By by) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));

	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	        applyBorder(by, "green");
	        logger.info("Clicked element using JavaScript: " + getElementDescription(by));
	    } catch (Exception e) {
	        applyBorder(by, "red");
	        logger.error("Unable to click using JavaScript on element: " + by.toString(), e);
	    }
	}

    
    // Method to scroll to the bottom of the page
    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        logger.info("Scrolled to the bottom of the page.");
    }
    
    // Method to highlight an element using JavaScript
    public void highlightElementJS(By by) {
        try {
            WebElement element = driver.findElement(by);
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid yellow'", element);
            logger.info("Highlighted element using JavaScript: " + getElementDescription(by));
        } catch (Exception e) {
            logger.error("Unable to highlight element using JavaScript", e);
        }
    }
    
    // ===================== Window and Frame Handling =====================
    
    // Method to switch between browser windows
    public void switchToWindow(String windowTitle) {
        try {
            Set<String> windows = driver.getWindowHandles();
            for (String window : windows) {
                driver.switchTo().window(window);
                if (driver.getTitle().equals(windowTitle)) {
                    logger.info("Switched to window: " + windowTitle);
                    return;
                }
            }
            logger.warn("Window with title " + windowTitle + " not found.");
        } catch (Exception e) {
            logger.error("Unable to switch window", e);
        }
    }
    
    // Method to switch to an iframe
    public void switchToFrame(By by) {
        try {
            driver.switchTo().frame(driver.findElement(by));
            logger.info("Switched to iframe: " + getElementDescription(by));
        } catch (Exception e) {
            logger.error("Unable to switch to iframe", e);
        }
    }
    
    // Method to switch back to the default content
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
        logger.info("Switched back to default content.");
    }
    
    // ===================== Alert Handling =====================
    
    // Method to accept an alert popup
    public void acceptAlert() {
        try {
            driver.switchTo().alert().accept();
            logger.info("Alert accepted.");
        } catch (Exception e) {
            logger.error("No alert found to accept", e);
        }
    }
    
    // Method to dismiss an alert popup
    public void dismissAlert() {
        try {
            driver.switchTo().alert().dismiss();
            logger.info("Alert dismissed.");
        } catch (Exception e) {
            logger.error("No alert found to dismiss", e);
        }
    }
    
    // Method to get alert text
    public String getAlertText() {
        try {
            return driver.switchTo().alert().getText();
        } catch (Exception e) {
            logger.error("No alert text found", e);
            return "";
        }
    }
    
    // ===================== Browser Actions =====================
    
    public void refreshPage() {
        try {
            driver.navigate().refresh();
            ExtentManager.logStep("Page refreshed successfully.");
            logger.info("Page refreshed successfully.");
        } catch (Exception e) {
            ExtentManager.logFailure(baseClass.getDriver(), "Unable to refresh page", "refresh_page_failed");
            logger.error("Unable to refresh page: " + e.getMessage());
        }
    }

    public String getCurrentURL() {
        try {
            String url = driver.getCurrentUrl();
            ExtentManager.logStep("Current URL fetched: " + url);
            logger.info("Current URL fetched: " + url);
            return url;
        } catch (Exception e) {
            ExtentManager.logFailure(baseClass.getDriver(), "Unable to fetch current URL", "get_current_url_failed");
            logger.error("Unable to fetch current URL: " + e.getMessage());
            return null;
        }
    }

    public void maximizeWindow() {
        try {
            driver.manage().window().maximize();
            ExtentManager.logStep("Browser window maximized.");
            logger.info("Browser window maximized.");
        } catch (Exception e) {
            ExtentManager.logFailure(baseClass.getDriver(), "Unable to maximize window", "maximize_window_failed");
            logger.error("Unable to maximize window: " + e.getMessage());
        }
    }
    
 // ===================== Advanced WebElement Actions =====================

    public void moveToElement(By by) {
        String elementDescription = getElementDescription(by); // Description uses same timeout
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(0));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by)); // Wait for visibility

            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();

            ExtentManager.logStep("Moved to element: " + elementDescription);
            logger.info("Moved to element --> " + elementDescription);
        } catch (Exception e) {
            ExtentManager.logFailure(baseClass.getDriver(), "Unable to move to element", elementDescription + "_move_failed");
            logger.error("Unable to move to element: " + e.getMessage());
        }
    }


    public void dragAndDrop(By source, By target) {
        String sourceDescription = getElementDescription(source);
        String targetDescription = getElementDescription(target);
        try {
            Actions actions = new Actions(driver);
            actions.dragAndDrop(driver.findElement(source), driver.findElement(target)).perform();
            ExtentManager.logStep("Dragged element: " + sourceDescription + " and dropped on " + targetDescription);
            logger.info("Dragged element: " + sourceDescription + " and dropped on " + targetDescription);
        } catch (Exception e) {
            ExtentManager.logFailure(baseClass.getDriver(), "Unable to drag and drop", sourceDescription + "_drag_failed");
            logger.error("Unable to drag and drop: " + e.getMessage());
        }
    }

    public void doubleClick(By by) {
        String elementDescription = getElementDescription(by);
        try {
            Actions actions = new Actions(driver);
            actions.doubleClick(driver.findElement(by)).perform();
            ExtentManager.logStep("Double-clicked on element: " + elementDescription);
            logger.info("Double-clicked on element --> " + elementDescription);
        } catch (Exception e) {
            ExtentManager.logFailure(baseClass.getDriver(), "Unable to double-click element", elementDescription + "_doubleclick_failed");
            logger.error("Unable to double-click element: " + e.getMessage());
        }
    }

    public void rightClick(By by) {
        String elementDescription = getElementDescription(by);
        try {
            Actions actions = new Actions(driver);
            actions.contextClick(driver.findElement(by)).perform();
            ExtentManager.logStep("Right-clicked on element: " + elementDescription);
            logger.info("Right-clicked on element --> " + elementDescription);
        } catch (Exception e) {
            ExtentManager.logFailure(baseClass.getDriver(), "Unable to right-click element", elementDescription + "_rightclick_failed");
            logger.error("Unable to right-click element: " + e.getMessage());
        }
    }

    public void sendKeysWithActions(By by, String value) {
        int timeoutInSeconds = 30;
        String elementDescription = getElementDescription(by);
        
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by)); // Wait for element to be visible

            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().sendKeys(value).perform();

            ExtentManager.logStep("Sent keys to element: " + elementDescription + " | Value: " + value);
            logger.info("Sent keys to element --> " + elementDescription + " | Value: " + value);
        } catch (Exception e) {
            ExtentManager.logFailure(baseClass.getDriver(), "Unable to send keys", elementDescription + "_sendkeys_failed");
            logger.error("Unable to send keys to element: " + e.getMessage());
        }
    }


    public void clearText(By by) {
        String elementDescription = getElementDescription(by);
        try {
            driver.findElement(by).clear();
            ExtentManager.logStep("Cleared text in element: " + elementDescription);
            logger.info("Cleared text in element --> " + elementDescription);
        } catch (Exception e) {
            ExtentManager.logFailure(baseClass.getDriver(), "Unable to clear text", elementDescription + "_clear_failed");
            logger.error("Unable to clear text in element: " + e.getMessage());
        }
    }   
    
 // Method to upload a file
    public void uploadFile(By by, String filePath) {
        try {
            driver.findElement(by).sendKeys(filePath);
            applyBorder(by, "green");
            logger.info("Uploaded file: " + filePath);
        } catch (Exception e) {
            applyBorder(by, "red");
            logger.error("Unable to upload file: " + e.getMessage());
        }
    }  
    
}
