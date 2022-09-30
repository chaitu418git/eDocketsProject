package eDockets.cucumber.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class CaseHomePage extends BasePage{
String welcomeEdocketsText="Welcome to eDockets.";
	public CaseHomePage(WebDriver driver) {
		super(driver);
	}
By ele_WelcomeToEdockets=By.xpath("//span[@id='headerMessage']");
By btn_userProfile=By.xpath("//button[@class='mat-focus-indicator mat-menu-trigger user-button mat-button mat-button-base']/span/diving/span");
By btn_Logout=By.xpath("//button[contains(@class,'mat-focus-indicator mat-menu-item')]/span[text()='Logout']");
By btn_Create = By.xpath("//div[text()='Create']");
By btn_Client = By.xpath("//button/span[text()='Client']");

public void verifyText() {
	//getTextOfWebElement(ele_WelcomeToEdockets);
	verifyText(welcomeEdocketsText, ele_WelcomeToEdockets);
	//Assert.assertEquals(getTextOfWebElement(ele_WelcomeToEdockets), welcomeEdocketsText);
	logger.info("Verification of Welcome to eDockets text is done");
}
public CaseHomePage clickOnUserProfile() {
	clickOnWebElement(btn_userProfile);
	logger.info("Clicked on User Profile");
	return this;
}
public CaseHomePage clickonLogoutButton() {
	clickOnWebElement(btn_Logout);
	logger.info("clicked on Logout Button");
	return this;
}
public void clickOnCreateButton()
{
	clickWithJS(btn_Create);
//	clickOnWebElement(btn_Create);
	logger.info("Clicked on Create Button");
}
public void clickOnClientButton()
{
	clickWithJS(btn_Client);
	logger.info("Clicked on Client Button");
}
}
