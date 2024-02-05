package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constant.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ProductinfoPage {
	
	protected WebDriver driver;
	protected ElementUtil eutil;
	protected Map<String,String> productmap ;
	
	
	protected By productheader = By.tagName("h1");
	protected By images = By.cssSelector("ul.thumbnails img");
	protected By productmetadata = By.cssSelector("div#product-product>div>div>div>div:nth-child(2)>ul:nth-child(3)>li");
	protected By pricedata =  By.cssSelector("div#product-product>div>div>div>div:nth-child(2)>ul:nth-child(4)>li");
	protected By Addquantity = By.cssSelector("input#input-quantity");
	protected By clickadd = By.cssSelector("button#button-cart");
	protected By successmessage = By.cssSelector("#product-product > div.alert.alert-success.alert-dismissible");
	
	public ProductinfoPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		this.driver=driver;
		eutil=new ElementUtil(driver);
	}
	
	
	public String checkproductheader() {
		
		String productheadertext = eutil.doElementGetText(productheader);
		System.out.println("product name"+productheadertext);
		return productheadertext;
		
		
	}
	
	public int checkimagescount() {
		
		int imagescount = eutil.waitForElementsVisible(images, Constants.DEAFAULT_MEDIUM_TIMEOUT).size();
		return imagescount;
		
	}
	
	public Map<String, String> getproductinfo() {
		
		 productmap = new HashMap<String,String>();
		 
		 productmap.put("Productname", checkproductheader());
		 getproductmetadata();
		 getproductprice();
		 
		 return productmap;
		
	}
	
	protected void getproductmetadata() {
		
		/*
		 * Brand: Apple 
		 * Product Code: Product 17 
		 * Reward Points: 700 
		 * Availability: InStock
		 */
		
		
		
		List<WebElement> metalist = eutil.getElements(productmetadata);
		for(WebElement e:metalist) {
			
			String data = e.getText();
			String[] splitdata = data.split(":");
			String key = splitdata[0].trim();
			System.out.println("key="+key);
			String value = splitdata[1].trim();
			System.out.println("value="+value);
			productmap.put(key, value);
			
		}
		
		
	}
	
	protected void getproductprice() {
		
		/*
		$1,202.00
		Ex Tax: $1,000.00
		*/
		
		List<WebElement> pricelist = eutil.getElements(pricedata);
		String price = pricelist.get(0).getText();
		String tax = pricelist.get(1).getText();
		String[] taxsplit = tax.split(":");
		String taxprice = taxsplit[1].trim();
		productmap.put("Price", price);
		productmap.put("Tax", taxprice);
		
		
	

}
public void addquantity(int quantity) {
	
	eutil.doSendKeys(Addquantity, String.valueOf(quantity));
	
}

public void clickaddtocart() {
	eutil.doClick(clickadd);
	
}

public String checksuccessmessage() {
	
	
	String text = eutil.waitForElementVisible(successmessage,Constants.DEAFAULT_MEDIUM_TIMEOUT).getText();
	return text;
	
}
	
}
