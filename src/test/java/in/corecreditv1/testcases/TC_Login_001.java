package in.corecreditv1.testcases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import in.corecreditv1.pageobject.LoginPage;

public class TC_Login_001 extends BaseClass {

	@Test
	public void loginTest() throws IOException
	{
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Entered Username");
		lp.setPassword(password);
		logger.info("Entered Password");
		lp.Loginbtn();
		
		try {
		if((alert = driver.switchTo().alert())==null)	
		{
			System.out.println("Alert is not present");
		}
		else
		{
			System.out.println(alert.getText());
			alert.dismiss();
			
		}
		}
		catch (NoAlertPresentException ex) {
		       System.out.println("No alert for this test case(In Case of Correct Credential).");
		      } 

		
		if(driver.getTitle().equals(title))
		{
			Assert.assertTrue(true);
			logger.info("Login test is passed");
		}
		else
		{
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
			logger.info("Login test is failed");
		}
		
	}

}
