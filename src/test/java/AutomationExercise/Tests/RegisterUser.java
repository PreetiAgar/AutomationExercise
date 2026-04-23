package AutomationExercise.Tests;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bouncycastle.asn1.dvcs.Data;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import AutomationExercise.AccountInfo;
import AutomationExercise.BaseTest;
import AutomationExercise.LandingPage;
import AutomationExercise.LoginPage;
import Utility.JsonReader;


/*1. Launch browser
2. Navigate to url 'http://automationexercise.com'
3. Verify that home page is visible successfully
4. Click on 'Signup / Login' button
5. Verify 'New User Signup!' is visible
6. Enter name and email address
7. Click 'Signup' button
8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
9. Fill details: Title, Name, Email, Password, Date of birth
10. Select checkbox 'Sign up for our newsletter!'
11. Select checkbox 'Receive special offers from our partners!'
12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
13. Click 'Create Account button'
14. Verify that 'ACCOUNT CREATED!' is visible
15. Click 'Continue' button
16. Verify that 'Logged in as username' is visible
17. Click 'Delete Account' button
18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button*/
public class RegisterUser extends BaseTest{
	WebDriver driver;

	@Test(dataProvider="getData")
	public void TestRegisterUser(AccountInfo accountInfo) throws JsonParseException, JsonMappingException, IOException {
		String text=landingPage.verifyHomePage();
		
		SoftAssert a = new SoftAssert();
		a.assertEquals(text, "Home");
		LoginPage loginPage =landingPage.clickLogin();
		String newSignUpText=loginPage.verifyNewSignUpText();
		a.assertEquals(newSignUpText,"New User Signup!");
		//String userName= "Preeti";
		//String email="preetiagarwal1993@gmail.com";
		loginPage.enterSignUpDetails();
		String enterAccInfoText=loginPage.verifyEnterAccountInfoDisplayed();
		a.assertEquals(enterAccInfoText,"ENTER ACCOUNT INFORMATION");
		loginPage.createAccount();
		String userName=loginPage.getUserName();
		loginPage.enterAccountInfo(accountInfo);
		Boolean flag=loginPage.verifyAccountCreatedIsDisplayed();
		a.assertTrue(flag);
		LandingPage landingPage=loginPage.clickContinue();
		String actualUsername=landingPage.getLoggedInUserName();
		a.assertEquals(actualUsername, userName);
		
		  
	}
	
	 @DataProvider
	    public Iterator<Object[]> getData() {

	        List<AccountInfo> accounts = JsonReader.getAccountData("src/test/java/resources/TestData.json");

	        return accounts.stream().map(account -> new Object[]{account}).iterator();
	    }
}

	

	

