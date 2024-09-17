package com.easemytrip.pageaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.easemytrip.base.Base;
import com.easemytrip.utils.ExcelUtils;

public class FunctionalComponent extends Base {

	public WebDriver driver;
	static Properties reportDetails;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	static ExtentTest test;
	Logger log;
	ExcelUtils excel;
	Properties propData;
	WebDriverWait wait;
	public static List<String> airLines = new ArrayList<>();
	public static List<String> departureTime = new ArrayList<>();
	public static List<String> arriveTime = new ArrayList<>();
	public static List<String> flightPrice = new ArrayList<>(); // new ArrayList() means an empty arreylist.

	
	public FunctionalComponent() {
		try {
			excel = new ExcelUtils(path + "/InputCity.xlsx");
			propData = super.readProperty(path + "/src/test/java/com/easemytrip/properties/data.properties");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public FunctionalComponent(WebDriver driver, Logger log) throws Exception {
		this();
		this.driver = driver;
		this.log = log;
		wait = new WebDriverWait(this.driver, 30);
	}

	
	public static void getExtentTest(ExtentTest extentTest) {

		if (extentTest == null) {
			
		} else {
			
		}
		test = extentTest;
	}

	
	/*
	 * Function Name : navigating_Google_Site()  Purpose : To navigate to Google Site. 
	 * Platform : Chrome Browser
	 * Parameters : None  Added by : "Suman Mondal" 
	 */
	public void navigating_Google_Site() {

		Logger log = LogManager.getLogger("Incorrect_Password_TestCase");
		log.info("****** TC41_WebBrowser_Incorrect_password_for_valid_username_TestCases for Chilis ******");

		/* Launching google.com in web browser */
		try {

			driver.get("https://www.google.com/");

			// Set a log sucessfull message.
			log.info("Successfully launched Google.com site.");

			// create Pass node with status Pass
			

			test.createNode("Successfully launched Google.com site.").pass("PASSED");

		} catch (Exception e) {

			// set log error message
			log.error("Failed to launch Goole.com site");

			// create failed node with method name
			test.createNode("Failed to launch Goole.com site")
					.fail("Methode Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()")
					.log(Status.FAIL, e);

			// get error message
			log.error(e.getMessage());

			// close driver
			tearDown();

		}

	}

	/*
	 * Function Name : search_and_Select_MakeMyTrip()  Purpose : To Search and select MakeMyTrip.com. 
	 * Platform : Chrome Browser
	 * Parameters : None  Added by : "Suman Mondal" 
	 */
	public void search_and_Select_MakeMyTrip() {

		/* Passing keyword 'Make My Trip' to google search box and search */
		try {

			WebElement searchBox = driver.findElement(By.xpath("//*[@aria-label='Search']"));
			searchBox.click();
			searchBox.sendKeys("Ease My Trip");

			// Set a log sucessfull message.
			log.info("Successfully entered text 'Ease My Trip' to search box.");

			// create Pass node with status Pass
			test.createNode("Successfully entered text 'Ease My Trip' to search box.").pass("PASSED");

		} catch (Exception e) {

			// set log error message
			log.error("Failed to entered text in search box.");

			// create failed node with method name
			test.createNode("Failed to entered text in search box.")
					.fail("Methode Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()")
					.log(Status.FAIL, e);

			// get error message
			log.error(e.getMessage());

			// close driver
			tearDown();
		}

		/* Select from google suggestion and wait till the element is visible */
		try {
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'EaseMyTrip')]")));
			driver.findElement(By.xpath("//span[contains(text(),'EaseMyTrip')]")).click();

			// Set a log sucessfull message.
			log.info("Successfully clicked on the 'Ease My Trip' Suggestion.");

			// create Pass node with status Pass.
			test.createNode("Successfully clicked on the 'Ease My Trip' Suggestion.").pass("PASSED");

		} catch (Exception e) {

			// set log error message
			log.error("Failed to click on google suggestion.");

			// create failed node with method name
			test.createNode("Failed to click on google suggestion.")
					.fail("Methode Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()")
					.log(Status.FAIL, e);

			// get error message
			log.error(e.getMessage());

			// close driver
			tearDown();
		}

	}
	
	/*
	 * Function Name : select_Destination_To_Location()  Purpose : To Select Destination Location. 
	 * Platform : Chrome Browser
	 * Parameters : None  Added by : "Suman Mondal" 
	 */

	public void select_Destination_To_Location() {

		/* Navigate to 'Make My Trip' site */
		String getURL = (propData.getProperty("url"));

		try {

			driver.navigate().to(getURL);

			// Set a log sucessfull message.
			log.info("Successfully navigated to 'Make My Trip' Url.");

			// create Pass node with status Pass.
			test.createNode("Successfully navigated to 'Make My Trip' Url.").pass("PASSED");

		} catch (Exception e) {

			// set log error message
			log.error("Failed to navigate to 'Make My Trip' site");

			// create failed node with method name
			test.createNode("Failed to navigate to 'Make My Trip' site")
					.fail("Methode Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()")
					.log(Status.FAIL, e);

			// get error message
			log.error(e.getMessage());

			// close driver
			tearDown();
		}

		/* fetching Destinations' from the excel sheet */
		String from = excel.getCellData("Destination", "From", 2);
		String to = excel.getCellData("Destination", "To", 2);

		/* Clicking on the 'From' destination read from InputCity Excel */
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='frmcity']"))).click();

			WebElement city = driver.findElement(
					By.xpath("//*[@id='fromautoFill']/ul/li/descendant::span[contains(text(),'" + from + "')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", city);
			city.click();

			// Set a log sucessfull message.
			log.info("Successfully clicked on 'From' destination city from dropdown.");

			// create Pass node with status Pass.
			test.createNode("Successfully clicked on 'From' destination city from dropdown.").pass("PASSED");

			// Stop driver execution for 3 seconds.
			Thread.sleep(3000);

		} catch (Exception e) {

			// set log error message
			log.error("Failed to select 'From' destination city.");

			// create failed node with method name
			test.createNode("Failed to select 'From' destination city.")
					.fail("Methode Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()")
					.log(Status.FAIL, e);

			// get error message
			log.error(e.getMessage());

			// close driver
			tearDown();
		}

		/* Clicking on the 'To' destination read from Input Excel */
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='toautoFill']")));

			WebElement city = driver.findElement(
					By.xpath("//*[@id='toautoFill']/ul/li/descendant::span[contains(text(),'" + to + "')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", city);
			city.click();

			// Set a log sucessfull message.
			log.info("Successfully clicked on 'TO' destination city from dropdown.");

			// create Pass node with status Pass.
			test.createNode("Successfully clicked on 'TO' destination city from dropdown.").pass("PASSED");

			// Stop driver execution for 1 seconds.
			Thread.sleep(1000);

		} catch (Exception e) {

			// set log error message
			log.error("Failed to select 'TO' destination city.");

			// create failed node with method name
			test.createNode("Failed to select 'TO' destination city.")
					.fail("Methode Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()")
					.log(Status.FAIL, e);

			// get error message
			log.error(e.getMessage());

			// close driver
			tearDown();
		}

	}

	/*
	 * Function Name : select_Date_and_Search()  Purpose : To Select current Date. 
	 * Platform : Chrome Browser
	 * Parameters : None  Added by : "Suman Mondal" 
	 */
	public void select_Date_and_Search() {

		/* Selecting the Flight Search date from the Calender as Current date */
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='active-date']"))).click();

			// Set a log sucessfull message.
			log.info("Successfully selected the Current date for Flight search");

			// create Pass node with status Pass.
			test.createNode("Successfully selected the Current date for Flight search").pass("PASSED");

			// Stop driver execution for 1 seconds.
			Thread.sleep(1000);

		} catch (Exception e1) {

			// set log error message
			log.error("Failed to select current date");

			// create failed node with method name
			test.createNode("Failed to select current date")
					.fail("Methode Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()")
					.log(Status.FAIL, e1);

			// get error message
			log.error(e1.getMessage());

			// close driver
			tearDown();
		}

		/* Clicking on 'SEARCH' button */
		try {

			// Stop driver execution for 3 seconds.
			Thread.sleep(3000);

			driver.findElement(By.xpath("//button[@class='srchBtnSe']")).click();

			// Set a log sucessfull message.
			log.info("Successfully clicked on Search button.");

			// create Pass node with status Pass.
			test.createNode("Successfully clicked on Search button.").pass("PASSED");

			// Stop driver execution for 4 seconds.
			Thread.sleep(4000);

		} catch (InterruptedException e) {

			// set log error message
			log.error("Failed to click on Search button");

			// create failed node with method name
			test.createNode("Failed to click on Search button")
					.fail("Methode Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()")
					.log(Status.FAIL, e);

			// get error message
			log.error(e.getMessage());

			// close driver
			tearDown();
		}

	}
	
	
	/*
	 * Function Name : printing_Airlines_Name()  Purpose : To Print All Airlines Names. 
	 * Platform : Chrome Browser
	 * Parameters : None  Added by : "Suman Mondal" 
	 */
	public void printing_Airlines_Name() {

		/* printing Airlines Names in Console */

		try {

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='FareCalDiv']")));

			List<WebElement> locations = driver.findElements(By.xpath(
					"//*[@alt='Flight']/ancestor::div[2]/following-sibling::div/span[contains(@ng-bind,'GetAirLineName')]"));
			Iterator<WebElement> iter = locations.iterator();
			while (iter.hasNext()) {

				airLines.add(iter.next().getText());

			}

			// Printing all the Airlines names.
			System.out.println("Airlines: " + airLines);

			// Set a log sucessfull message.
			log.info("Successfully printed all the Airlines name as: " + airLines);

			// create Pass node with status Pass.
			test.createNode("Successfully printed all the Airlines name as: " + airLines).pass("PASSED");

		} catch (Exception e) {

			// set log error message
			log.error("Failed to fetch Airlines name.");

			// create failed node with method name
			test.createNode("Failed to fetch Airlines name.")
					.fail("Methode Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()")
					.log(Status.FAIL, e);

			// get error message
			log.error(e.getMessage());

			// close driver
			tearDown();
		}
	}

	/*
	 * Function Name : printing_Airlines_Departure_Time()  Purpose : To Print All Airlines Departure Time. 
	 * Platform : Chrome Browser
	 * Parameters : None  Added by : "Suman Mondal" 
	 */
	public void printing_Airlines_Departure_Time() {

		/* printing the Flight Departure time in Console */

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='FareCalDiv']")));

			List<WebElement> locations = driver
					.findElements(By.xpath("//*[@alt='Flight']/ancestor::div[4]/following-sibling::div/span[contains(@ng-bind,'.DTM')]"));
			Iterator<WebElement> iter = locations.iterator();
			while (iter.hasNext()) {

				departureTime.add(iter.next().getText());

			}

			// Printing all the Airlines Departure Time.
			System.out.println("DepartureTime: " + departureTime);

			// Set a log sucessfull message.
			log.info("Successfully printed all Departure time as: " + departureTime);

			// create Pass node with status Pass.
			test.createNode("Successfully printed all Departure time as: " + departureTime).pass("PASSED");

		} catch (Exception e) {

			// set log error message
			log.error("Failed to fetch Flight Departure time.");

			// create failed node with method name
			test.createNode("Failed to fetch Flight Departure time.")
					.fail("Methode Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()")
					.log(Status.FAIL, e);

			// get error message
			log.error(e.getMessage());

			// close driver
			tearDown();
		}
	}

	
	/*
	 * Function Name : printing_Airlines_Arrive_Time()  Purpose : To Print All Airlines Arrive Time. 
	 * Platform : Chrome Browser
	 * Parameters : None  Added by : "Suman Mondal" 
	 */
	public void printing_Airlines_Arrive_Time() {

		/* Printing the Flight Arrive Time in Console */

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='FareCalDiv']")));

			List<WebElement> locations = driver
					.findElements(By.xpath("//*[@alt='Flight']/ancestor::div[4]/following-sibling::div/span[contains(@ng-bind,'.ATM')]"));
			Iterator<WebElement> iter = locations.iterator();
			while (iter.hasNext()) {

				arriveTime.add(iter.next().getText());

			}

			// Printing all the Airlines Arrive Time.
			System.out.println("ArriveTime: " + arriveTime);

			// Set a log sucessfull message.
			log.info("Successfully printed all Atrrive time as: " + arriveTime);

			// create Pass node with status Pass.
			test.createNode("Successfully printed all Atrrive time as: " + arriveTime).pass("PASSED");

		} catch (Exception e) {

			// set log error message
			log.error("Failed to fetch Flight Arrive time.");

			// create failed node with method name
			test.createNode("Failed to fetch Flight Arrive time.")
					.fail("Methode Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()")
					.log(Status.FAIL, e);

			// get error message
			log.error(e.getMessage());

			// close driver
			tearDown();
		}

	}

	
	/*
	 * Function Name : printing_Airlines_Prices()  Purpose : To Print All Airlines Prices. 
	 * Platform : Chrome Browser
	 * Parameters : None  Added by : "Suman Mondal" 
	 */
	public void printing_Airlines_Prices() {

		/* printing the Flight Price in Console */

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='FareCalDiv']")));

			List<WebElement> locations = driver.findElements(By.xpath(
					"(//*[@alt='Flight']/ancestor::div[4]/following-sibling::div/span)/parent::div/following-sibling::div//span[contains(@id,'Price')]"));
			Iterator<WebElement> iter = locations.iterator();
			while (iter.hasNext()) {
				flightPrice.add(iter.next().getText());

			}

			// Printing all the Airlines Price list.
			System.out.println("PriceList: " + flightPrice);

			// Set a log sucessfull message.
			log.info("Successfully printed all the Pricelist as: " + flightPrice);

			// create Pass node with status Pass.
			test.createNode("Successfully printed all the Pricelist as: " + flightPrice).pass("PASSED");

		} catch (Exception e) {

			// set log error message
			log.error("Failed to fetch Flights Price list.");

			// create failed node with method name
			test.createNode("Failed to fetch Flights Price list.")
					.fail("Methode Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()")
					.log(Status.FAIL, e);

			// get error message
			log.error(e.getMessage());

			// close driver
			tearDown();
		}
	}
}
