package AutomationExercise;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeBrowser() throws IOException  {
		Properties prop = new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//GlobalProperties.properties");
		prop.load(fis);
		
		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			
			ChromeOptions options = new ChromeOptions();

			// Disable annoying UI stuff
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			options.setExperimentalOption("prefs", prefs);
			options.addExtensions(new File("src/main/java/resources/ublock.crx"));

			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);

			
		}else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			 driver=new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			//WebDriverManager.edgedriver().setup();
			System.setProperty("webdriver.edge.driver", "C://drivers//msedgedriver.exe");
			driver=new EdgeDriver();
	
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		return driver;
	}
	@BeforeMethod
	public LandingPage launchApplication() throws IOException {
		try {
			driver=initializeBrowser();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		landingPage	= new LandingPage(driver);
		landingPage.goTo();
		return landingPage ;
	}
	
	@AfterMethod
	public void tearDown(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	
	
}
