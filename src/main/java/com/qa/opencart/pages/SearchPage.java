package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constant.Constants;
import com.qa.opencart.utils.ElementUtil;




public class SearchPage {
	
	private WebDriver driver;
	private ElementUtil eutil;
	
	
	private By searchproductresults = By.cssSelector("div#content>div>div.product-layout");

	public SearchPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		this.driver=driver;
		eutil=new ElementUtil(driver);
		
		
	}
	
	public int getsearchproductcount() {
		
		int productcount = eutil.waitForElementsVisible(searchproductresults, Constants.DEAFAULT_MEDIUM_TIMEOUT).size();
		System.out.println("product count"+productcount);
		return productcount;
		
	}
	
	
	public ProductinfoPage chooseproduct(String productname) {
		
		By productlocator = By.linkText(productname);
		eutil.waitForElementVisible(productlocator, Constants.DEAFAULT_MEDIUM_TIMEOUT).click();
		return new ProductinfoPage(driver);
		
		
	}
	
	
	

}
