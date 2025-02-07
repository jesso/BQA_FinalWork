package final_work.testCase;

import java.io.IOException;

import org.testng.annotations.Test;

import final_work.pageObject.Quebec;
import final_work.pageObject.BaseClass;


public class TestCase extends BaseClass {
	
	@Test
	public void execution() throws InterruptedException, IOException
	{
		
		logger.info("****Test Execution Started****");
		Quebec qc = new Quebec(driver);
		qc.changeLanguage();
		logger.info("****Language Changed****");
		
		//Thread.sleep(2000);
		
		qc.openQuebecFinance();
		logger.info("****Iterating through Tables****");
		qc.extractTablesData();
		logger.info("****Cpaturing Popup Tale Data****");
		
	}

}
