package final_work.pageObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import final_work.utilities.ExtendReport;

public class Quebec extends BaseClass{
	WebDriver driver;
	WebDriverWait wait;
	XSSFWorkbook workbook = new XSSFWorkbook();
	String path= "G:\\selenium_workspace\\Jaspreet_FinalProject\\TestData\\QuebecFinances.xlsx";
	

	public Quebec(WebDriver driver)
	{
		this.driver =driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//a[contains(normalize-space(), 'English')]") WebElement linkEnglish;
	@FindBy(xpath = "//li[@id='OBLIGATIONS_tab']//span") WebElement linkBonds;
	@FindBy(xpath = "//div[@id='OBLIGATIONS']//div[@class='t-Region-body']//tbody[3]//tr") List<WebElement> tableRows;
	@FindBy(xpath = ".//a") WebElement clickLink;
	@FindBy(xpath ="//span[@class='ui-button-icon ui-icon ui-icon-closethick']/..") WebElement closeLink;
	@FindBy(xpath = "//div[@id='OBLIGATIONS']//div[@class='t-Region-body']//tbody") 
    private List<WebElement> allTables;

    public void changeLanguage() {
        linkEnglish.click();
    }

    public void openQuebecFinance() {
        linkBonds.click();
    }

	public void extractTablesData() throws InterruptedException, IOException {
		System.out.println("Total tables found: " + allTables.size());

		for (int i = 2; i <= 2; i++) {
			WebElement currentTable = allTables.get(i);
//			List<WebElement> tableRows = currentTable.findElements(	By.xpath("//div[@id='OBLIGATIONS']//div[@class='t-Region-body']//tbody[" + i + "]//tr"));
			List<WebElement> tableRows = currentTable.findElements(	By.tagName("tr"));
			System.out.println("Table " + (i + 1) + " Data:");

			// try {
			for (WebElement row : tableRows) {
				List<WebElement> rowData = row.findElements(By.tagName("td"));

				List<WebElement> links = row.findElements(By.xpath(".//td/a"));
				for(WebElement link : links)
				{
					link.click();
					 String sheetName = link.getText().trim();
						driver.switchTo().frame(0);
						WebElement popup = driver.findElement(By.xpath("//div[@class='t-Region-body']//table[2]"));
						// Take Screenshot after clicking the link
//		                String screenshotPath = new BaseClass().captureScreen(sheetName);
//		                ExtendReport.test.addScreenCaptureFromPath(screenshotPath);
//		                ExtendReport.test = ExtendReport.extent.createTest(sheetName);
						ExtendReport.test = ExtendReport.extent.createTest(sheetName);
						 String screenshotPath = captureScreen(sheetName);
		                    ExtendReport.test.addScreenCaptureFromPath(screenshotPath);
		                    
		                
//		                System.out.println("Screenshot saved: " + screenshotPath);
		             // Attach screenshot to Extent Report
		                
						System.out.println("Data Captured for : " + sheetName);
						writeToExcel(sheetName, popup);
						
						driver.switchTo().defaultContent();
//					Thread.sleep(2000);
					closeLink.click();
				}
				for (WebElement cell : rowData) {

					System.out.print(cell.getText() + "\t");

					// System.out.println();
				}
				System.out.println();
			}
			System.out.println("------------------------");
		}
		saveExcelFile();
	}
	
	public void writeToExcel(String sheetName, WebElement table) throws IOException
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
	
	
	private void saveExcelFile() throws IOException
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



//public class Quebec extends BasePage {
//    WebDriver driver;
//    WebDriverWait wait;
//
//    // Constructor to call page factory
//    public Quebec(WebDriver driver) {
//        super(driver);
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }
//    
//    @FindBy(xpath = "//a[contains(normalize-space(), 'English')]")
//    WebElement linkEnglish;
//
//    @FindBy(xpath = "//li[@id='OBLIGATIONS_tab']//span")
//    WebElement linkBonds;
//
//    @FindBy(xpath = "//div[@id='OBLIGATIONS']//div[@class='t-Region-body']//tbody[3]//tr")
//    List<WebElement> tableRows;
//
//    @FindBy(xpath = "//*[starts-with(@headers,'NOM_OFFCL')]")
//    WebElement clickLink;
//
//    @FindBy(xpath = "//button[@title='Fermer']")
//    WebElement closeLink;
//
//    @FindBy(xpath = "//div[@id='OBLIGATIONS']//div[@class='t-Region-body']//tbody")
//    private List<WebElement> allTables;
//
//    public void changeLanguage() {
//        wait.until(ExpectedConditions.elementToBeClickable(linkEnglish)).click();
//    }
//
//    public void openQuebecFinance() {
//        wait.until(ExpectedConditions.elementToBeClickable(linkBonds)).click();
//    }
//
//    public void extractTablesData() {
//    	
//        System.out.println("Total tables found: " + allTables.size());
//
//        for (int i = 2; i < 5; i++) {
//            WebElement currentTable = allTables.get(i);
//            List<WebElement> tableRows = currentTable.findElements(By.tagName("tr"));
//            System.out.println("Table " + (i + 1) + " Data:");
//
//            for (WebElement row : tableRows) {
//                List<WebElement> rowData = row.findElements(By.tagName("td"));
//                
//                try {
//                    WebElement nameLink = row.findElement(By.xpath(".//a")); // Ensure it finds the link
//                    wait.until(ExpectedConditions.elementToBeClickable(nameLink)).click();
//
//                    // Wait for the pop-up/modal/table to appear
//                    WebElement popupTable = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                            By.xpath("//div[contains(@class, 'ui-dialog')]//table")));
//
//                    System.out.println("Data Captured: " + popupTable.getText());
//
//                    // Close the pop-up after data extraction
//                    wait.until(ExpectedConditions.elementToBeClickable(closeLink)).click();
//                } catch (Exception e) {
//                    System.out.println("No clickable link found in this cell.");
//                }
//                for (WebElement cell : rowData) {
//                    System.out.print(cell.getText() + "\t");
////                    wait.until(ExpectedConditions.elementToBeClickable(clickLink)).click();
////                    wait.until(ExpectedConditions.elementToBeClickable(closeLink)).click();
//                    
//                }
//                System.out.println();
//            }
//            System.out.println("------------------------");
//        }
//    }
//}
