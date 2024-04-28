package com.lms.ui.pageobjects;

import org.openqa.selenium.WebDriver;

import com.lms.ui.configs.CommonConfigs;

public class PageObjectManager {
	public LoginPage loginPage;
	public DashboardPage dashboardPage;
	public ProgramPage programPage;
	public BatchPage batchPage;
	public UserPage userPage;
	public WebDriver driver;
	private CommonConfigs commonConfigs;
	
	public PageObjectManager(WebDriver driver, CommonConfigs commonConfigs ) {
		this.driver = driver;
		this.commonConfigs = commonConfigs;
	}
	public LoginPage getLoginPage() {
		loginPage = new LoginPage(driver);
		return loginPage;		
	}
	public DashboardPage getDashboardPage() {
		dashboardPage = new DashboardPage(driver);
		return dashboardPage;		
	}
	public BatchPage getBatchPage() {
		batchPage = new BatchPage(driver);
		return batchPage;
	}
	public ProgramPage getProgrmaPage() {
		programPage = new ProgramPage(driver);
		return programPage;
	}
	public UserPage getUserPage() {
		userPage = new UserPage(driver);
		return userPage;
	}


}
