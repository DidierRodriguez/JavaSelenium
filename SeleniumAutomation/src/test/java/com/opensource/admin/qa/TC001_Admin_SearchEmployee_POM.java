package com.opensource.admin.qa;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.opensource.admin.Login;
import com.opensource.admin.UserManagment;
import com.opensource.base.GlobalVariables;
import com.opensource.base.SeleniumWrapper;

public class TC001_Admin_SearchEmployee_POM {
	
	WebDriver driver;
	SeleniumWrapper seleniumWrapper;
	Login login;
	UserManagment userManagment;
	// Hardcode de datos -nunca hacer esto
	String username, password;

  @BeforeTest
  public void beforeTest() {
	  
	  seleniumWrapper=new SeleniumWrapper(driver);
	  driver = seleniumWrapper.chromeDriverConnection();
	  login = new Login(driver);
	  userManagment = new UserManagment(driver);
	  
	  this.username = seleniumWrapper.getCellData(this.getClass().getSimpleName(), 1, 0);
	  this.password = seleniumWrapper.getCellData(this.getClass().getSimpleName(), 1, 1);

	  
//	  Hardcode de datos -nunca hacer esto
//	  username = "Admin";
//	  password = "admin123";
	  
//	  
  }

  
  @Test
  public void TC001_Admin_SearchEmployee_POM_TestScript() {
	  
	  // Step 1
	  seleniumWrapper.launchBrowser(GlobalVariables.QA_URL);
	  
	  // Step 2
	  login.loginOrange(username, password);
	  
	  // Step 3
	  userManagment.validateLogged();
	  
	  // Step 4
	  userManagment.clickAdmin();
	  
	  // Step 5 and 6
	  userManagment.searchUser(username, true);
	  
	  // Step 7
	  userManagment.validateFormSearchTable("1", "2", username);
	  
	  // Step 8
	  login.logoutOrange();
	  
  }
  
  
  @AfterTest
  public void afterTest() {
	  
	  // Step 9 
	  seleniumWrapper.closeBrowser();
	  
  }

}
