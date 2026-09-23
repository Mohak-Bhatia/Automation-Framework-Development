package TestComponents;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import abstractComponents.AbstractComponents;

import pageObjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	public AbstractComponents abstractComponents;
	
	public WebDriver Initializer(String browser, boolean gridEnabled, String gridUrl) throws IOException {
		String selectedBrowser = System.getProperty("browser", browser).trim().toLowerCase();
		boolean useGrid = Boolean.parseBoolean(System.getProperty("grid.enabled", Boolean.toString(gridEnabled)));
		String selectedGridUrl = System.getProperty("grid.url", gridUrl);

		if (selectedBrowser.equals("chrome")) {
			ChromeOptions options = chromeOptions();
			driver = useGrid ? new RemoteWebDriver(new URL(selectedGridUrl), options) : localChrome(options);
		} else if (selectedBrowser.equals("edge")) {
			EdgeOptions options = edgeOptions();
			driver = useGrid ? new RemoteWebDriver(new URL(selectedGridUrl), options) : localEdge(options);
		} else if (selectedBrowser.equals("firefox")) {
			FirefoxOptions options = firefoxOptions();
			driver = useGrid ? new RemoteWebDriver(new URL(selectedGridUrl), options) : localFirefox(options);
		} else {
			throw new IllegalArgumentException("Unsupported browser: " + selectedBrowser
					+ ". Use chrome, edge, or firefox.");
		}
		return driver;
	}

	private ChromeOptions chromeOptions() {
		ChromeOptions options = new ChromeOptions();
		if (isHeadless()) {
			options.addArguments("--headless=new");
		}
		options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu", "--window-size=1920,1080");
		setBrowserBinary(options, "chromeBinary");
		return options;
	}

	private EdgeOptions edgeOptions() {
		EdgeOptions options = new EdgeOptions();
		if (isHeadless()) {
			options.addArguments("--headless=new");
		}
		options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu", "--window-size=1920,1080");
		setBrowserBinary(options, "edgeBinary");
		return options;
	}

	private FirefoxOptions firefoxOptions() {
		FirefoxOptions options = new FirefoxOptions();
		if (isHeadless()) {
			options.addArguments("-headless");
		}
		options.addArguments("--width=1920", "--height=1080");
		setBrowserBinary(options, "firefoxBinary");
		return options;
	}

	private boolean isHeadless() {
		return Boolean.parseBoolean(System.getProperty("headless", "true"));
	}

	private void setBrowserBinary(ChromeOptions options, String propertyName) {
		String binary = System.getProperty(propertyName);
		if (binary != null && !binary.trim().isEmpty()) {
			options.setBinary(binary);
		}
	}

	private void setBrowserBinary(EdgeOptions options, String propertyName) {
		String binary = System.getProperty(propertyName);
		if (binary != null && !binary.trim().isEmpty()) {
			options.setBinary(binary);
		}
	}

	private void setBrowserBinary(FirefoxOptions options, String propertyName) {
		String binary = System.getProperty(propertyName);
		if (binary != null && !binary.trim().isEmpty()) {
			options.setBinary(binary);
		}
	}

	private WebDriver localChrome(ChromeOptions options) {
		WebDriverManager.chromedriver().setup();
		return new ChromeDriver(options);
	}

	private WebDriver localEdge(EdgeOptions options) {
		WebDriverManager.edgedriver().setup();
		return new EdgeDriver(options);
	}

	private WebDriver localFirefox(FirefoxOptions options) {
		WebDriverManager.firefoxdriver().setup();
		return new FirefoxDriver(options);
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
	 @Parameters({"browser", "grid.enabled", "grid.url"})
	 public LandingPage LaunchApplication(@Optional("chrome") String browser,
			 @Optional("false") String gridEnabled, @Optional("http://localhost:4444") String gridUrl) throws IOException {
		driver=Initializer(browser, Boolean.parseBoolean(gridEnabled), gridUrl);
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
