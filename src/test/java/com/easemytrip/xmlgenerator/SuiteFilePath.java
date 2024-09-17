package com.easemytrip.xmlgenerator;

public enum SuiteFilePath {
	
	EaseMyTripSuite("WebBrowser","Test Chilis In Web Browser","com.easemytrip.testscript.TestScripts","/EaseMyTripSuite.xml");
	private String suiteName;
	private String testName;
	private String classPath;
	private String xmlFilePath;
	SuiteFilePath(String suiteName, String testName, String classPath, String xmlFilePath)
	{
		this.suiteName =  suiteName;
		this.testName = testName;
		this.classPath = classPath;
		this.xmlFilePath = xmlFilePath;
	}
	
	public String getSuiteName()
	{
		return suiteName;
	}

	public String getTestName()
	{
		return testName;
	}
	public String getClassPath()
	{
		return classPath;
	}
	public String getXmlFilePath()
	{
		return xmlFilePath;
	}

}
