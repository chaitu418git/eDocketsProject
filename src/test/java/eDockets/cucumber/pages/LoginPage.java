package eDockets.cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import eDockets.cucumber.domainobjects.LoginDetails;

public class LoginPage extends BasePage{
	String loginPageTitle="Login";

	public LoginPage(WebDriver driver) {
		super(driver);
	}
By txt_username=By.name("Username");
By txt_password=By.name("Password");
By btn_login=By.name("button");



public LoginPage enterUserName(String username)
{
	sendKeysOnWebElement(txt_username, username);
	logger.info("Entered the username");
	return this;
}
public LoginPage enterPassword(String password)
{
	sendKeysOnWebElement(txt_password, password);
	logger.info("Entered the password");
	return this;
}
public void clickOnLoginButton()
{
	clickOnWebElement(btn_login);
	logger.info("Clicked on Login Button");
	
}
public LoginPage setLoginDetails(LoginDetails loginDetails)
{
	return enterUserName(loginDetails.getUsername())
			.enterPassword(loginDetails.getPassword());
	
}
public String getTitle()
{
	return getPageTitle();
	
	
}
public void verifyPageTitle()
{
	verifyTitle(loginPageTitle);
}
}
