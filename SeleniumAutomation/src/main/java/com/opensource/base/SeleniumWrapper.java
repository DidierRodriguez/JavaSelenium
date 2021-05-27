package com.opensource.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class SeleniumWrapper {

	private WebDriver driver;

	/* Constructor SeleniumWrapper */

	public SeleniumWrapper(WebDriver driver) {
		this.driver = driver;

	}

	// Chrome driver connection

	public WebDriver chromeDriverConnection() {
		System.setProperty(GlobalVariables.CHROME_DRIVER, GlobalVariables.PATH_CHROME_DRIVER);
		// run web page on private mode
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		driver = new ChromeDriver(options);
		return driver;
	}

	// Launch Browser

	public void launchBrowser(String url) {
		reporterLog("Launching..." + url);
		driver.get(url);
		driver.manage().window().maximize();
		implicitlyWait(5);

	}

	// Reporter Log

	public void reporterLog(String log) {
		Reporter.log(log);

	}

	// Implicitly wait

	public void implicitlyWait(int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);

	}

	// Find Element

	public WebElement findElement(By locator) {
		return driver.findElement(locator);
	}

	// Type method

	public void type(By locator, String inputText, String description) {
		System.out.println("Typing text: " + inputText + " " + description);
		driver.findElement(locator).sendKeys(inputText);

	}

	// click object

	public void click(By locator, String description) {
		System.out.println("Cliking..." + description);
		driver.findElement(locator).click();

	}

	/*
	 * Wait for Element Present
	 * 
	 */

	public void waitForElementPresent(By locator, int timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (TimeoutException e) {

		}
	}

	// Get Text

	public String getText(By locator) {
		try {
			return driver.findElement(locator).getText();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	// Hard Assertion

	public void assertEquals(String actualValue, String expectedValue) {
		try {
			Assert.assertEquals(actualValue, expectedValue);
		} catch (AssertionError e) {
			Assert.fail("Not able to assert Actual value: " + actualValue + " Expected Value " + expectedValue);
		}
	}

	// Close browser

	public void closeBrowser() {
		try {
			reporterLog("Close Browser");
			driver.close();
		} catch (NoSuchSessionException e) {
			Assert.fail("Not able to Close Browser");
		}
	}

}
