package com.easemytrip.testscript;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.easemytrip.base.Base;
import com.easemytrip.pageaction.FunctionalComponent;


public class TestScripts extends Base {

	public WebDriver driver;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;

	
	@BeforeTest(alwaysRun=true)
	public void initialize() {

		driver = initializeWebDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);

	}
	

	/*
	 * This Method will go to EaseMyTrip and Take Frm,To City and Search for Flights
	 */
	@Test
	public void flight_Search() throws Exception {
		Logger log = LogManager.getLogger("gfgghvhjhbhjjhbjbjhb");
		log.info("******vhvbbjbbbjjbjvvhvhghgvhgvghvhvh******");
		FunctionalComponent fc = new FunctionalComponent(driver, log);
		fc.navigating_Google_Site();
		fc.search_and_Select_MakeMyTrip();
		fc.select_Destination_To_Location();
		fc.select_Date_and_Search();
		fc.printing_Airlines_Name();
		fc.printing_Airlines_Departure_Time();
		fc.printing_Airlines_Arrive_Time();
		fc.printing_Airlines_Prices();
	}
	

	/* This Method will write all the Flight Details searched in Previous Method */

	@Test(dependsOnMethods = "flight_Search")
	public void write_Flight_Data_to_Excel() { // Writing the Data to a Excel Sheet//

		// Create blank workbook
		workbook = new XSSFWorkbook();

		// Create blank Sheet
		sheet = workbook.createSheet("Data");

		// Create header row
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("AirLines");
		headerRow.createCell(1).setCellValue("ArriveTime");
		headerRow.createCell(2).setCellValue("DepartureTime");
		headerRow.createCell(3).setCellValue("FlightPrices");

		// Populate data rows
		for (int i = 0; i < FunctionalComponent.airLines.size(); i++) {
			Row dataRow = sheet.createRow(i + 1);
			dataRow.createCell(0).setCellValue(FunctionalComponent.airLines.get(i));
			dataRow.createCell(1).setCellValue(FunctionalComponent.arriveTime.get(i));
			dataRow.createCell(2).setCellValue(FunctionalComponent.departureTime.get(i));
			dataRow.createCell(3).setCellValue(FunctionalComponent.flightPrice.get(i));
			
//			System.out.println(FunctionalComponent.airLines.size());
//			System.out.println(FunctionalComponent.airLines.get(i));
//			System.out.println(FunctionalComponent.arriveTime.get(i));
//			System.out.println(FunctionalComponent.departureTime.get(i));
//			System.out.println(FunctionalComponent.flightPrice.get(i));
		}
		

		// write down the file to HardDisk
		try {
			FileOutputStream wrtFile = new FileOutputStream("OutputData.xlsx");

			try {
				workbook.write(wrtFile);

				wrtFile.close();
				System.out.println("Output File has been created Successfully");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
/****This Method will terminate all Browser instances*********/

	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();

	}

}
