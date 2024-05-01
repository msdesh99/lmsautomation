package com.lms.ui.runner;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.lms.ui.configs.ConfigLoader;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
//@RunWith(Cucumber.class)
@CucumberOptions(   tags = ("@tag24"), //@tag22Extra or @tag21 or @tag23"), // @tag23"),//tags =("@tag19A"), // or @tag23"),
		           features = "src/test/resources/features",
					glue = {"com/lms/ui/stepdefinitions"},
				 // tags= "@LinkedList",
					plugin = {"pretty", "html:target/cucumber-Reports.html" , "json:target/cucumber.json",
							"junit:target/Cucumber.xml",
							//"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
							"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
							"rerun:target/failed_scenarios.txt"},
					//monochrome = true,
					publish = true)
public class TestRunner extends AbstractTestNGCucumberTests {
	
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() { return
			super.scenarios(); }
	@BeforeTest	
	@Parameters({"browser"})	
	public void defineBrowser(@Optional("Chrome")String browser) throws Throwable {
		ConfigLoader.setBrowserType(browser);
	}
}




/*package com.lms.ui.runner;

	@RunWith(Cucumber.class)
	@CucumberOptions(features = "src/test/resources/Features",
						glue = {"dsalgo.stepdefinitions"},
					 // tags= "@LinkedList",
						plugin = {"pretty", "html:target/cucumber-Reports.html" , "json:target/cucumber.json",
								"junit:target/Cucumber.xml",
								"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
								"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
								"rerun:target/failed_scenarios.txt"},
						monochrome = true,
						publish = true)
	public class TestRunner extends AbstractTestNGCucumberTests {
		@Override
		@DataProvider(parallel = true)
		public Object[][] scenarios() { return
				super.scenarios(); }
		@BeforeTest
		@Parameters({"browser"})
		public void defineBrowser(String browser) throws Throwable {
			ConfigLoader.setBrowserType(browser);
		}
	}
}*/
