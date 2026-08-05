package AutomationExercise.Tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import AutomationExercise.BaseTest;
import AutomationExercise.LoginPage;

public class TestDeleteAccount extends BaseTest {
//add demo comment to practice git conflict
	WebDriver driver;
	@Test
	public void testDeleteAccount() {
		
		LoginPage loginPage=landingPage.clickLogin();
		String email="preetiagarwal.official@gmail.com"; String Password="bubba@1106";
		loginPage.loginIntoAccount(email, Password);
		
		SoftAssert a = new SoftAssert();
	landingPage.clickDeleteAccount();
	String accountDeletedText=landingPage.verifyAccountDeletedDisplayed();
	a.assertEquals(accountDeletedText, "ACCOUNT DELETED!");
	loginPage.clickContinue();
	}
}
