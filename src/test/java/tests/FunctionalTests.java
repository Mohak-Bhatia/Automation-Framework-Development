package tests;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import TestComponents.RerunFlaky;
import pageObjects.CartPage;
import pageObjects.CheckOutPage;
import pageObjects.OrdersPage;
import pageObjects.ProductCatalogue;
import testData.TestsData;

public class FunctionalTests extends BaseTest {
	
	String [] DesiredProducts = TestsData.DesiredItems();
	
	@Test(dataProvider="LoginCredentials",dataProviderClass = TestsData.class,retryAnalyzer=RerunFlaky.class)
	public void OpenSiteAndLogin(Map<String,String> TestData) throws IOException {
		
		ProductCatalogue productCatalogue = landingPage.Login(TestData.get("email"), TestData.get("password"));
		
		productCatalogue.GetProducts();
		
		List<WebElement> CartProducts = productCatalogue.FetchProductsForCart(DesiredProducts);
		
		CartPage cartPage=productCatalogue.AddToCart(CartProducts);
		cartPage.WaitForSpinnerToGo();
		cartPage.GoToCart();
		
		List<String> CartItemNames = cartPage.CartItemNames();
		Boolean match = cartPage.Match(DesiredProducts, CartItemNames);
		Assert.assertTrue(match);
		
		CheckOutPage checkout = cartPage.ProceedToCheckout();
		String Country_Name = "india";
		
	    checkout.FillDetails(Country_Name);
		
	}
	
	@Test(dependsOnMethods={"OpenSiteAndLogin"},dataProvider="LoginCredentials",dataProviderClass = TestsData.class,retryAnalyzer=RerunFlaky.class)
	public void VerifyOrderHistory(Map<String,String> TestData) {
		ProductCatalogue productCatalogue = landingPage.Login(TestData.get("email"), TestData.get("password"));
		OrdersPage orderPage = productCatalogue.GoToOrders();
		Boolean match = orderPage.CheckOrderHistory(DesiredProducts);
		Assert.assertTrue(match);
		
	}
	
}
