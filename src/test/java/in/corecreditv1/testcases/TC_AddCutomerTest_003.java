package in.corecreditv1.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import in.corecreditv1.pageobject.AddCustomerPage;
import in.corecreditv1.pageobject.LoginPage;
import in.corecreditv1.utilities.XLUtils;

public class TC_AddCutomerTest_003 extends BaseClass{
	
	public String customerid;

	@Test
	public void addNewCustomer() throws InterruptedException, IOException
	{
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Entered Username");
		lp.setPassword(password);
		logger.info("Entered Password");
		lp.Loginbtn();
		
		Thread.sleep(1000);
		
		AddCustomerPage addCust = new AddCustomerPage(driver);
		addCust.clickonNewCustomer();
		
		try {
		 WebElement frame1 = driver.findElement(By.id("google_ads_iframe_/24132379/INTERSTITIAL_DemoGuru99_0"));
		    driver.switchTo().frame(frame1);
			    driver.findElement(By.xpath("//div[@id='dismiss-button']/div")).click();
			    driver.switchTo().defaultContent();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Providing Customer Details.");		
		
		addCust.custName("Nandlal");
		addCust.custGender("male");
		addCust.custDOB("07", "30", "1996");
		Thread.sleep(2000);
		addCust.custAddress("Kopar Khairane");
		addCust.custCity("Navi Mumbai");
		addCust.custState("Maharashtra");
		addCust.custPin(400709);
		addCust.custTelNo(randomNumber());
		addCust.custEID(randomString()+"@gmail.com");
		addCust.custPassword("Test123");
		logger.info("Entered All information");
		addCust.clkSubmitBtn();
		logger.info("Click on submit");		
		Thread.sleep(2000);
		
		boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");
		
		if (res==true)
		{
			Assert.assertTrue(true);
			logger.info("Testcase passed");		
		
			customerid = driver.findElement(By.xpath("//table[@id='customer']/tbody/tr[4]/td[2]")).getText();
			Thread.sleep(2000);
			setCustomerid();
		}
		else
		{
			captureScreen(driver, "AddNewCustomer");
			Assert.assertTrue(false);
			logger.info("Testcase failed");
			
		}
	}
	
	public void setCustomerid() throws IOException
	{
		String path = System.getProperty("user.dir")+"/src/test/java/in/corecreditv1/testdata/DDF.xlsx";
		
		XLUtils.setCellData(path, "CustomerID", 0, 1, customerid);
	}
	
	
}
