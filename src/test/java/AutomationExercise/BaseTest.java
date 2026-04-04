package AutomationExercise;

import java.io.File;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v145.network.Network;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeBrowser() throws IOException  {
		Properties prop = new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"//src//test//java//resources//GlobalProperties.properties");
		prop.load(fis);
		
		String browserName=System.getProperty("browser")!=null? System.getProperty("browser"): prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			
			ChromeOptions options = new ChromeOptions();

			// Disable annoying UI stuff
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			options.setExperimentalOption("prefs", prefs);
			options.addExtensions(new File("src/test/java/resources/ublock.crx"));

			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);

	        DevTools devTools = ((ChromeDriver) driver).getDevTools();
	        devTools.createSession();
	        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty(),Optional.empty(),Optional.empty()));

			devTools.send(Network.setBlockedURLs( Optional.empty(),Optional.of(Arrays.asList(
				    "*doubleclick.net*",
				    "*googlesyndication.com*",
				    "*ads.*"))
				));
					
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
	
		public List<AccountInfo> getJsondata(String filePath) throws JsonParseException, JsonMappingException, IOException {
		String fileContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
			
		ObjectMapper mapper = new ObjectMapper();
		List<AccountInfo> accountsInfo = Arrays.asList(mapper.readValue(fileContent, AccountInfo[].class));
		  
		 return accountsInfo;
		}
	
}
