package eDockets.cucumber.stepdefinitions;
import eDockets.cucumber.context.TestContext;
import eDockets.cucumber.pages.CaseHomePage;
import eDockets.cucumber.pages.PageFactoryManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CaseHomePageStepDefinition {
	private CaseHomePage caseHomePage;
	//public LoggerClass logger;
	public CaseHomePageStepDefinition(TestContext context)
	{
		//logger=Logger.getLogger("edockets Application");
		//this.logger=context.logger;
		caseHomePage=PageFactoryManager.getCaseHomePage(context.driver);
		
	}
	@Then("user should be able to login to Edockets Application")
	public void user_should_be_able_to_login_to_edockets_application() {
	   caseHomePage.verifyText();
	  // logger.info("Text Verification was done");
	}

	@Then("user clicks on Logout Button")
	public void user_clicks_on_logout_button() {
	    caseHomePage.clickOnUserProfile().clickonLogoutButton();
	    //logger.info("Clicked on Logout Button");
	}
	@Given("user clicks on Create Button")
	public void user_clicks_on_create_button() {
		caseHomePage.clickOnCreateButton();
	   
	}
	@And("user clicks on Client Button")
	public void user_clicks_on_client_button() {
	    caseHomePage.clickOnClientButton();
	}
}
