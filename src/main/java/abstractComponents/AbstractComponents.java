package abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {
	
	WebDriver driver;

	JavascriptExecutor js;
	
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
		this.js = (JavascriptExecutor) this.driver;
	}
	
	public void WaitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void WaitForElementToDisappear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}

	public void WaitForDuration(int t) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(t));
	}
	
	public void Scroll(int horizontal,int vertical) {
		js.executeScript("window.scrollBy(" + horizontal + "," + vertical + ")");
		
	}
}
