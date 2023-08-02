package com.online.utilities;

import java.io.FileInputStream;
import java.util.Properties;
import java.io.File;

public class ReadConfig {
	Properties pro;
	public  ReadConfig()
	{
		File file=new File("./Configuration//config.properties");
		try {
			FileInputStream fis=new FileInputStream(file);
			pro=new Properties();
			pro.load(fis);
		
		} catch (Exception e) {
			System.out.println("The  exception is"+e.getMessage());
		}
	}
	
	public String getbaseurl()
	{
		String url=pro.getProperty("baseurl");
		return url;
	}
	
	public String getuser_name()
	{
		String u_name=pro.getProperty("username");
		return u_name;
	}
	public String getpass_word()
	{
		String p_word=pro.getProperty("password");
		return p_word;
	}
	public String getchrome_path()
	{
		String c_path=pro.getProperty("chromepath");
		return c_path;
	}
	public String F_path()
	{
		String f_path=pro.getProperty("geckopath");
		return f_path;
	}

}
