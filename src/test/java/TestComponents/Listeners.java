package TestComponents;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.TestReportingService;

public class Listeners extends BaseTest implements ITestListener{
	ExtentReports report = TestReportingService.TestReport();
	ThreadLocal<ExtentTest> thread = new ThreadLocal();
	
	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest test=report.createTest(result.getMethod().getMethodName());
		thread.set(test);
		
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		
		thread.get().log(Status.PASS, "Test Passed");
		
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		
		thread.get().fail(result.getThrowable());
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TakeScreenshot(result.getMethod().getMethodName(), driver);
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		
		thread.get().log(Status.SKIP, "Test Skipped");
	}
	
	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}
}
