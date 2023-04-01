package in.corecreditv1.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="uid")
	@CacheLookup
	WebElement username;
	
	@FindBy(name="password")
	@CacheLookup
	WebElement password;
	
	@FindBy(name="btnLogin")
	@CacheLookup
	WebElement login;

	@FindBy(xpath="//a[text()='Log out']")
	@CacheLookup
	WebElement logout;
	
	public void setUserName(String uname)
	{
		username.sendKeys(uname);
	}

	public void setPassword(String pwd)
	{
		password.sendKeys(pwd);
	}
	
	public void Loginbtn()
	{
		login.click();
	}
	
	public void Logoutlink()
	{
		logout.click();
	}

}
