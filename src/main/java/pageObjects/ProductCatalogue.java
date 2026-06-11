package pageObjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import abstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//div[@class='card-body']")
	List<WebElement> Products;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement OrderPage;
	
	public List<WebElement> GetProducts(){
		WaitForElementToAppear(By.xpath("//div[@class='card-body']"));
		return Products;
		
	}
	
	public List<WebElement> FetchProductsForCart(String [] DesiredProducts){
		List<String> productLists = Arrays.asList(DesiredProducts);
		
		List<WebElement> AddToCart = Products.stream().filter(Product -> productLists.contains(Product.findElement(By.xpath("h5/b")).getText())).toList();
		
		return AddToCart;
	}
	
	public CartPage AddToCart(List<WebElement> AddToCart) {
		for(WebElement e:AddToCart) {
			System.out.println(e.getText());
			e.findElement(By.xpath("button[2]")).click();
			WaitForElementToDisappear(By.cssSelector(".ng-tns-c11-1.ng-trigger.ng-trigger-fadeIn.ng-star-inserted.ng-animating"));
		}
		return new CartPage(driver) ;
	}
	
	public OrdersPage GoToOrders() {
		WaitForElementToAppear(By.xpath("//button[@routerlink='/dashboard/myorders']"));
		OrderPage.click();
		return new OrdersPage(driver);
	}
	
	
	
	
}
