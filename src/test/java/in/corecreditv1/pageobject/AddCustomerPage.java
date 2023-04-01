package in.corecreditv1.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {

	WebDriver driver;
	
	public AddCustomerPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);	// this method is used to initialize the web  element.
	}
	
	@FindBy(xpath = "//a[text()='New Customer']")
	@CacheLookup
	WebElement newCustomerLink;
	
	@FindBy(name = "name")
	@CacheLookup
	WebElement customerName;

	@FindBy(name = "rad1")
	@CacheLookup
	WebElement genderRadiobtn;
	
	@FindBy(id = "dob")
	@CacheLookup
	WebElement dob;
	
	@FindBy(name = "addr")
	@CacheLookup
	WebElement address;
	
	@FindBy(name = "city")
	@CacheLookup
	WebElement city;
	
	@FindBy(name = "state")
	@CacheLookup
	WebElement state;	
	
	@FindBy(name = "pinno")
	@CacheLookup
	WebElement pin;
	
	@FindBy(name = "telephoneno")
	@CacheLookup
	WebElement mobile;
	
	@FindBy(name = "emailid")
	@CacheLookup
	WebElement email;
	
	@FindBy(name = "password")
	@CacheLookup
	WebElement password;	
	
	@FindBy(name = "sub")
	@CacheLookup
	WebElement btnSubmit;	
		

	public void clickonNewCustomer()
	{
		newCustomerLink.click();
	}
	
	public void custName(String username)
	{
		customerName.sendKeys(username);
	}
	
	public void custGender(String cgender)
	{
		genderRadiobtn.click();
	}
	
	public void custDOB(String mm, String dd, String yy)
	{
		dob.sendKeys(mm);
		dob.sendKeys(dd);
		dob.sendKeys(yy);
	}
	
	public void custAddress(String address)
	{
		this.address.sendKeys(address);
	}

	public void custCity(String ccity)
	{
		city.sendKeys(ccity);
	}
	
	public void custState(String cstate)
	{
		state.sendKeys(cstate);
	}

	public void custPin(int cpin)
	{
		pin.sendKeys(String.valueOf(cpin));
	}

	public void custTelNo(String ctel)
	{
		mobile.sendKeys(ctel);
	}

	public void custEID(String ceid)
	{
		email.sendKeys(ceid);
	}
	
	public void custPassword(String pwd)
	{
		password.sendKeys(pwd);
	}
	
	public void clkSubmitBtn()
	{	
		btnSubmit.click();
	}
}
