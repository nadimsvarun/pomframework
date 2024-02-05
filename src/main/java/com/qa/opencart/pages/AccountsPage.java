package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constant.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	protected WebDriver driver;
	ElementUtil eutil;
	
	//private By acctheader = By.xpath("//*[@id=\"content\"]/h2");
	private By logout = By.linkText("Logout");
	private By search = By.xpath("//*[@id=\"search\"]/input");
	private By acctheaders = By.cssSelector("div#content>h2");
	private By clickicon = By.cssSelector("#search > span > button");
	
	
	
	public AccountsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		this.driver=driver;
		eutil=new ElementUtil(driver);

}
	
public String getpagetitle() {
		
	
	   String title = eutil.waitForTitleIsAndFetch(Constants.DEAFAULT_MEDIUM_TIMEOUT, Constants.ACCOUNT_PAGE_TITLE);
	
		
		//String title = driver.getTitle();
		System.out.println("page title="+title);
		return title;
		
	}
	
	public String getpageurl() {
		
		
		String url = eutil.waitForURLContainsAndFetch(Constants.DEAFAULT_MEDIUM_TIMEOUT, Constants.ACCOUNT_PAGE_URL_CONTAINS);
		
		//String url = driver.getCurrentUrl();
		System.out.println("page url="+url);
		return url;
	}
	
	public boolean checklogoutlink() {
		
		
		return eutil.waitForElementVisible(logout, Constants.DEAFAULT_MEDIUM_TIMEOUT).isDisplayed();
		
		//return driver.findElement(logout).isDisplayed();
	}
	
	public boolean checksearchtext() {
		
		
		return eutil.waitForElementVisible(search, Constants.DEAFAULT_MEDIUM_TIMEOUT).isDisplayed();
		
		//return driver.findElement(search).isDisplayed();
		
	}
	
	public List<String> checkactheaders() {
		
		List<WebElement> acth =   eutil.waitForElementsPresence(acctheaders,Constants.DEAFAULT_MEDIUM_TIMEOUT);
		
	//List<WebElement> acth =	driver.findElements(acctheaders);
	List<String> aheaders = new ArrayList<String>();
	for(WebElement e:acth) {
		
		aheaders.add(e.getText());
	}
	
	return aheaders;
	}
	
	
	public SearchPage searchproduct(String product) {
		
       if(checksearchtext()) {
    	   
    	   eutil.doSendKeys(search, product);
    	   eutil.doClick(clickicon);
    	   return new SearchPage(driver);
    	   
       }else {
    	   
    	   System.out.println("search field is not present");
    	   return null;
       }

	}
	
	
}
