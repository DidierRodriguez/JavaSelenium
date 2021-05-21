package com.opensource.bitam.qa;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ValidacionSeguridad {

  @BeforeTest
  public void beforeTest() {
  }

  @Test
  public void ValidacionSeguridad_TC() {
	  
		// STEP 1
		Reporter.log("Open Browser BITAM Testing web page");
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chrome/chromedriver.exe");
		// run web page on private mode
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://kpionline10.bitam.com/eBavel6_test/app/fbm_bmd_0119/BAPPAD864904?environment=development");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		// STEP 2
		Reporter.log("Enter Username and Password");
		driver.findElement(By.xpath("//input[@id='userman']")).sendKeys("unittest6@ebavel.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Bitam2tam");
		driver.findElement(By.xpath("//*[@id=\"loginpage\"]/div/div[1]/div/div[3]/form/button")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		// STEP 3
		Reporter.log("Validate that you have logged in sucessfully");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/a[@data-name='BJB0YQ - AND en Seguridad']")));
		
		// STEP 4
		Reporter.log("Enter in the menu");
		driver.findElement(By.xpath("//div/a[@data-name='BJB0YQ - AND en Seguridad']")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		// STEP 5 
		Reporter.log("wait table");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"FRM_D07878C4\"]/div/div/span[2]")));

		// STEP 5
		Reporter.log("Verify the correct security");
		String CorrectNumber = driver.findElement(By.xpath("//*[@id=\"FRM_D07878C4\"]/div/div/span[2]")).getText();
		 //Assert.assertEquals(CorrectNumber, "999");
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(CorrectNumber, "1-20 of 999");
		 
		 soft.assertAll();
  }
  
  @AfterTest
  public void afterTest() {
  }

}
