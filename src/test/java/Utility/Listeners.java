package Utility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import AutomationExercise.BaseTest;

public class Listeners implements ITestListener{
	ExtentTest test;
	ExtentReports extentReport=ExtentReportUtility.getExtentReport();
	
	ThreadLocal<ExtentTest> extentTest= new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) {
		
		test=extentReport.createTest(result.getMethod().getMethodName());
		
		extentTest.set(test);
	  }

	 
	  public  void onTestSuccess(ITestResult result) {
		  //extentTest.get().log(Status.PASS, "Test is Passed");
	  }

	  
	  public void onTestFailure(ITestResult result) {

		  extentTest.get().fail(result.getThrowable());
		  String filePath=null;
		  
		  try {
	            // ✅ Get driver safely using ThreadLocal
	            filePath = new BaseTest().getScreenshot(result.getMethod().getMethodName()); //BASE TEST CLASS called here
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        //extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	    }
	  public void onFinish(ITestContext context) {
		  extentReport.flush();
		  }
}
