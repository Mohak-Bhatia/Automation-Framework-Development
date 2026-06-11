package testData;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;

public class TestsData {
	
	@DataProvider
	public static Object[][] LoginCredentials(){
		Map<String,String> TestData1 = new HashMap<>(); 
		TestData1.put("email","bhatiamohak1702@gmail.com");
		TestData1.put("password","2204@50Mb");
		
		Map<String,String> TestData2 = new HashMap<>(); 
		TestData2.put("email","bhatiaanishka3108@gmail.com");
		TestData2.put("password","Ani@bha3108");
		
		return new Object[][] {{TestData1},{TestData2}};
	}
	
	public static String[] DesiredItems() {
		String[] DesiredItems = { "ADIDAS ORIGINAL", "ZARA COAT 3" };
		return DesiredItems;
	}
	
	@DataProvider
	public static Object[][] InvalidLoginCredentials(){
		Map<String,String> TestData1 = new HashMap<>(); 
		TestData1.put("email","avina@gmail.com");
		TestData1.put("password","Moh@bha1702");
		
		Map<String,String> TestData2 = new HashMap<>(); 
		TestData2.put("email","anishka3108@gmail.com");
		TestData2.put("password","4408@100Mb");
		
		return new Object[][] {{TestData1},{TestData2}};
	}

}
