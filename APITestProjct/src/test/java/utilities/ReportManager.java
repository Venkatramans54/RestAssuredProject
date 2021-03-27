package utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportManager extends TestListenerAdapter {
	
	public ExtentReports extent;
	public ExtentTest test;
	public ExtentSparkReporter sparkReporter;
	
	public void onStart(ITestContext testContext) {
		sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/myReport.html");
		sparkReporter.config().setDocumentTitle("API Automation Report");
		sparkReporter.config().setReportName("API Report");
		sparkReporter.config().setTheme(Theme.DARK);		
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
	}
	
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getName());
		test.log(Status.PASS, "Test Case "+result.getName()+" is Passed");
	}
	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getName());
		test.log(Status.FAIL, "Test Case "+result.getTestName()+" is Failed");
		test.log(Status.FAIL, "Test Case "+result.getThrowable()+" is Failed");		
	}
	
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Case "+result.getTestName()+" is Skipped");
	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

}
