package com.easemytrip.report;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.easemytrip.base.Base;



public class ReportConfiguration extends Base{

	public static ExtentReports extent;
	static Properties propDetails;
	
	
	// Create the object of html reporter and attach extent reports 
	public static ExtentReports getReportObjectWebBrowser()
	{
		propDetails = readProperty(path+"/src/test/java/com/easemytrip/properties/data.properties");
		
		
		/*
		 * DateTimeFormatter class used to set the date and time format
		 * LocalDateTime class used to select time, we call now method to 
		 * get current time
		 */
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddyyyyHHmmss");
		LocalDateTime now = LocalDateTime.now();
		
		/*
		 * Creating the path where extent reports will store
		 */
		String path = System.getProperty("user.dir")+"/Reports/EaseMyTrip/Report - "+dtf.format(now)+".html";
		
		/*
		 * Create the object of ExtentHtmlReprter class and pass the path where extent 
		 * report store
		 */
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);

		
		/*
		 * set some extent report configuration 
		 */
		reporter.config().setReportName("EMT Flight Search Automation Result");
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setTheme(Theme.DARK);
		
		/*
		 * Create the object of extent report and attach the report
		 * set some system information and return the extent report
		 */
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "SUMAN");
		extent.setSystemInfo("Enviornment", "Prod");
		extent.setSystemInfo("Platform", "Web Browser");
		extent.setSystemInfo("Web Url", propDetails.getProperty("url"));
		return extent;
	}
	
}
