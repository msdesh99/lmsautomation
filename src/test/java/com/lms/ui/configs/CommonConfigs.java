package com.lms.ui.configs;

public class CommonConfigs {
	private static String browserType = null;
	String userName;
	String password;
	String url;
	String loginUrl;
	String registerUrl;
	
	
	public static void setBrowserType(String browser) {
		browserType = browser;
	}
	public static String getBrowserType() throws Throwable {
		if (browserType != null)
			return browserType;
		else
			throw new RuntimeException("browser not specified in the testng.xml");
	}
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

}
