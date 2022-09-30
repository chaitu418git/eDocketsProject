package eDockets.cucumber.pages;

import org.openqa.selenium.WebDriver;

public class PageFactoryManager {
private static LoginPage loginPage;
private static CaseHomePage caseHomePage;
private static ClientPage clientPage;

public static LoginPage getLoginPage(WebDriver driver)
{
	//Initially loginPage object will be null and LoginPage object will be created and it 
	//will hold that value as we have static keyword. So next time when program will come here 
	//it will be request for page and this time it will not be null and it will return the loginPage object
	return loginPage == null ? new LoginPage(driver) : loginPage;//Ternary Operator(just like if-else stmnt)
	
}
public static CaseHomePage getCaseHomePage(WebDriver driver)
{
	return caseHomePage == null ? new CaseHomePage(driver) : caseHomePage;
}

public static ClientPage getClientPage(WebDriver driver)
{
	return clientPage == null ? new ClientPage(driver) : clientPage;
}
}
