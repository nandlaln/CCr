package in.corecreditv1.testcases;



import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import in.corecreditv1.utilities.ReadConfig;

public class BaseClass {
	
	ReadConfig readconfig = new ReadConfig();
	
	public static WebDriver driver;
	public Alert alert;
	public static Logger logger;
	SoftAssert s = new SoftAssert();
	
	public String baseURL = readconfig.getApplicationURL();
	public String username= readconfig.getUsername();
	public String password= readconfig.getPassword();
	public String title = readconfig.getTitle();

	@Parameters("browser")
	@BeforeClass
	public void setup(String br)
	{
		logger = Logger.getLogger("CoreCreditV1");
		PropertyConfigurator.configure("log4j.properties");
		
		if(br.equals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(ops);
		}
		else if(br.equals("edge"))
		{
			System.setProperty("webdriver.ie.driver",readconfig.getEdgePath());
			driver = new EdgeDriver();			
		}
		

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(baseURL);
		logger.info("URL is opened");
			
		
	}
	
	
	@AfterClass
	public void tearDown()
	{
		//driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"/Screenshots/"+ tname +".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}

	public String randomString()
	{
		String ran = RandomStringUtils.randomAlphabetic(8);
		return (ran);
	}
	
	public String randomNumber()
	{
		String ran2 = RandomStringUtils.randomNumeric(10);
		return (ran2);
	}
	
}
