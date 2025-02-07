package final_work.pageObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class BaseClass{

		public static WebDriver driver;
		public static Logger logger;
		@BeforeClass
		public void setup()
		{
			driver = new ChromeDriver();
			logger = LogManager.getLogger(this.getClass());
			driver.get("https://www.finmun.finances.gouv.qc.ca/finmun/f?p=100:3000::RESLT:9561523948414");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		
		@AfterClass
		public void tearDown() throws Exception
		{
			//Thread.sleep(2000);
			driver.quit();
		}

		public String captureScreen(String fileName) throws IOException
		{
			if (driver == null) {
	            throw new IllegalStateException("WebDriver is not initialized.");
	        }
			
			 String timeStamp = new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date());
			    String fileSeparator = System.getProperty("file.separator");
			    String screenshotDir = System.getProperty("user.dir") + fileSeparator + "Screenshot";

			
			    File screenshotFolder = new File(screenshotDir);
			    if (!screenshotFolder.exists()) {
			        screenshotFolder.mkdirs();
			    }
			 String screenshotPath = screenshotDir + fileSeparator + fileName + "_" + timeStamp + ".png";

			    TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
			    File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);
			    File targetFile = new File(screenshotPath);
			    FileUtils.copyFile(sourceFile, targetFile);

			    logger.info("Screenshot saved: " + screenshotPath);
			    return screenshotPath;
			
		}
}
