package com.qa.opencart.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constant.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eutil;
	
	private By emailid = By.id("input-email");
	private By password = By.id("input-password");
	private By clickbtn = By.cssSelector("#content > div > div:nth-child(2) > div > form > input");
	private By forgotpwdlink = By.linkText("Forgotten Password");
	private By clickregistration = By.linkText("Register");
	
	
	
	public LoginPage(WebDriver driver) {
		
		this.driver=driver;
		eutil = new ElementUtil(driver);
	}
	
	@Step("Getting page title")
	public String getpagetitle() {
		
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.title)
		
		
		String title = eutil.waitForTitleIsAndFetch(Constants.DEAFAULT_MEDIUM_TIMEOUT, Constants.LOGIN_PAGE_TITLE);
		
		//String title = driver.getTitle();
		System.out.println("page title="+title);
		return title;
		
	}
	
	public String getpageurl() {
		
		String url = eutil.waitForURLContainsAndFetch(Constants.DEAFAULT_MEDIUM_TIMEOUT, Constants.LOGIN_PAGE_URL_CONTAINS);
		
		//String url = driver.getCurrentUrl();
		System.out.println("page url="+url);
		return url;
	}
	
	public boolean isforgotpasswordlinkexists() {
		
		
		return eutil.waitForElementVisible(forgotpwdlink, Constants.DEAFAULT_MEDIUM_TIMEOUT).isDisplayed();
		
		
		//return driver.findElement(forgotpwdlink).isDisplayed();
		
	}
	@Step("logging with username:{0} and password:{1}")
	public AccountsPage login(String uname,String pwd) {
		
		eutil.waitForElementVisible(emailid, Constants.DEAFAULT_MEDIUM_TIMEOUT).sendKeys(uname);
		eutil.doSendKeys(password,pwd);
		eutil.doClick(clickbtn);
		
		/*driver.findElement(emailid).sendKeys(uname);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(clickbtn).click();*/
		
		return new AccountsPage(driver);
		
		
	}
	
	public RegistrationPage clickregistration() {
		
		eutil.doClick(clickregistration);
		return new RegistrationPage(driver);
	}

}
