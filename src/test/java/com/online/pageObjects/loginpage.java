package com.online.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginpage {
	WebDriver ldriver;
	public loginpage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver,this);
	}
	@FindBy(name="uid")
	@CacheLookup
	WebElement u_name;
	@FindBy(name="password")
	@CacheLookup
	WebElement p_word;
	@FindBy(name="btnLogin")
	@CacheLookup
	WebElement button;
	@FindBy(linkText = "Log out")
	WebElement logout;
	
	public void setusername(String uname)
	{
		u_name.sendKeys(uname);
	}
	public void setpassword(String password)
	{
		p_word.sendKeys(password);
	}
	public void setclickbutton()
	{
		button.click();
	}
	public void clicklogout()
	{
		logout.click();
	}
}
