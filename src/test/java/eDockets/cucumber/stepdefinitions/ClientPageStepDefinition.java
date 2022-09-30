package eDockets.cucumber.stepdefinitions;

import eDockets.cucumber.context.TestContext;
//import eDockets.cucumber.logger.LoggerClass;
import eDockets.cucumber.pages.ClientPage;
import eDockets.cucumber.pages.PageFactoryManager;
import io.cucumber.java.en.*;


public class ClientPageStepDefinition {
	private ClientPage clientPage;
	private final TestContext context;
	//public LoggerClass logger;
	public ClientPageStepDefinition(TestContext context)
	{
		this.context =context;
		//this.logger=context.logger;
		clientPage=PageFactoryManager.getClientPage(context.driver);
	}

	@Given("user enters the client details")
	public void user_enters_the_client_details() {
	   clientPage.enterClientId().enterClientName().enterComments().clickOnStatusDropdownAndSelectActive();
	}
	@When("user clicks on Save Button")
	public void user_clicks_on_save_button() {
	    clientPage.clickOnSaveButton();
	}
	@Then("user able to view {string} message in popup")
	public void user_able_to_view_message_in_popup(String successmessage) {
	    clientPage.verifySuccessMessage(successmessage);
	}
	@Then("user clicks on Ok Button")
	public void user_clicks_on_ok_button() {
	   clientPage.clickOnOkButton();
	}
	@Then("verify the client name which was created")
	public void verify_the_client_name_which_was_created() throws InterruptedException {
		
	    clientPage.verifyClientId().verifyClientName();
	}

}
