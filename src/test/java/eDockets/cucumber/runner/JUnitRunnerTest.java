package eDockets.cucumber.runner;



import org.junit.runner.RunWith;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

@RunWith(Cucumber.class)
@CucumberOptions(
features = {".//Features/"},
        glue ={"eDockets.cucumber.stepdefinitions","eDockets.cucumber.hooks","eDockets.cucumber.customtype","eDockets.cucumber.domainobjects"},
        dryRun = false,
        monochrome = true,
        plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        tags = "@caseManagement"
)
public class JUnitRunnerTest {

}
