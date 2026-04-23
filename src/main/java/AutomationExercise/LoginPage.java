package AutomationExercise;

import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginPage extends AbstractComponents{
	public WebDriver driver;
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(xpath="//div[@class='signup-form']/h2")
	WebElement newSignUpText ;
	
	@FindBy(css="input[placeholder='Name']")
	WebElement Name;

	@FindBy(css="input[data-qa='name']")
	WebElement userName;
	
	@FindBy(css="input[placeholder='Email Address']:nth-child(3)")
	WebElement emailAddress;
	
	@FindBy(xpath="(//button[@type='submit'])[2]")
	WebElement signUpBtn;
	
	@FindBy(xpath="(//h2[@class='title text-center'])[1]/b")
	WebElement enterAccInfo;
	
	@FindBy(xpath="//input[@name='title']")
	List<WebElement> title;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(id="uniform-days")
	WebElement dayDropdown;
	
	@FindBy(id="uniform-months")
	WebElement monthDropdown;
	
	@FindBy(id="uniform-years")
	WebElement yearDropdown;
	
	@FindBy(id="newsletter")
	WebElement newsLetterCheckbox;
	
	@FindBy(id="optin")
	WebElement optinCheckbox;
	
	@FindBy(id="first_name")
	WebElement firstName;
	
	@FindBy(id="last_name")
	WebElement lastName;
	
	@FindBy(id="address1")
	WebElement address;
	
	@FindBy(id="state")
	WebElement state;
	
	@FindBy(id="city")
	WebElement city;
	
	@FindBy(id="zipcode")
	WebElement zipcode;
	
	@FindBy(id="mobile_number")
	WebElement mobileNumber;
	
	public String verifyNewSignUpText() {
		waitForElementToAppear(newSignUpText);
		String signUpText=newSignUpText.getText();
		return signUpText;
	}
	
	public void enterSignUpDetails() {
		waitForElementToBeClickable(Name);
		String userName=generateRandomAlphaNumericString();
		Name.sendKeys(userName);
		emailAddress.sendKeys(generateRandomAlphaNumericEmail());
		waitForElementToBeClickable(signUpBtn);
		signUpBtn.click(); 
	}
	
	public String getUserName() {
		//String userName=
		return userName.getDomAttribute("value");
		//return userName;
		
	}
	
	public String verifyEnterAccountInfoDisplayed() {
		waitForElementToAppear(enterAccInfo);
		String enterAccInfoText=enterAccInfo.getText();
		return enterAccInfoText;
	}
	
	
	public void createAccount() {
		List<WebElement> titleList=title;
		selectRandomOption(titleList);
		String Password=generateRandomAlphaNumericString();
		password.sendKeys(Password);
		dayDropdown.click();
		List<WebElement> daysList=getDropdownList("days");
		selectRandomOption(daysList);
		monthDropdown.click();
		List<WebElement> monthsList=getDropdownList("months");
		selectRandomOption(monthsList);
		yearDropdown.click();
		List<WebElement> yearsList=getDropdownList("years");
		selectRandomOption(yearsList);
		newsLetterCheckbox.click();
		optinCheckbox.click();	
	}
	@FindBy(xpath="//button[@data-qa='create-account']")
	WebElement createAccountBtn;
	
	public void enterAccountInfo(AccountInfo accountInfo) {
		firstName.sendKeys(accountInfo.getFirstname());
		lastName.sendKeys(accountInfo.getLastname());
		address.sendKeys(accountInfo.getAddress());
		state.sendKeys(accountInfo.getState());
		city.sendKeys(accountInfo.getCity());
		zipcode.sendKeys(accountInfo.getZipCode());
		mobileNumber.sendKeys(accountInfo.getMobileNumber());
		createAccountBtn.click();
	}
	
	@FindBy(xpath="//div/h2[@data-qa='account-created']")
	WebElement accountCreated;
	
	public boolean verifyAccountCreatedIsDisplayed() {
		waitForElementToAppear(accountCreated);
		return accountCreated.isDisplayed();
	}
	
	@FindBy(xpath="//a[@data-qa='continue-button']")
	WebElement continueBtn;

	public LandingPage clickContinue() {
		continueBtn.click();
		return new LandingPage(driver);
		
	}
	@FindBy(xpath="//div[@class='login-form']/h2")
	WebElement loginAccount;
	
	public String verifyLoginToAccountIsDisplayed() {
		waitForElementToAppear(loginAccount);
		return loginAccount.getText();
	}
	@FindBy(xpath="//input[@data-qa='login-email']")
	WebElement loginEmail;
	
	@FindBy(xpath="//input[@data-qa='login-password']")
	WebElement loginPassword;
	
	@FindBy(xpath="//button[@data-qa='login-button']")
	WebElement loginBtn;
	
	public LandingPage loginIntoAccount(String email,String password) {
		loginEmail.sendKeys(email);
		loginPassword.sendKeys(password);
		loginBtn.click();
		return new LandingPage(driver);
	}
	
	
	
}
