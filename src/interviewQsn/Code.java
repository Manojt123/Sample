package interviewQsn;

import org.openqa.selenium.support.ui.Select;

import com.gargoylesoftware.htmlunit.javascript.host.Set;

import java.awt.Desktop.Action;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class Code {

	public static void main(String[] args) 
	{
		try
		{
			WebDriver driver = new FirefoxDriver();
			driver.get("https://www.google.co.in/");
			 driver.manage().window().maximize();
			
		  WebElement web =  driver.findElement(By.xpath("//div[@id='viewport']//div[@id='searchform']//form[@id='tsf']//div[@id='sfdiv']//div[@class='gstl_0 sbib_a']"));
					
		  Actions act = new Actions(driver);
		  act.moveToElement(web).build().perform();
		  act.sendKeys("Richest man in").build().perform();
		  Thread.sleep(5000);
		  for(int i=1; i<=6; i++)
		     {
			  	act.sendKeys(Keys.ARROW_DOWN);
		    	System.out.println("iterator  "+i);
		     } 
		  act.click().perform();
		  
		WebElement ele =  driver.findElement(By.xpath("//div[@id='viewport']//div[@id='searchform']//form[@id='tsf']//div[@class='tsf-p']//div[@class='jsb']//input[@value='Google Search']"));
		act.moveToElement(ele).build().perform();
		System.out.println("Google Search Button is Identified");
		Thread.sleep(3000);
		act.click(ele).build().perform();
		
		Thread.sleep(3000);
		WebElement link =  driver.findElement(By.xpath("//body[@id='gsr']//div[@id='main']//div[@class='mw']//div[@id='search']//a[text()='Richest person of Karnataka - YouTube']"));
		act.moveToElement(link).build().perform();
		act.contextClick(link).build().perform();
		act.sendKeys("T").perform();
		
		Set <String> s = driver.getWindowHandles();
		Iterator<String> it = s.Iterator();
		String Parent_id =  it.next();
		String Child_id = it.next();
		System.out.println(Parent_id);
		
		
		
		
	//	Thread.sleep(5000);
		WebElement clk = driver.findElement(By.xpath("//div[@id='body-container']//div[@id='page-container']//div[@id='movie_player']//div[@class='ytp-chrome-bottom']//div[@class='ytp-left-controls']//button[contains(@class,'ytp-play-button ytp-button')]"));
		act.moveToElement(clk).build().perform();
		act.click(clk).build().perform();
		/*Thread.sleep(3000);
		act.click(clk).build().perform();
		Thread.sleep(15000);
		act.click(clk).build().perform();*/
		  //  web.sendKeys("Richest man in the world by Forbes");
			/*Select sel = new Select(web);
			sel.selectByVisibleText("Richest man in the world by Forbes");*/
		}
		catch(Exception e)
		{
			System.out.println("Exception Message:" + e.getMessage());
		}
		
	}

}
