package SeleniumLibraries;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.*;

public class Report 
{
	protected ExtentReports report;
	protected ExtentTest logger;
	protected WebDriver driver;
	protected Parameters oParameters;
	int passed = 0;
	int failed = 0;
	String failedScenarios = "";
	
	public Report(WebDriver driver, Parameters opParameters)
	{
		this.driver = driver;
		this.oParameters = oParameters;
		
		initialize_reports();
	}
	
	
	public void cleanup()
	{
		System.out.println("------------ ---------- ---------------");
		System.out.println("------------ TEST CLEAN UP START ---------");
		System.out.println("----------- ----------- ---------------");
		
		if(oParameters.GetParameters("CONTINUED_EXECUTION").toUpperCase().equalsIgnoreCase("YES"))
			AddStepResult("Completed Execution", "VR executed completely", "INFO");
		else if(oParameters.GetParameters("CONTINUED_EXECUTION").toUpperCase().equalsIgnoreCase("FAIL"))
			AddStepResult("Completed Execution", "VR executed completely and failures were found", "FAIL");
		else if(oParameters.GetParameters("CONTINUED_EXECUTION").toUpperCase().equalsIgnoreCase("NO"))
			AddStepResult("Completed Execution", "VR execution aborted as fatal issues were found", "ERROR");
		
		try
		{
			report.flush();
			report.endTest(logger);
			
			System.out.println("VR level Report Created");
			
			oParameters.SetParameters("PASSED_STEPS", String.valueOf(passed));
			oParameters.SetParameters("FAILED_STEPS", String.valueOf(failed));
			oParameters.SetParameters("FAILED_SCENARIO_DISCRIPTION", failedScenarios);
			
			AddClassStep();
		}
		catch(Exception exp)
		{
			System.out.println("Cleanup Exception 1 :" + exp.getMessage());
			System.out.println("Vr level Report creation failed");
		}
		
		System.out.println("------------ ---------- ---------------");
		System.out.println("------------ TEST CLEAN UP END --------------");
		System.out.println("----------- ----------- ---------------");
	}
	
	
	public String getscreenshot()
	{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		String scrShotPath = oParameters.GetParameters("ScreenShotPath")+"/"+System.currentTimeMillis();//"C:/Selenium Report/Screenshots/"+System.currentTimeMillis();
	
		try
		{
			FileUtils.copyFile(scrFile, new File(scrShotPath));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return scrShotPath;
	}
	
	
	public void AddStepResult(String StepName,String StepDescription,String StepResult)
	{
		
		System.out.println(StepName + " - " + StepResult + " - " + StepDescription);
		
		if(StepResult.toUpperCase().equalsIgnoreCase("PASS"))
		{
			logger.log(LogStatus.PASS,StepDescription + " : "+StepResult,logger.addScreenCapture(getscreenshot()));
			
			passed++;
			
			//pushing step result to hash table
			oParameters.SetParameters(StepDescription, StepResult);
			
			report.flush();
		}
		else if(StepResult.toUpperCase().equalsIgnoreCase("FAIL"))
		{
			logger.log(LogStatus.PASS,StepDescription + " : "+StepResult,logger.addScreenCapture(getscreenshot()));
			
			failed++;
			
			if(failed==1)
				failedScenarios = StepDescription;
			else if(failed > 1 && !failedScenarios.isEmpty())
				failedScenarios = failedScenarios +"\\n"+ StepDescription;
			
			//pushing step result to hash table
			oParameters.SetParameters("JIRADESCRIPTION", "Environment : " + oParameters.GetParameters("ENVIRONMENT")+ "\\n" + failedScenarios);
			oParameters.SetParameters(StepDescription, StepResult);
			
			report.flush();
			oParameters.SetParameters("CONTINUED_EXECUTION", "FAIL");
		}
		else if(StepResult.toUpperCase().equalsIgnoreCase("FATAL"))
		{
			logger.log(LogStatus.FATAL, StepDescription, StepResult);
			
			//pushing step reult to hash table
			oParameters.SetParameters(StepName +"_"+ StepDescription,StepResult);
			report.flush();
			oParameters.SetParameters("CONTINUED_EXECUTION", "NO");
		}
		else if(StepResult.toUpperCase().equalsIgnoreCase("ERROR"))
		{
			logger.log(LogStatus.ERROR, StepDescription, StepResult);
			
			//pushing step result to hash table
			oParameters.SetParameters(StepName +"- " + StepDescription, StepResult);
			
			report.flush();
			oParameters.SetParameters("CONTINUED_EXECUTION", "NO");
		}
		else if(StepResult.toUpperCase().equalsIgnoreCase("WARNING"))
		{
			logger.log(LogStatus.WARNING, StepDescription, StepResult);
			
			//pushing step result to hash table
			oParameters.SetParameters(StepName +"- " + StepDescription, StepResult);
			
			report.flush();
		}
		else if(StepResult.toUpperCase().equalsIgnoreCase("UNKNOWN"))
		{
			logger.log(LogStatus.UNKNOWN, StepDescription, StepResult);
			
			//pushing step result to hash table
			oParameters.SetParameters(StepName +"- " + StepDescription, StepResult);
			
			report.flush();
			oParameters.SetParameters("CONTINUED_EXECUTION", "NO");
		}
		else if("INFO|DONE".contains(StepResult))
		{
			logger.log(LogStatus.INFO, StepDescription, StepResult);
			
			//pushing step result to hash table
			oParameters.SetParameters(StepName +"- " + StepDescription, StepResult);
			
			report.flush();
		}
		else if(StepResult.toUpperCase().equalsIgnoreCase("SCREENSHOT"))
		{
			logger.log(LogStatus.INFO, StepDescription + " : "+ StepResult,logger.addScreenCapture(getscreenshot()));
			
			oParameters.SetParameters(StepName +"_", StepResult);
			
			report.flush();
		}
	}
	
	
	private void initialize_reports()
	{
		String reportPath = null;
		String vrname = oParameters.GetParameters("TESTNAME");
		
		if(oParameters.GetParameters("Report_PATH").equalsIgnoreCase("LOCAL"))
			reportPath = "C:/CCM/ExecutionReport/" + oParameters.GetParameters("ENVIRONMENT")+"/"+oParameters.GetParameters("CLASSNAME")+"/"+vrname+"/"+vrname+"-"+System.currentTimeMillis();
		else
			System.out.println("Virtual is not ready");
		
		String reportName = report+"/"+vrname+"-"+System.currentTimeMillis()+".Html";
		
		oParameters.SetParameters("HTML_REPORT_NAME", reportName);
		
		boolean isFolderCreated = (new File(reportPath)).mkdirs();
		
		if(isFolderCreated)
			System.out.println("Report Folder created");
		else
			System.out.println("Report Folder already exist");
		
		isFolderCreated = (new File(reportPath + File.separator + "Screenshots")).mkdirs();
		
		if(isFolderCreated)
			System.out.println("Screenshots Folder created");
		else
			System.out.println("Screenshots Folder already exist");
		
		report = new ExtentReports(reportName,true);
		
		logger = report.startTest(vrname);
		
		if(oParameters.GetParameters("HEADLESS").equalsIgnoreCase("YES"))
			AddStepResult("HEADLESS", "Ran in Headless Mode", "INFO");
		else
			System.out.println("VR Ran in normal chrome");
		
		oParameters.SetParameters("ScreenShotPath", reportPath+"/Screenshots");
	}
	
	
	private void AddClassStep()
	{
		String status = logger.getRunStatus().toString().toUpperCase();
		
		if(status.equalsIgnoreCase("FAIL"))
			status = "Failed";
		else if(status.equalsIgnoreCase("PASS"))
			status = "Passed";
		else if(status.equalsIgnoreCase("WARNING"))
			status = "Passed";
		else if("INFO|DONE".equalsIgnoreCase(status))
			status = "Passed";
		else if(status.equalsIgnoreCase("FATAL"))
			status = "Failed";
		else if(status.equalsIgnoreCase("ERROR"))
			status = "Failed";
		else if(status.equalsIgnoreCase("UNKNOWN"))
			status = "Failed";
		oParameters.SetParameters("TestStatus", status);
	}
}
