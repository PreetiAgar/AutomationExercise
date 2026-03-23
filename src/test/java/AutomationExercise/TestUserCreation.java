package AutomationExercise;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
/*Test Case 25: Verify Scroll Up using 'Arrow' button and Scroll Down functionality
1. Launch browser
2. Navigate to url 'http://automationexercise.com'
3. Verify that home page is visible successfully
4. Scroll down page to bottom
5. Verify 'SUBSCRIPTION' is visible
6. Click on arrow at bottom right side to move upward
7. Verify that page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen*/

public class TestUserCreation extends BaseTest {
	 WebDriver driver;
	
		
	@Test(groups="basicTestCase")
	public void scrollUpUsingArrow() {
	String text=landingPage.verifyHomePage();
	
	SoftAssert a = new SoftAssert();
	Assert.assertEquals(text, "Home");
	landingPage.scrollToBottom();
	String text1=landingPage.verifySubscriptionIsVisible();
	System.out.println(text1);
	Assert.assertTrue(text1.equalsIgnoreCase("Subscription"));
	landingPage.scrollUp();
	String text2=landingPage.verifyTextDisplayed();
	System.out.println(text2);
	Assert.assertTrue(text2.equalsIgnoreCase("Full-Fledged practice website for Automation Engineers"));
	a.assertAll();
	}
	

}