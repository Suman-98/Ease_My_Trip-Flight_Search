package com.easemytrip.testrunner;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

import com.easemytrip.base.Base;
import com.easemytrip.xmlgenerator.SuiteFileGenerator;
import com.easemytrip.xmlgenerator.SuiteFilePath;

public class TestRunner extends Base {

	public static void main(String[] args) {

		// Create object of TestNG Class
		TestNG runner = new TestNG();
		SuiteFileGenerator.createSuiteXMLFile(SuiteFilePath.EaseMyTripSuite.getSuiteName(),SuiteFilePath.EaseMyTripSuite.getTestName(), SuiteFilePath.EaseMyTripSuite.getClassPath(),path+SuiteFilePath.EaseMyTripSuite.getXmlFilePath());

		
		// Create a list of String
		List<String> suitefiles = new ArrayList<String>();
		suitefiles.add( path+ SuiteFilePath.EaseMyTripSuite.getXmlFilePath());
		runner.setTestSuites(suitefiles);
		
		 
		//finally execute the runner using run method
			runner.run();
	}
}
