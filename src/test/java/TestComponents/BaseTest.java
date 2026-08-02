package TestComponents;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
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
			WebDriverManager.chromedriver().setup();

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments(
                "--headless=new",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--disable-gpu",
                "--window-size=1920,1080",
                "--remote-allow-origins=*"
            );
            String chromeBinary = System.getProperty("chromeBinary", "/usr/bin/google-chrome");
            chromeOptions.setBinary(chromeBinary);

            chromeOptions.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
            chromeOptions.setExperimentalOption("useAutomationExtension", false);

            ChromeDriverService chromeService = new ChromeDriverService.Builder()
                .usingAnyFreePort()
                .withVerbose(true)
                .withLogFile(new File("chromedriver.log"))
                .build();

            driver = new ChromeDriver(chromeService, chromeOptions);
            
		}
		else if (System.getProperty("browser").equalsIgnoreCase("Edge")) {
			
            WebDriverManager.edgedriver().setup();

            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments(
                "--headless=new",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--disable-gpu",
                "--window-size=1920,1080",
                "--remote-allow-origins=*"
            );
            // Allow binary override from CI if needed
            String edgeBinary = System.getProperty("edgeBinary", "/usr/bin/microsoft-edge");
            edgeOptions.setBinary(edgeBinary);

            edgeOptions.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
            edgeOptions.setExperimentalOption("useAutomationExtension", false);

            EdgeDriverService edgeService = new EdgeDriverService.Builder()
                .usingAnyFreePort()
                .withVerbose(true)
                .withLogFile(new File("edgedriver.log"))
                .build();

            driver = new EdgeDriver(edgeService, edgeOptions);
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
