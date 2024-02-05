package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic(value = "EP001")
@Story(value = "Login Page of Ecommerce")
public class LoginPageTest extends BaseTest{

	
	@Severity(SeverityLevel.MINOR)
	@Description("Checking valid page title")
	@Test(priority=1)
	public void validatepagetitle() {
		
		String actualtitle = login.getpagetitle();
		Assert.assertEquals(actualtitle, "Account Login");
	}
	@Severity(SeverityLevel.MINOR)
	@Description("Checking valid page url")
	@Test(priority=2)
	public void validateurl() {
		
		String actualurl = login.getpageurl();
		Assert.assertEquals(actualurl, "https://naveenautomationlabs.com/opencart/index.php?route=account/login");
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("Checking forgot password link")
	@Test(priority=3)
	public void checkforgotpasswordlink() {
		
		Assert.assertTrue(login.isforgotpasswordlinkexists());
		
	}
	@Severity(SeverityLevel.BLOCKER)
	@Description("Checking login")
	@Test(priority=4)
	public void logintest() {
		
		login.login(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		
	}
	
	
}
