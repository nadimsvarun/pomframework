package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

public class ProductinfoPageTest extends BaseTest {
	
	
	@BeforeTest
	public void productpageinfosetup() {
		
		acctpage = login.login(prop.getProperty("username").trim(), prop.getProperty("password").trim());

		sa = new SoftAssert();
	}
	
	@DataProvider
	public Object[][] searchtextselect() {
		
		return new Object[][] {
			
			{"Macbook","MacBook",4},
			{"iMac","iMac",3},
			{"Apple","Apple Cinema 30\"",6},
			{"Samsung","Samsung SyncMaster 941BW",1}
			
	};
		
	}
	
	@Test(dataProvider="searchtextselect")
	public void productimagescount(String searchproduct, String selproduct, int Count) {
		
		searchpage = acctpage.searchproduct(searchproduct);
		pinfo = searchpage.chooseproduct(selproduct);
		int imagecount = pinfo.checkimagescount();
		Assert.assertEquals(imagecount,Count);
		
		
	}
	
	@Test
	public void verifyproductinfo() {
		
		
		searchpage = acctpage.searchproduct("MacBook");
		pinfo = searchpage.chooseproduct("MacBook Pro");
		Map<String,String> productinfo = pinfo.getproductinfo();
		System.out.println(productinfo);
		String brand = productinfo.get("Brand");
		System.out.println(brand);
		sa.assertEquals(brand, "Apple");
	    sa.assertEquals(productinfo.get("Productname"), "MacBook Pro");
		//sassert.assertAll();		
	}
	
	@Test
	public void addtocart() {
		

		searchpage = acctpage.searchproduct("MacBook");
		pinfo = searchpage.chooseproduct("MacBook Pro");
		Map<String,String> productinfo = pinfo.getproductinfo();
		pinfo.addquantity(3);
		pinfo.clickaddtocart();
		String actual = pinfo.checksuccessmessage();
		String expected = "Success: You have added "+productinfo.get("Productname")+" to your shopping cart!";
		Assert.assertTrue(actual.contains(expected));
		
	}
	
	

}
