package com.lms.ui.stepdefinitions;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.lms.ui.utilities.TestContextSetUp;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;

public class Hooks {
TestContextSetUp testContextSetup;
	
	public Hooks(TestContextSetUp testContextSetup)
	{
		this.testContextSetup = testContextSetup;
	}
	
	@After
	public void AfterScenario() throws Throwable
	{
		testContextSetup.base.WebDriverManager().quit();
	}
	@AfterStep
	public void AddScreenshot(Scenario scenario) throws Throwable
	{
		WebDriver driver =testContextSetup.base.WebDriverManager();
		if(scenario.isFailed())
		{
			//screenshot
			File sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
			scenario.attach(fileContent, "image/png", "image");
		}
	}



}
