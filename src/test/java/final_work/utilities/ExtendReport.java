package final_work.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import final_work.pageObject.BaseClass;

public class ExtendReport implements ITestListener {

		
		public ExtentSparkReporter sparkReporter;
		public static ExtentReports extent;
		public static ExtentTest test;
		
		String repName;
		
		 public void onStart(ITestContext testContext) {
		    
				/*
				 * SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss"); Date dt =
				 * new Date(); String currentDateTimeStamp = df.format(dt);
				 */
			 
			 String timeStamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
			 
			 repName = "Test-Report - " + timeStamp + ".html";
			 sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
			 
			 sparkReporter.config().setDocumentTitle("Quebec Finance Report");
		        sparkReporter.config().setReportName("Quebec Finance Functional Testing");
			 sparkReporter.config().setTheme(Theme.DARK);
			 
			 extent = new ExtentReports();
			 
			 extent.attachReporter(sparkReporter);
			 extent.setSystemInfo("Application", "Quebec Finance");
			 extent.setSystemInfo("Enviromment", "QA");
			 
			 String os = testContext.getCurrentXmlTest().getParameter("os");
			 extent.setSystemInfo("Operating System", os);
			 
			 String browser = testContext.getCurrentXmlTest().getParameter("browser");
			 extent.setSystemInfo("Browser", browser);

			 List<String> includeGroups = testContext.getCurrentXmlTest().getIncludedGroups();
			 if(!includeGroups.isEmpty())
			 {
				 extent.setSystemInfo("Groups", includeGroups.toString());
			 }
			 
		  }
		 
//		 @Override
//		    public void onTestStart(ITestResult result) {
//		        test = extent.createTest(result.getMethod().getMethodName()); // Initialize test here
//		    }
		
//		  public void onTestSuccess(ITestResult result) 
//		  {
//			  
//			  test = extent.createTest(result.getTestClass().getName());
//			  test.assignCategory(result.getMethod().getGroups()); //to display groups in report
//			  test.log(Status.PASS,result.getName()+"got succesfuly executed");
//			  try {
////				  String impPath = new BaseClass().captureScreen(result.getName());
////				  test.addScreenCaptureFromPath(impPath);
//				  String screenshotPath = new BaseClass().captureScreen(result.getName());
//				  test.addScreenCaptureFromPath(screenshotPath);
//			  }
//			  catch(IOException e1)
//			  {
//				  e1.printStackTrace();
//			  }
//
//		  }
		 public void onTestSuccess(ITestResult result) {
			    test = extent.createTest(result.getMethod().getMethodName());
			    test.assignCategory(result.getMethod().getGroups());
			    test.log(Status.PASS, result.getName() + " successfully executed");

			    try {
			        String screenshotPath = new BaseClass().captureScreen(result.getName());
			        test.addScreenCaptureFromPath(screenshotPath);
			    } catch (IOException e) {
			        //logger.error("Error capturing screenshot: " + e.getMessage());
			    }
			}
		  
		  public void onTestFailure(ITestResult result) 
		  {
			  test = extent.createTest(result.getTestClass().getName());
			  test.assignCategory(result.getMethod().getGroups());
			  test.log(Status.FAIL, result.getName()+" got failed");
			  test.log(Status.INFO, result.getThrowable().getMessage());
			  
			  try {
				  String impPath = new BaseClass().captureScreen(result.getName());
				  test.addScreenCaptureFromPath(impPath);
			  }
			  catch(IOException e1)
			  {
				  e1.printStackTrace();
			  }
		  }
		  
		  public void onTestSkipped(ITestResult result) 
		  {
			  test = extent.createTest(result.getTestClass().getName());
			  test.assignCategory(result.getMethod().getGroups());
			  test.log(Status.SKIP, result.getName()+" got skipped");
			  test.log(Status.INFO, result.getThrowable().getMessage());
		  }
		  
		  public void onFinish(ITestContext testContext) 
		  {
			    extent.flush();
			    
			    String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
			    File extentReport = new File(pathOfExtentReport);
			    
			    try
			    {
			    	Desktop.getDesktop().browse(extentReport.toURI()); //open this report on browser automatically
			    }
			    catch(IOException e)
			    {
			    	e.printStackTrace();
			    }
			    
		  }
		  	  
		  
	}

