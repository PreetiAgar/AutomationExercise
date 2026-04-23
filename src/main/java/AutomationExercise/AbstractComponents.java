package AutomationExercise;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AbstractComponents {
	WebDriver driver;
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public void waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitForElementToAppear(WebElement element) {
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToAppear(By by) {
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
		
	    public List<WebElement> getDropdownList(String elementName) {
	    Select select = new Select(driver.findElement(By.id(elementName)));
	    List<WebElement> options = select.getOptions();
	    return options;
	    }
	    
	    public void selectRandomOption(List<WebElement> elements) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	    
	    Random random = new Random();
	    int index = random.nextInt(elements.size() - 1) + 1;
	    WebElement element = elements.get(index);
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
	    wait.until(ExpectedConditions.elementToBeClickable(element));
	    element.click();
	    }
	
	public String generateRandomAlphaNumericString()
	{	
	String Password = RandomStringUtils.randomAlphabetic(8);
	return Password;
	}
	
	public String generateRandomAlphaNumericEmail()
	{	
	String email = "test" + System.currentTimeMillis() + "@gmail.com";
	return email;
	}
	
	
}
