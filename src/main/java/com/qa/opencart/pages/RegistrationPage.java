package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constant.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {
	
	private By fname = By.cssSelector("form>fieldset:nth-child(1)> div:nth-child(3)>div>input");
	private By lname = By.cssSelector("form>fieldset:nth-child(1)>div:nth-child(4)>div>input");
	private By email = By.cssSelector("form>fieldset:nth-child(1)> div:nth-child(5)>div>input");
	private By phoneno = By.cssSelector("form>fieldset:nth-child(1)> div:nth-child(6)>div>input");
	
	private By password = By.cssSelector("#input-password");
	private By repwd = By.cssSelector("#input-confirm");
	
	private By radiobtnyes = By.cssSelector("form>fieldset:nth-child(3)>div>div>label:nth-child(1)>input");
	private By radiobtnno = By.cssSelector("form>fieldset:nth-child(3)>div>div>label:nth-child(2)>input");
	private By accept = By.cssSelector("form>div>div>input:nth-child(2)");
	private By clickbtn = By.cssSelector("form>div>div>input:nth-child(3)");
	
	private By successmsg = By.cssSelector("#content > h1");
	private By logout = By.linkText("Logout");
	private By clickregistration = By.linkText("Register");
	
	WebDriver driver;
	ElementUtil eutil;
	
	public RegistrationPage(WebDriver driver) {
		
		this.driver=driver;
		eutil = new ElementUtil(driver);
		
	}
	
	public boolean registeruser(String fname,String lname,String email, String phoneno, String pwd,String subscribe) {
		
		eutil.waitForElementVisible(this.fname,Constants.DEFAULT_SHORT_TIMEOUT).sendKeys(fname);
		eutil.doSendKeys(this.lname, lname);
		eutil.doSendKeys(this.email, email);
		eutil.doSendKeys(this.phoneno, phoneno);
		eutil.doSendKeys(this.password, pwd);
		eutil.doSendKeys(this.repwd, pwd);
		if(subscribe.equalsIgnoreCase("yes")) {
			
			eutil.doClick(radiobtnyes);
		
		}else {
			
			eutil.doClick(radiobtnno);
		}
		
		eutil.doClick(accept);
		eutil.doClick(clickbtn);
		
		if(eutil.doElementGetText(successmsg).contains("Your Account Has Been Created!")) {
			
			eutil.doClick(logout);
			eutil.doClick(clickregistration);
			return true;
			
		}else {
			
			return false;
		}
		
	}

}
