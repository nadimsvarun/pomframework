package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	public OptionsManager(Properties prop) {
		this.prop=prop;
		
	}
	
	public ChromeOptions getchromeoptions() {
		
		co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) co.addArguments("--headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) co.addArguments("--incognito");
		return co;
		
	}
	
public FirefoxOptions getfirefoxoptions() {
		
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) co.addArguments("--headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) co.addArguments("--incognito");
		return fo;
		
	}

public EdgeOptions getedgeoptions() {
	
	eo = new EdgeOptions();
	if(Boolean.parseBoolean(prop.getProperty("headless").trim())) eo.addArguments("--headless");
	if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) eo.addArguments("--incognito");
	return eo;
	
}


}
