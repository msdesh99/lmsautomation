package com.lms.ui.utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import com.lms.ui.configs.CommonConfigs;
import com.lms.ui.configs.ConfigLoader;
public class Base {
	public WebDriver driver;
	public CommonConfigs configs;
	
	public WebDriver WebDriverManager() throws Throwable {
		String browser = ConfigLoader.getBrowserType();
		if(driver == null) {
			loadCommonConfigs();

			if (browser.equalsIgnoreCase("firefox")) {
				LoggerLoad.info("Testing on firefox");
				FirefoxOptions ffOpt = new FirefoxOptions();
				ffOpt.addArguments("--headless=new");
				driver = new FirefoxDriver(ffOpt);
			//	driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {
				LoggerLoad.info("Testing on chrome");
				ChromeOptions ChromeOpt = new ChromeOptions();
				ChromeOpt.addArguments("--headless=new");
			//	System.setProperty("webdriver.chrome.silentOutput","true");
				//driver = new ChromeDriver();

				driver = new ChromeDriver(ChromeOpt);
			} else if (browser.equalsIgnoreCase("safari")) {
				LoggerLoad.info("Testing on safari");
				driver = new SafariDriver();
			} else if (browser.equalsIgnoreCase("edge")) {
				LoggerLoad.info("Testing on Edge");
				EdgeOptions edgeOpt = new EdgeOptions();
				edgeOpt.addArguments("--headless=new");
				driver = new EdgeDriver(edgeOpt);
			//	driver = new EdgeDriver();
				
			} 
			//driver = new ChromeDriver();
			driver.manage().window().maximize();
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			// Set Page load timeout
		//	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
			driver.get(configs.getUrl());
		}
		return driver;
	}
	
	public void loadCommonConfigs() {
		FileInputStream fis = null;
		configs = new CommonConfigs();

		try {
			fis = new FileInputStream(System.getProperty("user.dir")+
					"/src/test/resources/config/global.properties");
			Properties prop = new Properties();
			prop.load(fis);
			configs.setUrl(prop.getProperty("url"));
			//configs.setLoginUrl(prop.getProperty("loginUrl"));
			//configs.setRegisterUrl(prop.getProperty("registerUrl"));
		//	configs.setBrowser(prop.getProperty("browser"));
			configs.setUserName(prop.getProperty("userName"));
			configs.setPassword(prop.getProperty("password"));		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public CommonConfigs getConfigs() {
		return configs;
	}
}


