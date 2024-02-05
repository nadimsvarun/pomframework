package com.qa.opencart.base;



import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.exception.CustomException;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductinfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchPage;

public class BaseTest {
	
	DriverFactory df;
	WebDriver driver;
	protected Properties prop;
	protected LoginPage login;
	protected AccountsPage acctpage;
	protected SearchPage searchpage;
	protected ProductinfoPage pinfo;
	protected SoftAssert sa;
	protected RegistrationPage register;
	
	@BeforeTest
	public void setup() throws CustomException {
		
		df = new DriverFactory();
		prop = df.initprop();
		
		driver = df.initdriver(prop);
		login= new LoginPage(driver);
		
		
	}
	
	@AfterTest
	public void teardown() {
		
		driver.quit();
	}

}
