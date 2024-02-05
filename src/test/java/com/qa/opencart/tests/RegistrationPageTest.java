package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constant.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {
	
	
	@BeforeClass
	public void clickregister() {
		
		register = login.clickregistration();
	}
	
	
	@DataProvider
	public Object[][] getexceldata() {
		
		Object regdata[][] = ExcelUtil.gettestdata(Constants.SHEET_NAME);
		return regdata;
	}
	
	public String generateemail() {
		
		Random random = new Random();
		String email = "varun"+random.nextInt(1000)+"@yopmail.com";
		return email;
	}
	
	@Test(dataProvider="getexceldata")
	public void registration(String fname,String lname,String phoneno, String pwd,String subscribe) {
		
		Assert.assertTrue(register.registeruser(fname,lname,generateemail(),phoneno,pwd,subscribe));
	}

}
