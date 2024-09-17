package com.easemytrip.listener;

import java.util.Iterator;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.easemytrip.pageaction.FunctionalComponent;
import com.easemytrip.report.ReportConfiguration;
import com.easemytrip.utils.CaptureScreenshot;


public class Listeners extends TestListenerAdapter implements ITestListener {

	ExtentTest test;
	ExtentReports extent;
	public int testPassed = 0;
	public int testFailed = 0;
	public int testSkipped = 0;
	public int testExecuted = 0;
	public WebDriver driver;
	

	@Override
	// To start the test
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		FunctionalComponent.getExtentTest(test);
		testExecuted++;
		
		System.out.println("Test Report Created: " + test);
	}

	@Override
	// To success the test
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

		test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - PASSED", ExtentColor.GREEN));

		testPassed++;
	}

	@Override
	// To failure the test and take a screenshot
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
			test.info("Failure Screenshot: ", MediaEntityBuilder
					.createScreenCaptureFromBase64String(CaptureScreenshot.captureWebScreenshot(driver)).build());
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Failed", ExtentColor.RED));

		testFailed++;
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		if (result.getThrowable().getMessage() == null) {
			extent.removeTest(test);
			testExecuted--;
		} else {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Skipped", ExtentColor.ORANGE));
			testSkipped++;
		}
	}

	@Override
	// get html report
	public void onStart(ITestContext context) {

		extent = ReportConfiguration.getReportObjectWebBrowser();
		 System.out.println("Extent Report Object: " + extent);

	}

	// After finish the test case get the method name
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		Iterator<ITestResult> skippedTestCases = context.getSkippedTests().getAllResults().iterator();
		while (skippedTestCases.hasNext()) {
			ITestResult skippedTestCase = skippedTestCases.next();
			ITestNGMethod method = skippedTestCase.getMethod();
			if (context.getSkippedTests().getResults(method).size() > 0) {
				System.out.println("Removing:" + skippedTestCase.getTestClass().toString());
				skippedTestCases.remove();

			}
		}

		extent.flush();
	}

}
