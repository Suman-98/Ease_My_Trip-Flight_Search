package com.easemytrip.utils;

import java.io.File;
import java.io.FileInputStream;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.easemytrip.base.Base;

public class CaptureScreenshot extends Base {
	
	public static String captureWebScreenshot(WebDriver driver) throws Exception
	{
		         
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String encodeBase64 = null;
		FileInputStream fisReader = null;
		try {
			fisReader = new FileInputStream(srcFile);
			byte[] bytes = new byte[(int)srcFile.length()];
			fisReader.read(bytes);
			encodeBase64 = new String(Base64.encodeBase64(bytes));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "data:image/png;base64, "+encodeBase64;
		
	}

}
