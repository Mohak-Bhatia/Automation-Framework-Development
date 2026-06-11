package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class CheckOutPage  extends AbstractComponents {
	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement SelectCountryDropDown;
	
	
	@FindBy(xpath="//a[text()='Place Order ']")
	WebElement PlaceOrderButton;
	
	@FindBy(xpath="//i[@class='fa fa-search']")
	List<WebElement> Countries;
	
	public void FillDetails(String Country_Name) {
		Actions a = new Actions(driver);
		a.sendKeys(SelectCountryDropDown, Country_Name).build().perform();
		for(WebElement e:Countries) {
			if(e.getText().equalsIgnoreCase(Country_Name)) {
				e.click();
			}
		}
		PlaceOrderButton.click();
	}
	
	
	

}
