package com.online.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.online.pageObjects.loginpage;

public class logintest_T001 extends baseclass {
	
	@Test
	public void logintest() throws IOException
	{
	
		loginpage lp=new loginpage(driver);
		
		lp.setusername(username);
		logger.info("entered user name");
		lp.setpassword(password);
		logger.info("entered password");
		lp.setclickbutton();
		logger.info("this is for checking");
		
		if(driver.getTitle().equals("GTPL Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
		}
		else
		{	capturescreenshot(driver,"logintest");
			Assert.assertTrue(false);
		}
	}

}
