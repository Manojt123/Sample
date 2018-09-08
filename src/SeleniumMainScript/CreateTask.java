package SeleniumMainScript;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreateTask {

	static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException
	{
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost/login.do");
		driver.findElement(By.id("username")).sendKeys(arg0);
		Thread.sleep(3000);
//	    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@name='pwd']")).sendKeys("raj12345");
		driver.findElement(By.xpath("//div[contains(text(),'Login')]")).click();
	}

}
