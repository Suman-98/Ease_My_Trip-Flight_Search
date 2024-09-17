package com.easemytrip.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelUtils {

	public FileInputStream fis = null ;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	public XSSFRow row = null;
	public XSSFCell cell = null;
	int rowNum = 0;
	int cellNum =0;
	
	public ExcelUtils(String excelFilePath) throws Exception
	{
		fis = new FileInputStream(excelFilePath);
		workbook = new XSSFWorkbook(fis);
		fis.close();
	}
	
	@SuppressWarnings("deprecation")
	public String getCellData(String sheetName, String colName, int rowNum)
	{
		int colNum = 0;
		try {
			colNum = -1;
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);
			for(int i=0; i<row.getLastCellNum(); i++)
			{
				if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
				colNum = i;
			}
				
				row = sheet.getRow(rowNum-1);
				cell = row.getCell(colNum);
				
//				System.out.println(cell);
//				System.out.println(cell.getCellType());
				
				if(cell.getCellType() == CellType.STRING)
				{
					return cell.getStringCellValue();
				}
				else if(cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA)
				{
					String cellValue = String.valueOf(cell.getNumericCellValue());
					if(HSSFDateUtil.isCellDateFormatted(cell)) {
						
						DateFormat df = new SimpleDateFormat("dd/MM/yy");
						Date date = cell.getDateCellValue();
						cellValue = df.format(date);
					}
					return cellValue;
				}
				
				else if(cell.getCellType() == CellType.BLANK)
				{
					return "";
				}
				else
				{
					return String.valueOf(cell.getBooleanCellValue());
				}
		} catch (Exception e) {
			e.printStackTrace();
			return "row "+rowNum+" or column "+colNum+" does not exist in excel sheet";
		}
	}
	
	public int getNoOfRows(String sheetName)
	{
		sheet = workbook.getSheet(sheetName);
		return sheet.getLastRowNum()+1;
		
	}
	
	
	public void writeExcelSheet(String sheetName, Map<String,Object[]> dataset) {
		
		//Create blank workbook
		workbook =new XSSFWorkbook();
		
	
		//Create blank Sheet
		sheet = workbook.createSheet(sheetName);
		
		
		//Iterate over the data
		Set<String> set = dataset.keySet();
		
	
		for (String key: set) {
			
			Row row = sheet.createRow(rowNum++);
			
			Object[] data = dataset.get(key);
			
			for (Object value : data) {
				
				Cell cell =row.createCell(cellNum++); 
				
					if (value instanceof String) 
						
						cell.setCellValue((String)value);
					
					else if (value instanceof Integer)
						
						cell.setCellValue((Integer)value);
			}
			
		}
		
		//write down the file to HardDisk
		try {
			FileOutputStream wrtFile = new FileOutputStream("Output.xlsx");
			
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
}
