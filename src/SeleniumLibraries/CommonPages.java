package SeleniumLibraries;



import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CommonPages extends TestData
{
	protected static WebDriver driver;
	protected long StartTime;
	protected Report oReport;
	protected Parameters oParameters;
	public  WebElement elemdesc;
	static Actions oActions = new Actions(driver);
	
	@SuppressWarnings("static-access")
	public CommonPages(WebDriver driver,Report oReport,Parameters oParameters)
	{
		this.driver = driver;
		this.oReport = oReport;
		this.oParameters = oParameters;
	}
	
	
	public static boolean moveToElement(String FeildName,By elemdesc)
	{
		WebElement ElemDesc = driver.findElement(elemdesc);
		
		if(ElemDesc.isDisplayed())
		{	
			System.out.println("IsElementDisplayed : FeildName :" +FeildName + ":True");
			oActions.moveToElement(ElemDesc).build().perform();
			return true;
		}
		else
		{
			System.out.println("IsElementDisplayed : FeildName :" +FeildName + ":False");
			return false;
		}
	}
	
	public static boolean dclick(String FeildName,By elemdesc)
	{
		try
		{
			WebElement ElemDesc = driver.findElement(elemdesc);
			
			if(ElemDesc.isDisplayed())
			{
				System.out.println("IsElementDisplayed : FeildName :" +FeildName + ":True");
				oActions.click(ElemDesc).build().perform();
				return true;
			}
			else
			{
				System.out.println("IsElementDisplayed : FeildName :" +FeildName + ":False");
				return false;
			}
		}
		catch(Exception expobj)
		{
			System.out.println("Exception Message:" + expobj.getMessage());
		}
		return false;
	}
	
	public static void selectIndex(String Element,By elemdesc,int n1)
	{	
		try
		{
			WebElement ElemDesc = driver.findElement(elemdesc);
			
			if(ElemDesc.isDisplayed())
			{
				Select sel = new Select(ElemDesc);
				sel.selectByIndex(n1);
			}
			else
				System.out.println("This "+Element+" Not Found");
		}
		catch(Exception e)
		{
			System.out.println("Exception Message:" + e.getMessage());
		}
		
	}
	
	public static void selectValue(String FeildName,By elemdesc,String value )
	{
		try
		{
			WebElement ElemDesc = driver.findElement(elemdesc);
			
			if(ElemDesc.isDisplayed())
			{
				Select sel = new Select(ElemDesc);
				sel.selectByValue(value);
			}
			else
				System.out.println("This "+FeildName+" Not Found");
		}
		catch(Exception expobj)
		{
			System.out.println("Excpetion Message:" + expobj.getMessage());
		}
	}
	
	public static void enter_text_value(String FieldName, By elemDesc, String FVtoEnter)
	{
		try
		{
			WebElement ElemDesc = driver.findElement(elemDesc);
			
			if(ElemDesc.isDisplayed()) 
			{	
				System.out.println("enter_text_value : FeildName Found :" +FieldName + ":True");
			    ElemDesc.clear();
			    fixed_wait_time(1);
			    ElemDesc.sendKeys(FVtoEnter);
			 
			   if(FieldName.equalsIgnoreCase("Password"))
				   System.out.println("enter_text_value : FeildName :" +FieldName + ":True");
			   else
				   System.out.println("enter_text_value : FeildName :  " +FieldName + " Value: " +FVtoEnter+ " False");
			}
			else
				System.out.println("enter_text_value : FeildName NotFound :" +FieldName + ":False");
				
		}
		catch(Exception eObj)
	   {
			System.out.println("Unhandled Error : " + eObj.getMessage());
	   }
	}
	
	
	public static void fixed_wait_time(int number)
	{
		try 
		{
			Thread.sleep(number);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception Message:" +e.getMessage());
		}
	}
	
	 public static boolean IsElementSelected(String FieldName, By elemDesc)
	 {
		 try
		 {
			 WebElement ElemDesc = driver.findElement(elemDesc);
			 
			 if(ElemDesc.isSelected())
			 {
				 System.out.println("IsElementSelected : FieldName :" + FieldName + " ## True");
				 return true;
			 }
			 else
			 {
				 System.out.println("IsElementSelected : FieldName :" + FieldName + " ## False");
				 return false;
			 }
		 }
		 catch(Exception eObj)
		 {
			 System.out.println("IsElementSelected - Exception : " + eObj.getMessage());
			 return false;
		 }
	 }
	 
	 public boolean IsElementDisplayed(String FieldName, By elemDesc)
	 {
		 try
		 {
			 WebElement ElemDesc = driver.findElement(elemDesc); 
			 
			 if(ElemDesc.isDisplayed())
			 {
				 System.out.println("IsElementDisplayed : FieldName :" + FieldName + " ## True");
				 return true;
			 }
			 else
			 {
				 System.out.println("IsElementDisplayed : FieldName :" + FieldName + " ## False");
				 return false;
			 }
		 }
		 catch(Exception eObj)
		 {
			 System.out.println("IsFieldDisplayed - Exception : " + eObj.getMessage());
			 return false;
		 }
	 }
	 
	 public boolean IsElementEnabled(String FieldName, By elemDesc)
	 {
		 try
		 {
			 WebElement ElemDesc = driver.findElement(elemDesc); 
			 
			 if(ElemDesc.isEnabled())
			 {
				 System.out.println("IsElementEnabled : FieldName :" + FieldName + " ## True");
				 return true;
			 }
			 else
			 {
				 System.out.println("IsElementEnabled : FieldName :" + FieldName + " ## False");
				 return false;
			 }
		 }
		 catch(Exception eObj)
		 {
			 System.out.println("IsFieldEnabled - Exception : " + eObj.getMessage());
			 return false;
		 }
	 }
	 
	 public boolean clickButton(String FieldName, By elemDesc)
	 {
		 try
		 {
			 WebElement ElemDesc = driver.findElement(elemDesc);
			 
			 if(ElemDesc.isDisplayed())
			 {
				 ElemDesc.click();
				 System.out.println("clickButton : FieldName :" + FieldName + " ## True");
				 return true;
			 }
			 else
			 {
				 System.out.println("clickButton : FieldName :" + FieldName + " ## False");
				 return false;
			 }
		 }
		 catch(Exception eObj)
		 {
			 System.out.println("clickButton - Exception : " + eObj.getMessage());
			 return false;
		 }
	 }
	 
	 public static void softAssertEqual(String act,String exp)
	 {
		 try
		 {
			 softAssertEqual(act, exp);
		 
			 System.out.println("Name entered in the Text Box is correct : Pass");
		 }
		 catch(Exception eObj)
		 {
			 System.out.println("Exception Message:" +eObj.getMessage());
		 }
	 }
	 
	 public static void alertPopup(String alt)
	 {
		 try
		 {
			 Alert popup = driver.switchTo().alert();
		
			 if(alt == "ok")
			 {
				 System.out.println("Popup appeared :" +popup.getText()+ "and performed ok"); 
				 popup.accept();
			 }
			else
			{
				 System.out.println("Popup appeared :" +popup.getText()+ "and performed Cancel");
				 popup.dismiss();	
			}
		 }
		 catch(Exception eObj)
		 {
			 System.out.println("Exception Message:" +eObj.getMessage());
		 }
	 }
	 
	 public static WebElement convertToWebElement(By elemdesc)
	 {
		WebElement oWebelement = driver.findElement(elemdesc);
		return oWebelement;
	 }
	 
	 public boolean waitFor(By elemdesc)
	 {
		 try
		 {
			 fixed_wait_time(1);
			 WebDriverWait wait = new WebDriverWait(driver,10);
			 
			 WebElement finder = wait.until(ExpectedConditions.visibilityOfElementLocated(elemdesc));
			 
			 return finder.isDisplayed();
		 }
		 catch(Exception eobj)
		 {
			 System.out.println("Exception :" + eobj.getMessage());
		 }
		return false;
	 }
}
