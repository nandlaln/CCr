package in.corecreditv1.testcases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import in.corecreditv1.pageobject.LoginPage;
import in.corecreditv1.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass{

	@Test(dataProvider = "LoginData")
	public void loginDDT(String user, String pwd) throws InterruptedException
	{
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		logger.info("Entered Username");
		lp.setPassword(pwd);
		logger.info("Entered Password");
		lp.Loginbtn();
		
		Thread.sleep(3000);
		
		if(isAlertPresent()==true)	
		{
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login failed");			
		}
		else
		{
			Assert.assertTrue(true);
			logger.info("logging passed");
			lp.Logoutlink();
			Thread.sleep(3000);			
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}
	}
	
	public boolean isAlertPresent(){			//User Defined method to check alert is present or not
	try {
			driver.switchTo().alert();
			return true;
		}
		catch (NoAlertPresentException ex) {
		       System.out.println("No alert for this test case(In Case of Correct Credential).");
		       return false;
		      } 
	}
	
	
	@DataProvider(name="LoginData")
	String[][] getData() throws IOException
	{
		String path = System.getProperty("user.dir")+"/src/test/java/in/corecreditv1/testdata/DDF.xlsx";
		
		int rownum = XLUtils.getRowCount(path, "ddf");
		int colcount = XLUtils.getCellCount(path, "ddf", 1);
		
		String loginData[][] = new String [rownum][colcount];
		
		for (int i = 1; i<=rownum; i++) 		// Represents row and Starting from 1 because 0 is header.
		{
			for (int j = 0; j<colcount; j++)		// Represent Column and it will start from 0.
			{
				loginData[i-1][j]= XLUtils.getCellData(path, "ddf", i, j);		// Here we are string 1 0 row data into 0 0 index of array because we don't want to take header into array
			}
			
		}
		return loginData;
	}
}
