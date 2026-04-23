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
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
	
	public static ThreadLocal<WebDriver> driver=new ThreadLocal<>();
	public LandingPage landingPage;
	
	public static WebDriver getDriver()
	{
	    return driver.get();
	}
	
	public ThreadLocal<WebDriver> initializeBrowser() throws IOException  {
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
			driver.set(new ChromeDriver(options));
			//driver=new ChromeDriver(options);
	        /*DevTools devTools = ((ChromeDriver) driver).getDevTools();
	        devTools.createSession();
	        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty(),Optional.empty(),Optional.empty()));

			devTools.send(Network.setBlockedURLs( Optional.empty(),Optional.of(Arrays.asList(
				    "*doubleclick.net*",
				    "*googlesyndication.com*",
				    "*ads.*"))
				));*/
					
		}else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			//WebDriverManager.edgedriver().setup();
			System.setProperty("webdriver.edge.driver", "C://drivers//msedgedriver.exe");
			driver.set(new EdgeDriver());
	
		}
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		return driver;
	}
	
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		try {
			driver=initializeBrowser();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		landingPage	= new LandingPage(getDriver());
		landingPage.goTo();
		return landingPage ;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown(){
		if (getDriver() != null) {
		getDriver().manage().deleteAllCookies();
		getDriver().quit();
		driver.remove();   //memory cleanup
		}
	}
	
		public List<AccountInfo> getJsondata(String filePath) throws JsonParseException, JsonMappingException, IOException {
		String fileContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
			
		ObjectMapper mapper = new ObjectMapper();
		List<AccountInfo> accountsInfo = Arrays.asList(mapper.readValue(fileContent, AccountInfo[].class));
		  
		 return accountsInfo;
		}
	
		public String getScreenshot(String testName) throws IOException {
			 File src=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
			 File file=new File(System.getProperty("user.dir") + "/reports/" +testName+".png");
			 FileUtils.copyFile(src,file);
			 return System.getProperty("user.dir") + "/reports/" +testName+".png";
		}
}
