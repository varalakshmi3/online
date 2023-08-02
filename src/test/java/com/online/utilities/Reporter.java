package com.online.utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporter extends TestListenerAdapter {

	public ExtentReports extentReports;
	public ExtentSparkReporter reporter;
	public ExtentTest logger;

	@Override
	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = "Test-Report" + timeStamp + ".html";
		reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/" + repName);
		try {
			reporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentReports = new ExtentReports();
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("HostName", "localHost");
		extentReports.setSystemInfo("Environment", "QA");
		extentReports.setSystemInfo("user", "varalakshmi");
		reporter.config().setDocumentTitle("EBanking");
		reporter.config().setReportName("Vara Lakshmi Devi");
		reporter.config().setTheme(Theme.STANDARD);
	

		super.onStart(testContext);
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		logger=extentReports.createTest(tr.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.AMBER));
		String screenpath = System.getProperty("user.dir") + "\\Screenshots\\" + "tr.getName()" + ".png";
		File file = new File(screenpath);
		if (file.exists()) {
			try {
				logger.fail("Screen shot is below:" + logger.addScreenCaptureFromPath(screenpath));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	@Override
	public void onTestSkipped(ITestResult tr) {
		logger=extentReports.createTest(tr.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(),ExtentColor.BLUE));
	}
	
	@Override
	public void onFinish(ITestContext testContext) {
		extentReports.flush();
	}

}
