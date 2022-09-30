package eDockets.cucumber.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
public WebDriver driver;
public WebDriverWait wait;
public static Logger logger;

public BasePage(WebDriver driver)
{
	this.driver=driver;
	wait=new WebDriverWait(driver, Duration.ofSeconds(30));
	//As we are using Pagefactory to define the ui elements, we need to initialize those ui elements, so for that below stmnt
	PageFactory.initElements(driver, this);//Here this represents the current class
	logger=Logger.getLogger("eDockets Application");
	PropertyConfigurator.configure(System.getProperty("user.dir")+"\\src\\test\\resources\\log4j.properties");
}
public void load(String url)
{
	driver.get(url);
	
}
//Generates Random String
public String randomString(int lengthOfRandomString)
{
	String generatedString=RandomStringUtils.randomAlphabetic(lengthOfRandomString);
	return generatedString;
}
//Generates Random Number
public int randomNumber(int lengthOfRandomNumber)
{
	String generatedNumber=RandomStringUtils.randomNumeric(lengthOfRandomNumber);
	return Integer.parseInt(generatedNumber);
}
//Generates Random Alpha Numeric
public String randomAlphaNumber(int lengthOfRandomAlphaNumeric)
{
	String generatedAlphaNumber=RandomStringUtils.randomNumeric(lengthOfRandomAlphaNumeric);
	return generatedAlphaNumber;
}
//Generates Random Email
public String randomEmailGen()
{
	String email=RandomStringUtils.randomAlphabetic(9)+"gmail.com";
	return email;
}
//waitInSeconds
protected void waitInSeconds(int milliSeconds)
{
	try {
		Thread.sleep(milliSeconds);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	catch (Exception e) {
      e.printStackTrace();
	}
}

//Get Current Time Stamp
protected String getCurrentDateInHHMMSSFormat()
{
	DateFormat DF=DateFormat.getDateTimeInstance();
	Date date=new Date();
	String DateValue=DF.format(date);
	return DateValue;
}
//Get CurrentDateInDDMMYYYY
protected String getCurrentDateInDDMMYYYYFormat()
{
	Date date=new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	String strDate= formatter.format(date);
	return strDate;
}
//Get CurrentDateInMMDDYYYY
protected String getCurrentDateInMMDDYYYYFormat()
{
	Date date=new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
	String strDate= formatter.format(date);
	return strDate;
}
//Press the Enter Key
protected void pressEnterKey(By locator) {
	try {

		// EntryPoint.driver.switchTo().activeElement();
		Actions act = new Actions(driver);
		act.sendKeys(driver.findElement(locator), Keys.ENTER).build().perform();
		

	} catch (Exception e) {
		
		e.printStackTrace();
	}
}
//Verify Element is Present in Web Page
private boolean verifyElementPresentt(By locator)
{
	try {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		if (driver.findElement(locator).isDisplayed()) {
			return true;
		}
	} 
	catch (NoSuchElementException noElement) 
	   {
		System.out.println(noElement.getMessage());
		noElement.printStackTrace();
	   }
	catch (Exception e) {
		e.printStackTrace();
	}
	return false;
}
//Verify Text
protected void verifyText(String expectedText,By locator)
{
	String actualValue="";
	try {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		if(driver.findElement(locator).getTagName().equalsIgnoreCase("input") 
				|| driver.findElement(locator).getTagName().equalsIgnoreCase("textarea"))
		{
			actualValue = driver.findElement(locator).getAttribute("value").trim();
		}
		else {
			actualValue = driver.findElement(locator).getText();
		}
		if (actualValue.trim().equals(expectedText)) {
			System.out.println("Expected Text :"+expectedText);
		}
		else
		{
			System.out.println("Expected Text is not present");
		}
		
		//Assert.assertEquals(getTextOfWebElement(locator), expected);
		 //driver.findElement(locator).getText();
	} catch (NoSuchElementException noElement) 
	   {
		System.out.println(noElement.getMessage());
		noElement.printStackTrace();
	   }
	catch (Exception e) {
		e.printStackTrace();
	}
}
//verify Text Contains
protected void verifyTextContains(String expectedText,By locator)
{
	String actualValue="";
	try {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		//String actualValue="";
		if(driver.findElement(locator).getTagName().equalsIgnoreCase("input") 
				|| driver.findElement(locator).getTagName().equalsIgnoreCase("textarea"))
		{

			actualValue = driver.findElement(locator).getAttribute("value").trim();
		} else {
			actualValue = driver.findElement(locator).getText();
		}
		logger.info("Actual Value :"+actualValue);
		if (expectedText.contains(actualValue)
				|| actualValue.contains(expectedText)) {
			System.out.println("Expected Text is present");
								
		} else {
			System.out.println("Expected Text is not present");
		}
		//Assert.assertEquals(getTextOfWebElement(locator), expected);
		 //driver.findElement(locator).getText();
	} catch (NoSuchElementException noElement) 
	   {
		System.out.println(noElement.getMessage());
		noElement.printStackTrace();
	   }
	catch (Exception e) {
		e.printStackTrace();
	}
}
//Get text
protected String getTextOfWebElement(By locator)
{
	//String value = "";
	try {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		if (driver.findElement(locator).getTagName().equalsIgnoreCase("input")
				|| driver.findElement(locator).getTagName().equalsIgnoreCase("textarea")) {
			return driver.findElement(locator).getAttribute("value").trim();
		} else {
			return driver.findElement(locator).getText();
		}
	}
	 catch (NoSuchElementException noElement) 
	   {
		System.out.println(noElement.getMessage());
		noElement.printStackTrace();
	   }
	catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}
//select the dropdown using "select by visible text" ->This will work only for Select tag in HTML
protected void selectByVisibleText(By selectlocator, String VisibleText){
	try {
		wait.until(ExpectedConditions.presenceOfElementLocated(selectlocator));
		Select selObj=new Select(driver.findElement(selectlocator));
		selObj.selectByVisibleText(VisibleText);
	    } 
	catch (NoSuchElementException noElement) 
	   {
		logger.info(noElement.getMessage());
		noElement.printStackTrace();
	   }
	catch (ElementNotSelectableException e) {
		e.printStackTrace();
	}
	catch (TimeoutException e) {
		logger.info("WebDriver couldn’t locate the element");
		}
	catch (WebDriverException e) {
		e.printStackTrace();
		}
		
	catch (Exception e) 
	   {
		e.printStackTrace();
	   }

}

//select the dropdown using "select by index" -> This will work only for Select tag in HTML
protected void selectByIndex(By selectlocator, int IndexValue){
	try {
		wait.until(ExpectedConditions.presenceOfElementLocated(selectlocator));
		Select selObj=new Select(driver.findElement(selectlocator));
		selObj.selectByIndex(IndexValue);
	} catch (NoSuchElementException noElement) {
		System.out.println(noElement.getMessage());
		noElement.printStackTrace();
	}
	catch (ElementNotSelectableException e) {
		e.printStackTrace();
	}
	catch (TimeoutException e) {
		System.out.println("WebDriver couldn’t locate the element");
		}
	catch (WebDriverException e) {
		e.printStackTrace();
		}
	catch (Exception e) {
		e.printStackTrace();
	}

}

//select the dropdown using "select by value", so pass Value as ‘thirdcolor’
protected void selectByValue(By selectlocator, String Value){
	try {
		wait.until(ExpectedConditions.presenceOfElementLocated(selectlocator));
		Select selObj=new Select(driver.findElement(selectlocator));
		selObj.selectByValue(Value);
	} catch (NoSuchElementException noElement) {
		System.out.println(noElement.getMessage());
		noElement.printStackTrace();
	}
	catch (ElementNotSelectableException e) {
		e.printStackTrace();
	}
	catch (TimeoutException e) {
		System.out.println("WebDriver couldn’t locate the element");
		}
	catch (WebDriverException e) {
		e.printStackTrace();
		}
	catch (Exception e) {
		e.printStackTrace();
	}

}

//Click Operation
protected void clickOnWebElement(By elementTobeClicked)
{
	try {
	//driver.manage().window().maximize();
		WebElement ele=wait.until(ExpectedConditions.elementToBeClickable(elementTobeClicked));
		ele.click();
	} catch (NoSuchElementException noElement) {
		System.out.println("Element " + elementTobeClicked + " was not found in DOM "+ noElement.getStackTrace());
		System.out.println(noElement.getMessage());
		noElement.printStackTrace();
	}
	catch (ElementNotVisibleException e) {
		e.printStackTrace();
	}
	catch (ElementClickInterceptedException e) {
		e.printStackTrace();
	}
	catch (ElementNotInteractableException e) {
		e.printStackTrace();
	}
	catch (TimeoutException e) {
		System.out.println("WebDriver couldn’t locate the element");
		}
	catch (WebDriverException e) {
		e.printStackTrace();
		}
	catch (Exception e) {
		e.printStackTrace();
	}
	
	
}
//Click By Using Java Script
private void clickByJs(By elementTobeClicked)
{
	try {
		JavascriptExecutor executor=(JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", driver.findElement(elementTobeClicked));
			} 
	catch (NoSuchElementException noElement) {
		System.out.println("Element " + elementTobeClicked + " was not found in DOM "+ noElement.getStackTrace());
		System.out.println(noElement.getMessage());
		noElement.printStackTrace();
	}
	catch (ElementNotVisibleException e) {
		e.printStackTrace();
	}
	catch (ElementClickInterceptedException e) {
		e.printStackTrace();
	}
	catch (ElementNotInteractableException e) {
		e.printStackTrace();
	}
	catch (TimeoutException e) {
		System.out.println("WebDriver couldn’t locate the element");
		}
	catch (WebDriverException e) {
		e.printStackTrace();
		}
	catch (Exception e) {
		e.printStackTrace();
	}
}

public void clickWithJS(By locator) {
	try {
		WaitForPageLoad();
		((JavascriptExecutor)driver)
				.executeScript("var evt = document.createEvent('MouseEvents');"
						+ "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
						+ "arguments[0].dispatchEvent(evt);", driver.findElement(locator));
		
	} catch (TimeoutException e) {
		System.out.println("WebDriver couldn’t locate the element");
		}
	catch (WebDriverException e) {
		e.printStackTrace();
		}
	catch (Exception e) {
		e.printStackTrace();
	}

	}
private void WaitForPageLoad() {
	// Start Time
	Date stDate = new Date();
	System.out.println("Page is Loading...");
	waitUntilJSReady();
	ajaxComplete();
	waitUntilJQueryReady();
	waitUntilAngularReady();
	waitUntilAngular5Ready();
//	waitUntilAlnSpinnerLoaded();
	System.out.println("Page load is completed.");
	// Cal page load time
	Date enDate = new Date();
	long duration = enDate.getTime() - stDate.getTime();
	long diffInSeconds = duration / 1000 % 60;
	long diffInMinutes = duration / (60 * 1000) % 60;
	long diffInHours = duration / (60 * 60 * 1000) % 24;
	long diffInDays = duration / (24 * 60 * 60 * 1000);
	System.out.println("Time taken to load:" + diffInDays + " days, " + diffInHours + " hours, " + diffInMinutes
			+ " minutes, " + diffInSeconds + " seconds.");
}

private void waitUntilJSReady() {
	try {
		String pageLoadState = ((JavascriptExecutor)driver).executeScript(
				"if (document != undefined && document.readyState) { return document.readyState;} else { return undefined;}")
				.toString();
		while (true) {
			if (pageLoadState.toUpperCase().equals("COMPLETE") || pageLoadState.toUpperCase().equals("LOADED")) {
				break;
			}
			pageLoadState = ((JavascriptExecutor)driver).executeScript(
					"if (document != undefined && document.readyState) { return document.readyState;} else { return undefined;}")
					.toString();
		}

	} catch (Exception e) {
	}
}
private void ajaxComplete() {
	JavascriptExecutor jsExec = (JavascriptExecutor)driver;
	jsExec.executeScript("var callback = arguments[arguments.length - 1];" + "var xhr = new XMLHttpRequest();"
			+ "xhr.open('GET', '/Ajax_call', true);" + "xhr.onreadystatechange = function() {"
			+ "  if (xhr.readyState == 4) {" + "    callback(xhr.responseText);" + "  }" + "};" + "xhr.send();");
}
private void waitUntilJQueryReady() {
	JavascriptExecutor jsExec = (JavascriptExecutor)driver;
	Boolean jQueryDefined = (Boolean) jsExec.executeScript("return typeof jQuery != 'undefined'");
	if (jQueryDefined) {
		waitForJQueryLoad();

	}
}
private void waitForJQueryLoad() {
	JavascriptExecutor jsExec = (JavascriptExecutor)driver;
	wait=new WebDriverWait(driver, Duration.ofSeconds(30));
	//WebDriverWait jsWait = new WebDriverWait(this.driver.getDriver(), 60);
	try {
		ExpectedCondition<Boolean> jQueryLoad = driver -> ((Long) ((JavascriptExecutor)this.driver)
				.executeScript("return jQuery.active") == 0);

		boolean jqueryReady = (Boolean) jsExec.executeScript("return jQuery.active==0");

		if (!jqueryReady) {
			wait.until(jQueryLoad);
		}
	} catch (WebDriverException ignored) {
	}
}
private void waitUntilAngularReady() {
	JavascriptExecutor jsExec = (JavascriptExecutor)driver;
	try {
		Boolean angularUnDefined = (Boolean) jsExec.executeScript("return window.angular === undefined");
		if (!angularUnDefined) {
			Boolean angularInjectorUnDefined = (Boolean) jsExec
					.executeScript("return angular.element(document).injector() === undefined");
			if (!angularInjectorUnDefined) {

				waitForAngularLoad();

			}
		}
	} catch (WebDriverException ignored) {
	}
}
private void waitForAngularLoad() {
	//String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";
	String angularReadyScript = "return angular.element(document).injector() === undefined";
	angularLoads(angularReadyScript);
}
private void angularLoads(String angularReadyScript) {
	wait=new WebDriverWait(driver, Duration.ofSeconds(30));
	//WebDriverWait jsWait = new WebDriverWait(this.driver.getDriver(), 30);
	JavascriptExecutor jsExec = (JavascriptExecutor) driver;
	try {
		ExpectedCondition<Boolean> angularLoad = driver -> Boolean
				.valueOf(((JavascriptExecutor) driver).executeScript(angularReadyScript).toString());

		boolean angularReady = Boolean.valueOf(jsExec.executeScript(angularReadyScript).toString());

		if (!angularReady) {
			wait.until(angularLoad);
		}
	} catch (WebDriverException ignored) {
	}
}

private void waitUntilAngular5Ready() {
	JavascriptExecutor jsExec = (JavascriptExecutor) driver;
	try {
		Object angular5Check = jsExec
				.executeScript("return getAllAngularRootElements()[0].attributes['ng-version']");
		if (angular5Check != null) {
			Boolean angularPageLoaded = (Boolean) jsExec
					.executeScript("return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1");
			if (!angularPageLoaded) {

				waitForAngular5Load();

			}
		}
	} catch (WebDriverException ignored) {
	}
}
private void waitForAngular5Load() {
	String angularReadyScript = "return window.getAngularTestability(window.getAllAngularRootElements()[0])._ngZone.isStable;";
	angularLoads(angularReadyScript);
}

//SendKeys Method
protected void sendKeysOnWebElement(By locator, String textToBeEntered)
{
	try {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		driver.findElement(locator).click();
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(textToBeEntered);
	} catch (NoSuchElementException noElement) {
		System.out.println("Element " + locator + " was not found in DOM "+ noElement.getStackTrace());
		System.out.println(noElement.getMessage());
		noElement.printStackTrace();
	}
	catch (TimeoutException e) {
		System.out.println("WebDriver couldn’t locate the element");
		}
	catch (WebDriverException e) {
		e.printStackTrace();
		}
	catch (Exception e) {
		e.printStackTrace();
	}
	
}
protected void verifyAlertText(By locator, String expectedAlertText) {
	try {
		try {
			wait.until(ExpectedConditions.alertIsPresent());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Alert a = driver.switchTo().alert();
		String actual = a.getText();
		String expected = expectedAlertText;
		if (actual.equalsIgnoreCase(expected)) {
			logger.info("Actual Text is:" +actual+ "And Expected Text is:" + expected + "Both texts are equal");
		} else {
			logger.info("Actual Text is:" +actual+ "And Expected Text is:" + expected + "Both texts are not equal");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}
//Accept Alert
protected void acceptAlert(WebDriver driver)
{
	try {
		wait.until(ExpectedConditions.alertIsPresent());
driver.switchTo().alert().accept();	
}
	catch (NoAlertPresentException noAlert) {
		System.out.println("No Alert present " +noAlert.getStackTrace());
		noAlert.printStackTrace();
	}
	catch (TimeoutException e) {
	System.out.println("WebDriver couldn’t locate the Alert");
	}
	catch (Exception e) {
		e.printStackTrace();
	}
}
//Dismiss Alert
protected void dismissAlert(WebDriver driver)
{
	try {
	wait.until(ExpectedConditions.alertIsPresent());
	driver.switchTo().alert().dismiss();
}
	catch (NoAlertPresentException noAlert) {
		noAlert.printStackTrace();
	}
	catch (Exception e) {
		e.printStackTrace();
	}
}
//MouseOver Method->It will mouse hover on element
protected void mouseHover(By locator){
	try {
		Actions actObj=new Actions(driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		actObj.moveToElement(driver.findElement(locator)).build().perform();
		logger.info("Successfully mouse hovered");
	} catch (NoSuchElementException noElement) {
		System.out.println("Element " + locator + " was not found in DOM "+ noElement.getStackTrace());
		System.out.println(noElement.getMessage());
		noElement.printStackTrace();
			}
	catch (TimeoutException e) {
		System.out.println("WebDriver couldn’t locate the element");
		}
	catch (WebDriverException e) {
		e.printStackTrace();
		}
	catch (Exception e) {
		e.printStackTrace();
	}
	

}

//DoubleClick
protected void doubleClick(By locator){
	try {
		Actions actObj=new Actions(driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		actObj.moveToElement(driver.findElement(locator)).doubleClick(driver.findElement(locator)).build().perform();
	} catch (NoSuchElementException noElement) {
		System.out.println("Element " + locator + " was not found in DOM "+ noElement.getStackTrace());
		System.out.println(noElement.getMessage());
		noElement.printStackTrace();
	}
	catch (TimeoutException e) {
		System.out.println("WebDriver couldn’t locate the element");
		}
	catch (WebDriverException e) {
		e.printStackTrace();
		}
	catch (Exception e) {
		e.printStackTrace();
	}

}
//DoubleClickWithJS
protected void doubleClickWithJs(By locator)
{
	try {
		Actions actObj=new Actions(driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(locator));
		actObj.moveToElement(driver.findElement(locator)).doubleClick(driver.findElement(locator)).build().perform();
	} catch (NoSuchElementException noElement) {
		System.out.println("Element " + locator + " was not found in DOM "+ noElement.getStackTrace());
		System.out.println(noElement.getMessage());
		noElement.printStackTrace();
	}
	catch (TimeoutException e) {
		System.out.println("WebDriver couldn’t locate the element");
		}
	catch (WebDriverException e) {
		e.printStackTrace();
		}
	catch (Exception e) {
		e.printStackTrace();
	}

}
//DoubleClickWithJs
public void DoubleClickWithJs(By locator) {
	try {
		//this.browser.WaitForPageLoad();
		((JavascriptExecutor) driver)
				.executeScript("var evt = document.createEvent('MouseEvents');"
						+ "evt.initMouseEvent('dblclick',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
						+ "arguments[0].dispatchEvent(evt);", driver.findElement(locator));
//		Actions actions = new Actions(this.driver.getDriver());
//		actions.moveToElement(this.driver.findWebElement(step)).doubleClick().build().perform();
		//step.log("PASSED", "PASSED", "Successfully clicked.", testCase, this.driver);
	} catch (TimeoutException e) {
		System.out.println("WebDriver couldn’t locate the element");
		}
	catch (WebDriverException e) {
		e.printStackTrace();
		}
	catch (Exception e) {
		e.printStackTrace();
	}
	}

//DragAndDrop
protected void dragAndDrop(By sourceElement, By destinationElement)
{
	try {
		Actions actObj=new Actions(driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(sourceElement));
		wait.until(ExpectedConditions.presenceOfElementLocated(destinationElement));
		actObj.dragAndDrop(driver.findElement(sourceElement), driver.findElement(destinationElement)).build().perform();
		
	} 
	catch (StaleElementReferenceException e) {
		System.out.println("Element with " + sourceElement + "or" + destinationElement + "is not attached to the page document "
				+ e.getStackTrace());
	}
	catch (NoSuchElementException e) {
		System.out.println("Element " + sourceElement + "or" + destinationElement + " was not found in DOM "+ e.getStackTrace());
	} 
	catch (TimeoutException e) {
		System.out.println("WebDriver couldn’t locate the element");
		}
	catch (WebDriverException e) {
		e.printStackTrace();
		}
	catch (Exception e) {
		System.out.println("Error occurred while performing drag and drop operation "+ e.getStackTrace());
		e.printStackTrace();
	}
}
//DragAndDropWithClickAndHold
protected void dragAndDropByClickAndHold(By sourceElement, By destinationElement)
{
	try {
		Actions actObj=new Actions(driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(sourceElement));
		wait.until(ExpectedConditions.presenceOfElementLocated(destinationElement));
		actObj.clickAndHold(driver.findElement(sourceElement))
		.moveToElement(driver.findElement(destinationElement))
		.release(driver.findElement(destinationElement))
		.build()
		.perform();
		
		
	} 
	catch (StaleElementReferenceException e) {
		System.out.println("Element with " + sourceElement + "or" + destinationElement + "is not attached to the page document "
				+ e.getStackTrace());
	}
	catch (NoSuchElementException e) {
		System.out.println("Element " + sourceElement + "or" + destinationElement + " was not found in DOM "+ e.getStackTrace());
	} 
	catch (TimeoutException e) {
		System.out.println("WebDriver couldn’t locate the element");
		}
	catch (WebDriverException e) {
		e.printStackTrace();
		}
	catch (Exception e) {
		System.out.println("Error occurred while performing drag and drop operation "+ e.getStackTrace());
		e.printStackTrace();
	}
}
//SwithtoFrameWithWebElement
protected void switchToFrameByWebElement(By frameWebElement)
{
	try {
		
		wait.until(ExpectedConditions.presenceOfElementLocated(frameWebElement));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameWebElement));
driver.switchTo().frame(driver.findElement(frameWebElement));		
	} catch (NoSuchFrameException e) {
		System.out.println("Unable to locate frame with element " + frameWebElement + e.getStackTrace());
	}
	catch (StaleElementReferenceException e) {
		System.out.println("Element with " + frameWebElement + "is not attached to the page document" + e.getStackTrace());
	}
	catch (TimeoutException e) {
		System.out.println("WebDriver couldn’t locate the element");
		}
	catch (WebDriverException e) {
		e.printStackTrace();
		}
	catch (Exception e) {
		System.out.println("Unable to navigate to frame with element " + frameWebElement + e.getStackTrace());
	}
}

//SiwtchToAParticluarWindow
private boolean switchToRightChildWindow(String windowTitle)
{
	try {
		Set<String> handles = driver.getWindowHandles();
		List<String> hList=new ArrayList<>(handles);
		 for(String e:hList)
		 {
			 String title=driver.switchTo().window(e).getTitle();
			 if(title.contains(windowTitle))
			 {
				 System.out.println("Found the right Window");
				 return true;
			 }
		 }
	}
	catch (NoSuchWindowException e) {
		e.printStackTrace();
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return false;
}
//SwitchToParentWindow
private void switchToParentWidnow(String parentId)
{
	try {
		driver.switchTo().window(parentId);
	} catch (NoSuchWindowException e) {
		e.printStackTrace();
	}
	catch (Exception e) {
		e.printStackTrace();
	}
}

private String getParentId()
{
return driver.getWindowHandle();
}
//Switch to a new window
protected void switchToNewWindow() {
	try {
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> ite = windows.iterator();
		String latestWindow = "";
		System.out.println("Total windows:" + windows.size());
		while (ite.hasNext()) {
			latestWindow = ite.next();
		}
		driver.switchTo().window(latestWindow);
	} catch (Exception e) {
		logger.info("Unable to switch to a new window");
		e.printStackTrace();
	}
}
//Switch to a Parent Window
protected void switchToParentWindow() {
	try {
		driver.switchTo().window(driver.getWindowHandles().iterator().next());
		
	} catch (Exception e) {
		logger.info("Unable to Switch to a Parent Window");
		e.printStackTrace();
	}
}
protected String getPageTitle()
{
	 try {
		 wait.until(ExpectedConditions.titleContains(driver.getTitle()));
		 return driver.getTitle();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}
//verify the Page Title
protected void verifyTitle(String expectedTitle)
{
	String actualTitle = driver.getTitle();
	 wait.until(ExpectedConditions.titleContains(driver.getTitle()));
	//String expectedText = testCase.getDataFromTestStorage(step.getTestData());
	if (actualTitle.equals(expectedTitle)) {
		logger.info("Actual Title is: " + actualTitle + "And Expected Title is: " + expectedTitle + "\\r\\n Both are equal.\"");
				
	}
	else
	{
		logger.info("Actual Title is: " + actualTitle + "And Expected Title is: " + expectedTitle + "\\r\\n Both are not equal.\"");
	}
}
protected void getCurrenturl()
{
	try {
		driver.getCurrentUrl();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
protected void refreshPage() {
	try {
		driver.navigate().refresh();
		logger.info("refreshed the page");
	} catch (Exception e) {
		e.printStackTrace();
	}
		}

//To clear the Text
protected void Textclear(By locator) throws InterruptedException {
	try {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		driver.findElement(locator).clear();
		
	} 
	catch (NoSuchElementException noElement) {
		System.out.println("Element " + locator + " was not found in DOM "+ noElement.getStackTrace());
		System.out.println(noElement.getMessage());
		noElement.printStackTrace();
	}
	catch (Exception e) {
		e.printStackTrace();
	}
}
//Text clear with JS
protected void TextclearWithJs(By locator) throws InterruptedException {
	try {
		
		((JavascriptExecutor)driver).executeScript("arguments[0].value='';",driver.findElement(locator));
		
	} catch (Exception e) {
     e.printStackTrace();
				}
	}
//Text Clear with Back Space
protected void TextclearWithBackSpace(By locator) throws InterruptedException {
	try {
		driver.findElement(locator).sendKeys(Keys.BACK_SPACE);
	} catch (Exception e) {
       e.printStackTrace();
			}
}
//Verify the Element Present
protected void verifyElementPresent(By locator) {
	try {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		driver.findElement(locator);
		logger.info("Element is Present");
			} catch (Exception e) {
				logger.info("Element is not present");
				e.printStackTrace();
		
	}
}

//Verify Element is Not Present
protected void verifyElementNotPresent(By locator) {
	try {
		if (isElementPresent(locator)) {
			logger.info("Element is Present");
		} else
			logger.info("Element is not present");
	} catch (Exception e) {
		e.printStackTrace();
		}
	}

private boolean isElementPresent(By locator) {
	try {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.findElement(locator);
	} catch (Exception e) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return false;
	}
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	return true;
}
//Switching to the default content
public void SwitchToDefaultContent() {
	try {
		driver.switchTo().defaultContent();
	} catch (Exception e) {
		logger.info("Unable to switch to default content");
		e.printStackTrace();
}

}
//This is for verifying the selected dropdown value 
protected void VerifyDropdownSelectedText(By locator, String expectedtext) {
	try {
		Select dropdown = new Select(driver.findElement(locator));
		WebElement webElement = dropdown.getFirstSelectedOption();
		String actualText = webElement.getText();
		String expectedText = expectedtext;
		if (actualText.equals(expectedText)) {
			logger.info("Actual Text of dropdown:" + actualText + "ExpectedText:" +expectedText+ "Both are equal");
			
		} 
	} catch (Exception e) {
		logger.info( "Actual Text and Expected Text Both are not equal");
		e.printStackTrace();
	}
}
//Verify Element Visibility
protected void verifyElementVisible(By locator) {
	try {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		if (driver.findElement(locator).isDisplayed()) {
			logger.info("Element is displayed");
		} 
	} catch (Exception ex) {
		logger.info("Element is not displayed");
		ex.printStackTrace();
	}
}
//Verify Element is not visisble
protected void verifyElementNotVisible(By locator) {
	try {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		if (!driver.findElement(locator).isDisplayed()) {
			logger.info("Element is not displayed");
	} }catch (Exception ex) {
		logger.info("Element is not displayed");
		ex.printStackTrace();
	}
}
//Verify element is checked
protected void verifyIsChecked(By locator) {
	try {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		if (driver.findElement(locator).isSelected()) {
			logger.info("CheckBox is Checked");
		}
	} catch (Exception e) {
		logger.info("CheckBox is not Checked");
		e.printStackTrace();
}
}
//Verify Element is not checked
protected void verifyIsNotChecked(By locator) {
	try {
		if (!driver.findElement(locator).isSelected()) {
			logger.info("CheckBox is not Checked");
		} 
	} catch (Exception e) {
		logger.info("CheckBox is checked");
		e.printStackTrace();
	}
}

protected void verifyIsDisabled(By locator) {
	try {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		if (!driver.findElement(locator).isEnabled()) {
			logger.info("Element is disabled");
		} else {
			logger.info("Element is enabled");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}

}

