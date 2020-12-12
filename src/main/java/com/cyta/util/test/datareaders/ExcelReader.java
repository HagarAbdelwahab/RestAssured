package com.cyta.util.test.datareaders;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	static String path;
	private static XSSFSheet excelWSheet;
	private static XSSFWorkbook excelWBook;
	private static XSSFCell cell;
	private static XSSFRow row;
	
	private static HSSFSheet xlsWSheet;
	private static HSSFWorkbook xlsWBook;
	private static HSSFCell xlsCell;

	//load Excel file and set the sheet initially
	public static void setExcelFile(String path, String sheetName) throws IOException {
		FileInputStream excelFile = null;
		try {
			//Open the Excel file
			ExcelReader.path = path;
		    excelFile= new FileInputStream(path);
			excelWBook = new XSSFWorkbook(excelFile);
			excelWSheet = excelWBook.getSheet(sheetName);
		}
		finally {
			 try {
				 if (null != excelFile) {
					 excelFile.close();
				 }
			     }
			catch (Exception e) {

				e.printStackTrace();
			}
		}


	}

		

	
	//set or change work sheet
	public static void setExcelSheet(String sheetName) {
			excelWSheet = excelWBook.getSheet(sheetName);
	}
	
	//load XLS file and set the sheet initially
	public static void setXLSFile(String path, String sheetName) throws Exception{
		
		try {
			//Open the Excel file
			ExcelReader.path = path;
			FileInputStream excelFile= new FileInputStream(path);
			xlsWBook = new HSSFWorkbook(excelFile);
			xlsWSheet = xlsWBook.getSheet(sheetName);
			
		} catch (Exception e) {

			throw(e);
		}
	}

	//get excel file cell data by row num and col number 
	public static String getCellData(int rowNum, int colNum) {

			cell = excelWSheet.getRow(rowNum).getCell(colNum);
			if (cell == null) return "";

			return cell.getStringCellValue();

	}
	

	//get xls file cell data by row num and col number 
	public static String getXLSCellData(int rowNum, int colNum) throws IOException{
			
			try {

				xlsCell = xlsWSheet.getRow(rowNum).getCell(colNum);
				if (xlsCell == null) return "";

				return xlsCell.getStringCellValue();
				
			} catch (Exception e) {

				throw(e);
			}
		}
		
	//get excel file cell data by row name and col name 
	public String getCellData(String rowName, String columnName) {
	
		try {
				int colNum = getColumnIndexByName(columnName);
				int rowNum = getRowIndexByName(rowName);
				 // return cell value given the different cell types
				 return getCellData(rowNum,colNum);
	
			} 
		catch (Exception e) 
		{
				  return "";
		}
	}
	
	// return column number by its name
	public static int getColumnIndexByName(String columnName) throws Exception
	{
		int coefficient=0;
	    row = excelWSheet.getRow(0);
	    int cellNum = row.getPhysicalNumberOfCells();
	    for (int i = 0; i < cellNum; i++) {
	        if ((row.getCell(i).toString()).equals(columnName)) {
	            coefficient = i;
	        }
	    }

	    return coefficient;
	}
	
	// return Row number by its name Excel sheet
	public static int getRowIndexByName(String rowName)
	{
		int coefficient=0;
	    int cellNum = excelWSheet.getPhysicalNumberOfRows();
	    
	    for (int i = 0; i < cellNum; i++) {
	    	
	        if ((excelWSheet.getRow(i).getCell(0).toString()).equals(rowName)) {
	            coefficient = i;
	        }
	    }

	    return coefficient;
	}
		
	//This function writes a certain value �Result� in a specific cell, the cell is at row RowNum and column colNum
	public static void setCellData(String result, int rowNum, int colNum) throws Exception{
		
		try {
			
			row= excelWSheet.getRow(rowNum);
			cell =row.getCell(colNum);
			
			if (cell == null)
			{
				cell =row.createCell(colNum);
				cell.setCellValue(result);
			}
			else 
			{
				cell.setCellValue(result);
			}
			
			FileOutputStream fileOut= new FileOutputStream(path);
			excelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			
		} catch (Exception e) {

			throw(e);
		}
	}
	
	//return number of rows in excel work sheet
	public static int getNumberOfRows() {
		
		return excelWSheet.getPhysicalNumberOfRows();
	}
	
	//return number of columns in excel work sheet
	public static int getNumberOfColumns() {
		
		return excelWSheet.getRow(0).getPhysicalNumberOfCells();
	
	}
	
	//This function writes a certain value �Result� in a specific cell, the cell is at row RowNum and column colNum
	public static void setXLSCellData(String result, int rowNum, int colNum) throws Exception{
		
		try {

			HSSFRow xLSRow = xlsWSheet.getRow(rowNum);
			xlsCell = xLSRow.getCell(colNum);
			
			if (cell == null)
			{
				xlsCell = xLSRow.createCell(colNum);
				xlsCell.setCellValue(result);
			}
			else 
			{
				xlsCell.setCellValue(result);
			}
			
			FileOutputStream fileOut= new FileOutputStream(path);
			xlsWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			
		} catch (Exception e) {

			throw(e);
		}
	}
	
	//return number of rows for xls work sheet
	public static int xLSGetNumberOfRows() {
		
		return xlsWSheet.getPhysicalNumberOfRows();
	}

}
