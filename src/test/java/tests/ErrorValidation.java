package tests;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import TestComponents.RerunFlaky;
import pageObjects.CartPage;
import pageObjects.ProductCatalogue;
import testData.TestsData;

public class ErrorValidation extends BaseTest {
	
	String [] DesiredProducts = TestsData.DesiredItems();
	
	@Test(dataProvider="InvalidLoginCredentials",dataProviderClass = TestsData.class)
	public void InvalidLogin(Map<String,String> TestData) {
		
		landingPage.Login(TestData.get("email"), TestData.get("password"));
		System.out.println(landingPage.GetMessageOnInvalidUser());
		Assert.assertEquals("Incorrect email or password.", landingPage.GetMessageOnInvalidUser());
	}
	
	@Test(groups= {"Products And Cart"},dataProvider="LoginCredentials",dataProviderClass = TestsData.class,retryAnalyzer=RerunFlaky.class)
	public void ProductsCheck(Map<String,String> TestData) {
		
		ProductCatalogue productCatalogue = landingPage.Login(TestData.get("email"), TestData.get("password"));
		
		productCatalogue.GetProducts();
		List<WebElement> CartProducts = productCatalogue.FetchProductsForCart(DesiredProducts);
		
		CartPage cartPage=productCatalogue.AddToCart(CartProducts);
		cartPage.WaitForSpinnerToGo();
		cartPage.GoToCart();
		
		List<String> CartItemNames = cartPage.CartItemNames();
		Boolean match = cartPage.Match(DesiredProducts, CartItemNames);
		Assert.assertTrue(match);
		
	}

}
