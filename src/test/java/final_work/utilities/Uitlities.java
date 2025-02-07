package final_work.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Uitlities {
	static XSSFWorkbook workbook = new XSSFWorkbook();
	static String path= "G:\\selenium_workspace\\BQA\\TestData\\QuebecFinances.xlsx";
	public static void writeToExcel(String sheetName, WebElement table) throws IOException
	{
		
		 XSSFSheet sheet = workbook.getSheet(sheetName);
	        if (sheet == null) {
	            sheet = workbook.createSheet(sheetName);
	        }
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		for(int i=0;i<rows.size();i++)
		{
			XSSFRow row = sheet.createRow(i);
			List<WebElement> cells = table.findElements(By.tagName("tr"));
			for(int j=0;j<cells.size();j++)
			{
				XSSFCell cell = row.createCell(j);
				cell.setCellValue(cells.get(j).getText());
			}
		}
	}
	
	
	private static void saveExcelFile() throws IOException
	{
	        try (FileOutputStream fileOut = new FileOutputStream(path))
	        {
	            workbook.write(fileOut);
	            System.out.println("Excel file saved: " + path);
	        } 
	        finally 
	        {
	            workbook.close();
	        }
	   }
}

