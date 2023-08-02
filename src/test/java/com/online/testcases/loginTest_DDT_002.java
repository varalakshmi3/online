package com.online.testcases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import com.online.pageObjects.*;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.online.utilities.XLUtils;

public class loginTest_DDT_002 extends baseclass{
	@Test(dataProvider="loginData")
	public void loginTestDDT(String uname,String pass) throws Exception 
	{
		driver.get(baseurl);
		logger.info("base url opened");
		loginpage test=new loginpage(driver);
		test.setusername(uname);
		logger.info("username entered");
		test.setpassword(pass);
		logger.info("password entered");
		test.setclickbutton();
		if(isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
		}
		else
		{
			test.clicklogout();
			Thread.sleep(5000);
			driver.switchTo().alert().accept();
			Assert.assertTrue(true);
		}
	}
	
	public boolean isAlertPresent() throws InterruptedException
	{
		try {
		driver.switchTo().alert();
		Thread.sleep(5000);
		return true;
		}catch (NoAlertPresentException e) {
			return false;
		}
		
	}
	
	@DataProvider(name="loginData")
	String[][] getdata() throws Exception
	{
		String path=System.getProperty("user.dir")+"/src/test/java/com/online/testData/loginform.xls";
		int rowcount=XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path, "Sheet1", rowcount);
		String data[][]=new String[rowcount][colcount];
		for(int i=1;i<=rowcount;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				data[i-1][j]=XLUtils.getCellValue("Sheet1", path, i, j);
			}
		}
		return data;
		
	}

}
