package TestComponents;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import abstractComponents.AbstractComponents;

import pageObjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	public AbstractComponents abstractComponents;
	
	public WebDriver Initializer() throws IOException {
		if(System.getProperty("browser").equalsIgnoreCase("Chrome"))
		{
			driver=new ChromeDriver();
		}
		else if (System.getProperty("browser").equalsIgnoreCase("Edge")) {
			driver=new EdgeDriver();
		}
		
		return driver;
	}
	
	public String TakeScreenshot(String TestName, WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String EvidencePath = "C:\\Users\\Public\\Pictures\\" + TestName + ".png";
		File destination = new File(EvidencePath);
		try {
			FileUtils.copyFile(source,destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EvidencePath;
	}
	
	 @BeforeMethod(alwaysRun=true)
	 public LandingPage LaunchApplication() throws IOException {
		driver=Initializer();
		landingPage = new LandingPage(driver);
		landingPage.GoTo();
		return landingPage;
	}
	 
	 
	 @AfterMethod(alwaysRun=true)
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
}
