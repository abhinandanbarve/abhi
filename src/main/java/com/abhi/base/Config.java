package com.abhi.base;

import java.util.Properties;

public  class Config extends TestBase{
	
	private Properties properties;
	
	private static Config config = null;
	
	private Config(){
		this.properties = TestBase.OR;
	}
	
	public static Config getInstance(){
		if(config != null)
			return config;
		else {
			config = new Config(); 	
			return config;
		}
	}
	
	public String getUserName() {
		return properties.getProperty("Username");
	}

	public String getPassword() {
		return properties.getProperty("Password");
	}

	public String getWebsite() {
		return properties.getProperty("Website");
	}

	public int getPageLoadTimeOut() {
		return Integer.parseInt(properties.getProperty("PageLoadTimeOut"));
	}

	public int getImplicitWait() {
		return Integer.parseInt(properties.getProperty("ImplcitWait"));
	}

	public int getExplicitWait() {
		return Integer.parseInt(properties.getProperty("ExplicitWait"));
	}
	
	public int getTcRALoginWait() {
		return Integer.parseInt(properties.getProperty("TcRALoginWait"));
	}

	public String getBrowser() {
		return properties.getProperty("Browser");
	}

	public String getIsSSOMode() {
		return properties.getProperty("IsSSOMode");
	}
}
