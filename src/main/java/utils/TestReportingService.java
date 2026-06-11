package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestReportingService {
	
	public static ExtentReports TestReport() {
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("E-Commerce Test Report");
		reporter.config().setReportName("Test Executions Report");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter); 
		extent.setSystemInfo("Test Engineer", "Mohak Bhatia");
		return extent;
	}

}
