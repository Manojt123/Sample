package SeleniumLibraries;

import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browser 
{
	public static WebDriver driver;
	public String  ENVIRONMENT = "Actitime";
	//public String  ENVIRONMENT1 = "AC_Local";
	
	public Browser()
	{
		String wikiURL = "";
		
		if(ENVIRONMENT.equalsIgnoreCase("Actitime"))
			wikiURL="http://localhost/login.do";
		try
		{
			driver.navigate().to(wikiURL);
		}
		catch(Exception arg)
		{
			System.out.println("Exception caught : " + arg.getMessage());
		}
	}
	
	
	protected WebDriver local_createDriver(String string)
	{
		WebDriver browser = null;
		
		String relativePath = "";
		
		relativePath = "C:/";//here we to add chromedriver.exe file.
		
		switch(string)
		{
			case "CHROME" :
				
				ChromeOptions cOptions = new ChromeOptions();
				cOptions.addArguments("disable-infobars");
				cOptions.addArguments("--strat-maximized");
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("Credentials_enabled_service",false);
				prefs.put("profile.password_manager_enabled", false);
				cOptions.setExperimentalOption("prefs", prefs);
				System.setProperty("Webdriver.chrome.driver",relativePath + "chromedriver.exe");
				
				browser = new ChromeDriver(cOptions);
				break;
				
			case "FIREFOX" :
				browser = new FirefoxDriver();
				break;
		}
		
		browser.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return browser;
	}
	
	
	public void cleanup()
	{
		try
		{
			driver.close();
			driver.quit();
		}
		catch(Exception exp)
		{
			System.out.println("Cleanup Exception : " + exp.getMessage());
		}
	}
}
