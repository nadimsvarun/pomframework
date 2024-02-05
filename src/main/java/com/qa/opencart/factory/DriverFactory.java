package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.google.common.io.Files;
import com.qa.opencart.exception.CustomException;

public class DriverFactory {
	
	public WebDriver driver;
	public Properties prop;
	public OptionsManager om;
	public static String highlight;
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	public WebDriver initdriver(Properties prop) {
		
		om = new OptionsManager(prop);
		highlight = prop.getProperty("highlight");
		
		if(prop.getProperty("browser").trim().equalsIgnoreCase("chrome")) {
			
			//driver = new ChromeDriver(om.getchromeoptions());
			tldriver.set(new ChromeDriver(om.getchromeoptions()));
			
		}else if(prop.getProperty("browser").trim().equalsIgnoreCase("firefox")) {
			
			//driver = new FirefoxDriver(om.getfirefoxoptions());
			tldriver.set(new FirefoxDriver(om.getfirefoxoptions()));
			
			
		}else if(prop.getProperty("browser").trim().equalsIgnoreCase("edge")) {
			
			//driver = new EdgeDriver(om.getedgeoptions());
			tldriver.set(new EdgeDriver(om.getedgeoptions()));
		}
		
		
		getthread().manage().deleteAllCookies();
		getthread().manage().window().maximize();
		getthread().get(prop.getProperty("url"));
		return getthread();
	}
	
	public synchronized static WebDriver getthread() {
		
		return tldriver.get();
	}
	
	public Properties initprop() throws CustomException {
		
		prop = new Properties();
        FileInputStream fs = null;
		
		String envName = System.getProperty("env");
		
		try {
		if(envName==null) {
			
			System.out.println("no env is passed");
				fs = new FileInputStream(".\\src\\test\\resources\\properties\\config.properties");
			
		}
	
		else {
			
			switch(envName.toLowerCase().trim()) {
			case "qa":
				try {
					fs = new FileInputStream(".\\src\\test\\resources\\properties\\qa.config.properties");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			default:
				 throw new CustomException("invalid environment");
			    // break;
			}
		}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			prop.load(fs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return prop;
	}
	
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getthread()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
		
		
	}

}
