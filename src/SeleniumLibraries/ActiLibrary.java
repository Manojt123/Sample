package SeleniumLibraries;

import org.openqa.selenium.*;
//import Libraries.*;


public class ActiLibrary extends CommonPages
{
	public ActiLibrary(WebDriver driver,Report oReport,Parameters oParameters)
	{
		super(driver,oReport,oParameters);
	}

		
	By UserName = By.xpath("//table[@class='login']//table[@class='textFieldsTable']//input[@name='username']");
	
	By Password = By.xpath("//table[@class='login']//table[@class='textFieldsTable']//input[@name='pwd']");
	
	By Login = By.xpath("//td[@id='loginButtonContainer']/a/div[contains(text(),'Login')]");
	
	
	//This Method is used to Login to Application
	public void login(String loginType)
	{
		oParameters.SetParameters("CONTINUED_EXECUTION", "YES");
		
		oParameters.SetParameters("LoginType", loginType);
		if(oParameters.GetParameters("LoginType").equalsIgnoreCase("admin"))
		{
			try
			{
				enter_text_value("User Name", UserName, oParameters.GetParameters("ADMIN_USER_NAME"));
				enter_text_value("Pass word", Password, oParameters.GetParameters("ADMIN_PASSWORD"));
				clickButton("Login Button", Login);
			}
			catch(Exception e)
			{
				System.out.println("Login admin Exception Caught :" +e.getMessage());
			}
		}
		else if(oParameters.GetParameters("LoginType").equalsIgnoreCase("Users"))
		{
			try
			{
				enter_text_value("User Name", UserName, oParameters.GetParameters("USERS_USER_NAME"));
				enter_text_value("Pass word", Password, oParameters.GetParameters("USERS_PASSWORD"));
				clickButton("Login Button", Login);
			}
			catch(Exception e)
			{
				System.out.println("Login User Exception Caught :"+e.getMessage());
			}
		}
	}
	
	
	By logout = By.xpath("//a[text()='Logout']");
	
	public void logout()
	{
		fixed_wait_time(1);
		clickButton("Logout Button",logout);
		
		waitFor(logout);
		
		if(IsElementDisplayed("Logout Button", logout))
			System.out.println("Logout Successfully logged out of portal");
	}
}
