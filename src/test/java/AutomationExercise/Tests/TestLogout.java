package AutomationExercise.Tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import AutomationExercise.AccountInfo;
import AutomationExercise.BaseTest;
import AutomationExercise.LandingPage;
import AutomationExercise.LoginPage;
import Utility.JsonReader;
/*4. Click on 'Signup / Login' button
5. Verify 'Login to your account' is visible
6. Enter correct email address and password
7. Click 'login' button
8. Verify that 'Logged in as username' is visible
9. Click 'Logout' button
10. Verify that user is navigated to login page*/
public class TestLogout extends BaseTest {
	//WebDriver driver;
	
	@Test
	public void testLogout() {
		// Read test data from JSON file - using index 0 (Preeti's data)
		AccountInfo accountInfo = JsonReader.getAccountDataByIndex("src/test/java/resources/TestData.json", 0);
		
		if (accountInfo == null) {
			throw new RuntimeException("Failed to load test data");
		}
		
		String email = accountInfo.getEmail();
		String password = accountInfo.getPassword();
		
		String text=landingPage.verifyHomePage();
		Assert.assertEquals(text, "Home");
		
		LoginPage loginPage=landingPage.clickLogin();
		String text1=loginPage.verifyLoginToAccountIsDisplayed();
		Assert.assertEquals(text1, "Login to your account");
		
		//String email="preetiagarwal1993@gmail.com"; String Password="bubba@1106";
		
		LandingPage loggedInPage=loginPage.loginIntoAccount(email, password);
		String loggedInUsername = loggedInPage.getLoggedInUserName();
		Assert.assertNotNull(loggedInUsername, "User should be logged in");
		
		LoginPage logoutPage = loggedInPage.clickLogout();
		String loginText = logoutPage.verifyLoginToAccountIsDisplayed();
		Assert.assertEquals(loginText, "Login to your account", "User should be on login page after logout");
		
		
	}

}
