package com.paysecure.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.paysecure.actiondriver.ActionDriver;
import com.paysecure.utilities.ExtentManager;
import com.paysecure.utilities.LoggerManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class baseClass {

	protected static Properties prop;
	// protected static WebDriver driver;
	// private static ActionDriver actionDriver;

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private static ThreadLocal<ActionDriver> actionDriver = new ThreadLocal<>();
	public static final Logger logger = LoggerManager.getLogger(baseClass.class);

	protected ThreadLocal<SoftAssert> softAssert = ThreadLocal.withInitial(SoftAssert::new);

	// Getter method for soft assert
	public SoftAssert getSoftAssert() {
		return softAssert.get();
	}

	@BeforeSuite
	public void loadConfig() throws IOException {
		// Load the configuration file
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\LENOVO\\Downloads\\Payse-main\\Payse-main\\src\\main\\resources\\config.properties");
		prop.load(fis);
		logger.info("config.properties file loaded");

		// Start the Extent Report
		// ExtentManager.getReporter(); --This has been implemented in TestListener
	}

	@BeforeMethod
	public synchronized void setup() throws IOException {
		System.out.println("Setting up WebDriver for:" + this.getClass().getSimpleName());
		launchBrowser();
		configureBrowser();
		staticWait(2);
		// Sample logger message
		logger.info("WebDriver Initialized and Browser Maximized");
		logger.trace("This is a Trace message");
		logger.error("This is a error message");
		logger.debug("This is a debug message");
		logger.fatal("This is a fatal message");
		logger.warn("This is a warm message");

		/*
		 * // Initialize the actionDriver only once if (actionDriver == null) {
		 * actionDriver = new ActionDriver(driver);
		 * logger.info("ActionDriver instance is created. "+Thread.currentThread().getId
		 * ()); }
		 */

		// Initialize ActionDriver for the current Thread
		actionDriver.set(new ActionDriver(getDriver()));
		logger.info("ActionDriver initlialized for thread: " + Thread.currentThread().getId());

	}

	/*
	 * Initialize the WebDriver based on browser defined in config.properties file
	 */
	private synchronized void launchBrowser() {

		String browser = prop.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {
		    WebDriverManager.chromedriver().setup();
		    ChromeOptions options = new ChromeOptions();

		  
	        //    options.addArguments("--headless");
	            options.addArguments("--disable-gpu");
	            options.addArguments("--window-size=1920,1080");
	            options.addArguments("--disable-notifications");
	            options.addArguments("--no-sandbox");
	            options.addArguments("--disable-dev-shm-usage");

		    // ✅ Disable credential prompts
		    Map<String, Object> prefs = new HashMap<>();
		    prefs.put("credentials_enable_service", false);
		    prefs.put("profile.password_manager_enabled", false);
		    options.setExperimentalOption("prefs", prefs);

		    // ✅ Set driver
		    driver.set(new ChromeDriver(options));
		    ExtentManager.registerDriver(getDriver());
		    logger.info("ChromeDriver Instance is created in headless mode.");
		} else if (browser.equalsIgnoreCase("firefox")) {
	        WebDriverManager.firefoxdriver().setup();
	        FirefoxOptions options = new FirefoxOptions();
	        options.addArguments("--headless");
	        options.addArguments("--disable-gpu");
	        options.addArguments("--width=1920");
	        options.addArguments("--height=1080");
	        options.addArguments("--disable-notifications");
	        options.addArguments("--no-sandbox");
	        options.addArguments("--disable-dev-shm-usage");

	        driver.set(new FirefoxDriver(options));
	        ExtentManager.registerDriver(getDriver());
	        logger.info("FirefoxDriver Instance is created.");

	    } else if (browser.equalsIgnoreCase("edge")) {
	        WebDriverManager.edgedriver().setup();
	        EdgeOptions options = new EdgeOptions();
	        options.addArguments("--headless");
	        options.addArguments("--disable-gpu");
	        options.addArguments("--window-size=1920,1080");
	        options.addArguments("--disable-notifications");
	        options.addArguments("--no-sandbox");
	        options.addArguments("--disable-dev-shm-usage");

	        driver.set(new EdgeDriver(options));
	        ExtentManager.registerDriver(getDriver());
	        logger.info("EdgeDriver Instance is created.");

	    } else {
	        throw new IllegalArgumentException("Browser Not Supported: " + browser);
	    }
	}

	/*
	 * Configure browser settings such as implicit wait, maximize the browser and
	 * navigate to the URL
	 */

	private void configureBrowser() {
		// Implicit Wait
		int implicitWait = Integer.parseInt(prop.getProperty("implicitWait"));
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

		// maximize the browser
		getDriver().manage().window().maximize();

		// Navigate to URL
		try {
			getDriver().get(prop.getProperty("url"));
		} catch (Exception e) {
			System.out.println("Failed to Navigate to the URL:" + e.getMessage());
		}
	}

	@AfterSuite
	public synchronized void tearDown() {
		if (getDriver() != null) {
			try {
				// getDriver().quit();
			} catch (Exception e) {
				System.out.println("unable to quit the driver:" + e.getMessage());
			}
		}
		logger.info("WebDriver instance is closed.");
		driver.remove();
		actionDriver.remove();
		// driver = null;
		// actionDriver = null;
		// ExtentManager.endTest(); --This has been implemented in TestListener
	}

	/*
	 * 
	 * 
	 * //Driver getter method public WebDriver getDriver() { return driver; }
	 */

	// Getter Method for WebDriver
	public static WebDriver getDriver() {

		if (driver.get() == null) {
			System.out.println("WebDriver is not initialized");
			throw new IllegalStateException("WebDriver is not initialized");
		}
		return driver.get();

	}

	// Getter Method for ActionDriver
	public static ActionDriver getActionDriver() {

		if (actionDriver.get() == null) {
			System.out.println("ActionDriver is not initialized");
			throw new IllegalStateException("ActionDriver is not initialized");
		}
		return actionDriver.get();

	}

	// Getter method for prop
	public static Properties getProp() {
		return prop;
	}

	// Driver setter method
	public void setDriver(ThreadLocal<WebDriver> driver) {
		this.driver = driver;
	}

	// Static wait for pause
	public void staticWait(int seconds) {
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
	}
	
	@AfterMethod
	public void failurMethod(ITestResult result) {
	    if (ITestResult.FAILURE == result.getStatus()) {
	        try {
	            TakesScreenshot screenshot = (TakesScreenshot) getDriver();
	            File src = screenshot.getScreenshotAs(OutputType.FILE);
	            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
	            File dest = new File(".\\screenshot\\" + result.getName() + "_" + timestamp + ".png");
	            FileUtils.copyFile(src, dest);
	            logger.error("Screenshot captured for failed test case: " + result.getName());
	        } catch (Exception e) {
	            logger.error("Exception while taking screenshot: " + e.getMessage(), e);
	        }
	    }
	}

}
