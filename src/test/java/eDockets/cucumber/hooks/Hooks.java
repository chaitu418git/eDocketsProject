package eDockets.cucumber.hooks;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import eDockets.cucumber.context.TestContext;
import eDockets.cucumber.factory.*;
import eDockets.cucumber.owner.OwnerClass;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentReporter;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Hooks {
	private WebDriver driver;
	private final TestContext context;

	public Hooks(TestContext context)
	{
		this.context=context;
	}
	@Before()
	public void before(Scenario scenario)
	{
		//System.out.println("BEFORE: THREAD ID : "+Thread.currentThread().getId()+","+"SCENARIO NAME: "+scenario.getName());
	driver=DriverFactory.initializeDriver(OwnerClass.configLoader().browser());
	context.driver=driver;
	scenario.log("browser name :"+OwnerClass.configLoader().browser());
	}
	//Base64
		public String getScreenshotPath(byte[] screenshotBytes)
		{
			String screenShotPath = "data:image/png;base64,"+Base64.getEncoder().encodeToString(screenshotBytes);
			return screenShotPath;
		}
		
		public byte[] getByteScreenshot() throws IOException 
		{
		    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		    byte[] fileContent = FileUtils.readFileToByteArray(src);
		    return fileContent;
		}
		
		public byte[] screenShotByte()
		{
			return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		}
		public String getBase64Screenshot()
		{
		    return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		}
		
		@After
		public void after(Scenario scenario) throws IOException
		{
		System.out.println(scenario.getStatus());
		//	System.out.println("AFTER: THREAD ID : "+Thread.currentThread().getId()+","+"SCENARIO NAME: "+scenario.getName());
			if(scenario.isFailed())
			{
				scenario.log("Scenario is failed");
				ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64Screenshot()).build());
				/*
				 * File destination = new File(System.getProperty("user.dir") +
				 * "/test-output/screenshots/+"+"*.png"+""); File scrFile = ((TakesScreenshot)
				 * driver).getScreenshotAs(OutputType.FILE); FileUtils.copyFile(scrFile,
				 * destination.getAbsoluteFile());
				 * ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(System.getProperty(
				 * "user.dir") + "/test-output/screenshots/+"+"*.png"+"");
				 */
				//Take a Screenshot
			////	byte[] screenshot= ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
				//embed it in the report
				//scenario.attach(screenShotByte(), "image/png", ""+scenario.getName()+"");
			////	scenario.attach(screenShotByte(), "image/png", scenario.getName());
				//scenario.at
				//scenario.attach(getScreenshotPath(screenshot), "image/png", ""+scenario.getName()+"");
			}
			scenario.log("closing the browser");
			//ExtentCucumberAdapter.addTestStepLog("this is my log");
		driver.quit();
		scenario.log("browser is closed");
		ExtentCucumberAdapter.getCurrentScenario().assignAuthor(System.getProperty("user.name"));
		//ExtentCucumberAdapter.getCurrentScenario().
		}
	@AfterAll
	public static void addSystemInfo()
	{
		//ExtentCucumberAdapter.addTestStepLog("this is my log");
		ExtentCucumberAdapter.addTestStepLog(System.getProperty("os.name"));
		
	}
}
