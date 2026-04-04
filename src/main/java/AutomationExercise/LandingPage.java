package AutomationExercise;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LandingPage extends AbstractComponents  {
	
	public WebDriver driver;

	public LandingPage (WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	

	@FindBy(xpath="//a[@style='color: orange;']")
	WebElement home;
	
	@FindBy(xpath="//a[@href='/login']")
	WebElement login;
	
	@FindBy(xpath="//a[@id='scrollUp']")
	WebElement arrowBtn;
	

	@FindBy(xpath="//div[@class='single-widget']//h2")
	WebElement subscriptionText;
	
	@FindBy(xpath="//div[@class='col-sm-6']//h2")
	WebElement pageText;
	
	
	/*public AdminPage login(String username,String password)
	{
	waitForElementToBeClickable(Username);
	Username.sendKeys(username);
	Password.sendKeys(password);
	waitForElementToBeClickable(Loginbtn);
	Loginbtn.click();
	return new AdminPage(driver);
	}*/
	
	public void goTo() {
		driver.get("https://automationexercise.com/");
	}

	public String verifyHomePage() {
		String text=home.getText();
		return text;
	}

	public void scrollToBottom() {
		//WebElement element = 
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}
	
	public String verifySubscriptionIsVisible() {
		String text1= subscriptionText.getText();
		return text1;
	}

	public void scrollUp() {
		arrowBtn.click();
	}

	public String verifyTextDisplayed() {
		waitForElementToAppear(pageText);
		String text2=pageText.getText().trim();
		return text2;
	}
	
	public LoginPage clickLogin() {
		login.click();
		return new LoginPage(driver);
	}
}
	
	
	
	

