package eDockets.cucumber.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ClientPage extends BasePage{
	public String clientSuccessMessage = "Client added successfully.";

	public ClientPage(WebDriver driver) {
		super(driver);
		
	}
	By txt_clientId = By.xpath("//input[@formcontrolname='clientId']");
	By txt_clientName= By.xpath("//input[@formcontrolname='clientName']");
	By txt_comments = By.xpath("//input[@formcontrolname='notes']");
	By dropdown_status = By.xpath("(//mat-select[@formcontrolname='status'])[1]");
	By option_active= By.xpath("//mat-option/span[contains(text(),'Active')]");
	By btn_clientSaveButton= By.xpath("//button[contains(@class,'btnSaveClient')]");
	By msg_success_creation = By.xpath("//fuse-confirm-dialog/div[@class='mat-dialog-content']");
	By btn_okButton= By.xpath("//fuse-confirm-dialog//button[contains(@class,'btn-Confirm')]");
	By value_clientId=By.xpath("(//mat-panel-title)[1]/p");
	By value_clientName=By.xpath("(//mat-panel-title)[1]/p");
	By client_Header_Exapnd_Path=By.xpath("(//mat-expansion-panel-header[@aria-expanded='false'])[1]");
	 
	
	
	public ClientPage enterClientId()
	{
		clickOnWebElement(txt_clientId);
		logger.info("Clicked on ClientId field");
		sendKeysOnWebElement(txt_clientId,randomString(8));
		logger.info("Entered the ClientId");
		return this;
	}
	public ClientPage enterClientName()
	{
		clickOnWebElement(txt_clientName);
		logger.info("Clicked on ClientName field");
		sendKeysOnWebElement(txt_clientName, randomString(7));
		logger.info("Entered the Client Name");
		return this;
	}
	public ClientPage enterComments()
{
		clickOnWebElement(txt_comments);
		logger.info("Clicked on Comments textbox");
		sendKeysOnWebElement(txt_comments, randomString(7));
		logger.info("Entered the comments");
		return this;
	}
	public ClientPage clickOnStatusDropdownAndSelectActive()
	{
		waitInSeconds(2000);
		clickWithJS(dropdown_status);
		logger.info("Clicked on Status Dropdown");
		waitInSeconds(2000);
		clickWithJS(option_active);
		logger.info("Selected Active Option From Dropdown");
		return this;
	}
	public String storeClientId()
	{
		return getTextOfWebElement(txt_clientId);
	}
	public String storeClientName()
	{
		return getTextOfWebElement(txt_clientName);
	}
	public void clickOnSaveButton()
	{
		clickOnWebElement(btn_clientSaveButton);
		logger.info("Clicked on Save Button");
	}

	public void verifySuccessMessage(String successmessage)
	{
		Assert.assertEquals(getTextOfWebElement(msg_success_creation), successmessage);
		logger.info("Client Creation Success Message Verification is Done");
		
	}
	public void clickOnOkButton()
	{
		clickOnWebElement(btn_okButton);
		logger.info("Clicked on Ok Button");
		waitInSeconds(3000);
	}
	public ClientPage verifyClientId()
	{
		String clientId=storeClientId();
		System.out.println(clientId);
		logger.info("ExpectedClientId :"+clientId);
		//Assert.assertEquals(getTextOfWebElement(value_clientId), clientId);
		verifyTextContains(clientId, value_clientId);
		logger.info("ClientId Verification is Done");
		return this;
	}
	public ClientPage verifyClientName()
	{
		String clientName=storeClientName();
		//System.out.println(clientName);
		logger.info("ExpectedClientName :"+clientName);
	//	Assert.assertEquals(getTextOfWebElement(value_clientName), clientName);
		verifyTextContains(clientName, value_clientName);
		logger.info("ClientName Verification is Done");
		return this;
	}
}
