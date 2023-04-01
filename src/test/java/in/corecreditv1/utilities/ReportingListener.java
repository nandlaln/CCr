package in.corecreditv1.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ReportingListener extends TestListenerAdapter {

	public ExtentSparkReporter reporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); 		// Time stamp
		String repName="Test-Report-"+timeStamp+".html";
		
		reporter= new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/"+repName)		// Specify Location
		.viewConfigurer().viewOrder().as(new ViewName [] {ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY}).apply();
		try {
			reporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extent = new ExtentReports();
		
		extent.attachReporter(reporter);
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "Nandlal");
		
		reporter.config().setDocumentTitle("CoreCredit Report"); 	// Title of the Project
		reporter.config().setReportName("Functional Automation Test Report"); 	// Name of the report
		reporter.config().setTheme(Theme.DARK); 	// Use DARK theme
	}
	
	public void onTestSuccess(ITestResult tr)
	{
		logger = extent.createTest(tr.getName());		// Create new entry in report
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));	//Send the passed information
	}
	
	public void onTestFailure(ITestResult tr) {
		logger = extent.createTest(tr.getName());		// Create new entry in report
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));	//Send the passed info

		String screenshotpath = System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		
		File f = new File(screenshotpath);
				
		if(f.exists())
		{
			logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotpath));
		}	
	}

	public void onTestSkipped(ITestResult tr) {
		logger = extent.createTest(tr.getName());		// Create new entry in report
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));	//Send the passed info
		
	}
	

	public void onFinish(ITestContext testcontext) {
		extent.flush();
	}
	
	
	
	
}
