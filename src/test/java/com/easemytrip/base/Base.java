package com.easemytrip.base;

import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import io.github.bonigarcia.wdm.WebDriverManager;



public class Base {

	private WebDriver driver;
	public static final String path = System.getProperty("user.dir");
	protected static Properties prop;

	// set the path of browser and add arguments
	public WebDriver initializeWebDriver() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--process-per-tab");
		options.addArguments("--process-per-site");
		options.addArguments("--disable-plugins");
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-gpu");
		options.addArguments("--media-cache-size=1 --disk-cache-size=1");
		options.addArguments("disable-infobars");

		if (prop.getProperty("browser").equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			// System.setProperty("webdriver.chrome.driver",
			// path+"/driver/chromedriver.exe");
			// driver = new ChromeDriver();
		}

		// safari driver
		else if (prop.getProperty("browser").equalsIgnoreCase("Safari")) {
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
		}

		// edge driver
		else if (prop.getProperty("browser").equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		// firefox driver
		else if (prop.getProperty("browser").equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		// driver error message
		else {
			System.out.println("Driver error");
		}
		return driver;

	}

	// To get the driver
	public void getDriver(WebDriver driver) {
		this.driver = driver;
	}

	/* To write the data into properties file */
	public static Properties readProperty(String propPath) {
		prop = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream(propPath);
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return prop;

	}

	// close the driver
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
