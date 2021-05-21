package com.opensource.admin.qa;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC001_Admin_SearchEmployee {

	@BeforeTest
	public void beforeTest() {
	}

	@Test
	public void TC001_Admin_SearchEmployee_TC() {

		// STEP 1
		Reporter.log("Open Browser \"OrangeHRM\" web page");
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chrome/chromedriver.exe");
		// run web page on private mode
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");

		WebDriver driver = new ChromeDriver(options);
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// STEP 2
		Reporter.log("Enter Username and Password");
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("admin123");
		driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// STEP 3
		Reporter.log("Validate that you have logged in sucessfully");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_admin_viewAdminModule']")));

		// STEP 4
		Reporter.log("Click Admin - Go to the admin page");
		driver.findElement(By.xpath("//a[@id='menu_admin_viewAdminModule']")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// STEP 5
		Reporter.log("Search username in field");
		driver.findElement(By.xpath("//input[@id='searchSystemUser_userName']")).sendKeys("Admin");

		// STEP 6
		Reporter.log("Click Search");
		driver.findElement(By.xpath("//input[@id = 'searchBtn']")).click();

		// STEP 7
		Reporter.log("Verify username exist in table");
		String actualUsername = driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).getText();
		// Assert.assertEquals(actualUsername, "Admin");

		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actualUsername, "Admin");

		// STEP 8
		Reporter.log("Log out");
		driver.findElement(By.xpath("//a[@id='welcome']")).click();
		driver.findElement(By.xpath("//a[contains(text(), 'Logout')]")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// STEP 9
		Reporter.log("Close Browser");
		driver.close();

		soft.assertAll();

	}

	@AfterTest
	public void afterTest() {
	}

}
