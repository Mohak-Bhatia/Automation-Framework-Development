package pageObjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import abstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void WaitForSpinnerToGo() {
		WaitForElementToDisappear(By.xpath("//div[@id='toast-container']"));
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement GoToCartButton;
	//click cart
	public void GoToCart() {
		GoToCartButton.click();
	}
	
	@FindBy(xpath="//div[@class='cartSection']")
	List<WebElement> CartItems;
	
	public List<String> CartItemNames(){
		List<String> CartItemNames= CartItems.stream().map(CartItem -> CartItem.findElement(By.xpath("h3")).getText()).toList();
		return CartItemNames;
	}
	
	public Boolean Match(String[] DesiredProducts,List<String> CartItemNames) {
		Boolean isMatch =  Arrays.asList(DesiredProducts).stream().anyMatch(productList -> CartItemNames.contains(productList));
		return isMatch;
	}
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement Checkout_btn;
	
	public CheckOutPage ProceedToCheckout() {
		Scroll(0, 800);
		Checkout_btn.click();
		return new CheckOutPage(driver);
	}
	
	
}
