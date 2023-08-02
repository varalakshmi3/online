package com.online.testcases;
import java.io.*;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.online.utilities.ReadConfig;

public class baseclass {
	ReadConfig config=new ReadConfig();
	public String baseurl=config.getbaseurl();
	public String username=config.getuser_name();
	public String password=config.getpass_word();
	
	public WebDriver driver;
	public static Logger logger;
	@BeforeClass
	@Parameters("browser")
	public void setup(String br)
	{
		logger=LogManager.getLogger(baseclass.class);
		if(br.equals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver",config.getchrome_path());
		driver=new ChromeDriver();
		logger.info("chrome driver opened");
		}
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",config.F_path());
			//FirefoxOptions options=new FirefoxOptions();
			//options.addArguments("--remote-allow-origins");
			WebDriver driver = new FirefoxDriver();

			driver=new FirefoxDriver();
			logger.info("firefox driver opened");

		}
		else if(br.equals("edge"))
		{
			System.setProperty("webdriver.ie.driver",config.getchrome_path());
			driver=new InternetExplorerDriver();
			logger.info("IE driver openend");

		}
		else
		{
			System.out.println("enter any valid browser");
		}
		driver.get(baseurl);
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.MICROSECONDS);
		
	}
	@AfterClass
	public void teardown() throws Exception
	{	
		Thread.sleep(2000);
		driver.quit();
	}
	public void capturescreenshot(WebDriver driver, String tname) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File scr=ts.getScreenshotAs(OutputType.FILE);
		File des=new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
		FileUtils.copyFile(scr, des);
		logger.info("Screen shot taken successfully");
	}

}
