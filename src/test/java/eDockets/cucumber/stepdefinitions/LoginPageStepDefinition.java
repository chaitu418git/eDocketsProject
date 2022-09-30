package eDockets.cucumber.stepdefinitions;
import eDockets.cucumber.context.TestContext;
import eDockets.cucumber.domainobjects.LoginDetails;
//import eDockets.cucumber.logger.LoggerClass;
import eDockets.cucumber.owner.OwnerClass;
import eDockets.cucumber.pages.LoginPage;
import eDockets.cucumber.pages.PageFactoryManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class LoginPageStepDefinition {
	private LoginPage loginPage;
	private final TestContext context;
	//public LoggerClass logger;
	public LoginPageStepDefinition(TestContext context)
	{
		this.context =context;
		//this.logger=context.logger;
		loginPage=PageFactoryManager.getLoginPage(context.driver);
	}
	@Given("user is on Edockets Login Page")
	public void user_is_on_edockets_login_page() {
		//logger().info("Launching browser");
	    loginPage.load(OwnerClass.configLoader().StageUrl());
	    //logger.info("Browser was launched");
	}

	@And("login details are")
	public void login_details_are(LoginDetails loginDetails) {
		context.loginDetails=loginDetails;
	}

	@When("user enters the login details")
	public void user_enters_the_login_details() {
		//logger.info("Entering the Login Details");
	    loginPage.setLoginDetails(context.loginDetails);
	    //logger.info("Login Details were entered");
	}

	@When("clicks on Login Button")
	public void clicks_on_login_button() {
	   loginPage.clickOnLoginButton();
	   //logger.info("Clicked on Login Button");
	}
	
	@Then("user should be navigated to Login Page")
	public void user_should_be_navigated_to_login_page()
	{
		loginPage.verifyPageTitle();
		
		//logger.info("Return the Page Title");
	}


}
