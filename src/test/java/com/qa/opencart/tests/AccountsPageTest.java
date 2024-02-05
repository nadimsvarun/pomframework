package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constant.Constants;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void acctpagesetup() {
		
		acctpage = login.login(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		
		
	}
	
	@Test
	public void acctpagetitle() {
		
		String title = acctpage.getpagetitle();
		Assert.assertEquals(title,Constants.ACCOUNT_PAGE_TITLE);
		
	}
	
	@Test
	public void acctpageurl() {
		
		String url = acctpage.getpageurl();
		Assert.assertTrue(url.contains(Constants.ACCOUNT_PAGE_URL_CONTAINS));
		
		
	}
	
	@Test
	public void logoutlinkexist() {
		
		Assert.assertTrue(acctpage.checklogoutlink());
	}
	
	@Test
	public void searchtextexists() {
		
		Assert.assertTrue(acctpage.checksearchtext());
	}
	
	@Test
	public void checkacctheaders() {
		
		List<String> checkactheadertext = acctpage.checkactheaders();
		Assert.assertEquals(checkactheadertext.size(),Constants.ACCOUNTS_PAGE_HEADERS_COUNT);
	}
	
	@Test
	public void checkacctheaderstext() {
		List<String> checkactheadertext = acctpage.checkactheaders();
		System.out.println(checkactheadertext);
		Assert.assertEquals(checkactheadertext, Constants.ACCOUNTS_PAGE_HEADERS_LIST);
		
	}
	
	@DataProvider
	public Object[][] searchtext() {
		
		return new Object[][] {
			
			{"Macbook"},
			{"iMac"},
			{"Apple"},
			{"Samsung"}
			
	};
		
	}
	
	
	@Test(dataProvider="searchtext")
	public void searchproductcounttest(String searchkey) {
		
		searchpage = acctpage.searchproduct(searchkey);
		Assert.assertTrue(searchpage.getsearchproductcount()>0);
	}
	
	@DataProvider
	public Object[][] searchtextselect() {
		
		return new Object[][] {
			
			{"Macbook","MacBook"},
			{"Macbook","MacBook Air"},
			{"iMac","iMac"},
			{"Apple","Apple Cinema 30\""},
			{"Samsung","Samsung SyncMaster 941BW"}
			
	};
		
	}
	
	
	
	@Test(dataProvider="searchtextselect")
	public void searchproductcount(String sproduct, String cproduct) {
		
		searchpage = acctpage.searchproduct(sproduct);
		
		int count = searchpage.getsearchproductcount();
		if(count>0) {
			
		pinfo =	searchpage.chooseproduct(cproduct);
		String header = pinfo.checkproductheader();
		Assert.assertEquals(header, cproduct);
		
		}
			
	
		
		
		
	}
	

}
