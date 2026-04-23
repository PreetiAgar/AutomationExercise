package AutomationExercise.Tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import AutomationExercise.BaseTest;
import AutomationExercise.LandingPage;
import AutomationExercise.LoginPage;
/*4. Click on 'Signup / Login' button
5. Verify 'Login to your account' is visible
6. Enter correct email address and password
7. Click 'login' button
8. Verify that 'Logged in as username' is visible
9. Click 'Logout' button
10. Verify that user is navigated to login page*/
public class TestLogout extends BaseTest {
	WebDriver driver;
	
	@Test
	public void testLogout() {
		String text=landingPage.verifyHomePage();
		Assert.assertEquals(text, "Home");
		LoginPage loginPage=landingPage.clickLogin();
		String text1=loginPage.verifyLoginToAccountIsDisplayed();
		Assert.assertEquals(text1, "Login to your account");
		
		String email="preetiagarwal1993@gmail.com"; String Password="bubba@1106";
		
		LandingPage landingPage=loginPage.loginIntoAccount(email, Password);
		landingPage.clickLogout();
		
		
	}

}
