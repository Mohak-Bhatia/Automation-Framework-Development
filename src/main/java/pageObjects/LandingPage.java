package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

import abstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement User_Email;
	
	@FindBy(id="userPassword")
	WebElement User_Password;
	
	@FindBy(id="login")
	WebElement Login_Button;
	
//	@FindBy(xpath="//div[@class='toast-bottom-right toast-container']")
	@FindBy(css="[class*='flyInOut']")
	WebElement InvalidAlert;
	
	public void GoTo() {
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		driver.manage().window().maximize();
	}
	
	public ProductCatalogue Login(String Email,String Password) {
		User_Email.sendKeys(Email);
		User_Password.sendKeys(Password);
		Login_Button.click();
		return new ProductCatalogue(driver);
	}
	
	public String GetMessageOnInvalidUser() {
		WaitForElementToAppear(By.cssSelector("[class*='flyInOut']"));
		return InvalidAlert.getText();
	}

}
