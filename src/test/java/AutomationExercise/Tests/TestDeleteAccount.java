package AutomationExercise.Tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import AutomationExercise.AccountInfo;
import AutomationExercise.BaseTest;
import AutomationExercise.LoginPage;
import Utility.JsonReader;

public class TestDeleteAccount extends BaseTest {

	//WebDriver driver;
	@Test
	public void testDeleteAccount() {
		// Read test data from JSON file
		AccountInfo accountInfo = JsonReader.getAccountDataByIndex("src/test/java/resources/TestData.json", 2);
		
		// Verify data was loaded
		if (accountInfo == null) {
			throw new RuntimeException("Failed to load test data");
		}
		
		String email = accountInfo.getEmail();
		String password = accountInfo.getPassword();
		
		LoginPage loginPage=landingPage.clickLogin();
		loginPage.loginIntoAccount(email, password);
		
		SoftAssert a = new SoftAssert();
	landingPage.clickDeleteAccount();
	String accountDeletedText=landingPage.verifyAccountDeletedDisplayed();
	a.assertEquals(accountDeletedText, "ACCOUNT DELETED!");
	landingPage.clickContinue();
	a.assertAll();
	}
}
