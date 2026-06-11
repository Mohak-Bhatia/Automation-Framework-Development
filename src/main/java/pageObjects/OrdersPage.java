package pageObjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents{
	
	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//table/tbody/tr")
	List<WebElement> OrderHistory;
	
	public boolean CheckOrderHistory(String[] DesiredProducts){
		
		List<String> productLists = Arrays.asList(DesiredProducts);
		List<String> OrderedItems = OrderHistory.stream().map(Order -> Order.getText()).filter(Order -> productLists.contains(Order)).toList();
		
		for(String i:productLists) {
			System.out.println(i);
		}
		
		for(String i:OrderedItems) {
			System.out.println(i);
		}
		
		
		
		boolean CheckHistory =  OrderedItems.equals(productLists);
		return CheckHistory;
	}
	
	

}
