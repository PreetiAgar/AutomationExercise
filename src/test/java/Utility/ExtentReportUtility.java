package Utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportUtility {
	
	ExtentReports report;
	
	public static ExtentReports getExtentReport() {
	String path=System.getProperty("user.dir")+"/reports/index.html";
	
	ExtentSparkReporter reporter = new ExtentSparkReporter(path);
	reporter.config().setDocumentTitle("Test Results");
	reporter.config().setReportName("Web Automation Report");
	
	ExtentReports report = new ExtentReports();
	//this.report=report;
	report.attachReporter(reporter);
	report.setSystemInfo("Tester", "Preeti");
	
	return report;
	}

}
