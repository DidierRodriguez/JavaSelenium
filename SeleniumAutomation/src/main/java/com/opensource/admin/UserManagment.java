package com.opensource.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.opensource.base.SeleniumWrapper;

public class UserManagment extends SeleniumWrapper {

	public UserManagment(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	By link_admin = By.xpath("//a[@id='menu_admin_viewAdminModule']");
	By txt_search = By.xpath("//input[@id='searchSystemUser_userName']");
	By btn_search = By.xpath("//input[@id = 'searchBtn']");
	By tbl_firstRowSearch = By.xpath("//tbody/tr[1]/td[2]");

	/*
	 * Validate logged successfully
	 */

	public void validateLogged() {
		Reporter.log("Validate that you have logged in sucessfully");
		waitForElementPresent(link_admin, 10);

	}

	/*
	 * Click Admin
	 */

	public void clickAdmin() {
		reporterLog("Click Admin - Go to the admin page");
		click(link_admin, "Admin");
		implicitlyWait(5);
	}

	/*
	 * Search user
	 */

	public void searchUser(String user, boolean ClickSearch) {
		Reporter.log("Search username in field");
		type(txt_search, user, "Search field");
		if (ClickSearch) {
			click(btn_search, "Button Search");
		}
		implicitlyWait(5);

	}

	/*
	 * Validate value from search table
	 */

	public void validateFormSearchTable(String expectedValue) {
		reporterLog("verify value in search table");
		String actualValue = getText(tbl_firstRowSearch);
		assertEquals(actualValue, expectedValue);
	}
}
