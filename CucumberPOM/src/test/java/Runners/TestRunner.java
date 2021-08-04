package Runners;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions( features ="src/test/java/Features/AddNewUser.feature",
glue = {"StepDefinitions"},
tags = {"@NewUser"},
format = {
	"html: target/cucumber-reports/cucumber-pretty"},
plugin = "json:target/cucumber-report/CucumberTestReport.json")

public class TestRunner {
	private  TestNGCucumberRunner runner;
	
	@BeforeClass(alwaysRun=true)
	public void setUpClass() throws Exception{
		runner= new TestNGCucumberRunner(this.getClass());
	}
	@Test(groups = "WebLink" , description = "Weblink Galaxy Test" , dataProvider = "features")
	public void feature(CucumberFeatureWrapper wrap) {
		runner.runCucumber(wrap.getCucumberFeature());
	}
	@DataProvider(parallel = true)
	public Object[][] features(){
		return runner.provideFeatures();
	}
	@AfterClass()
	public void tearDown() {
		runner.finish();
	}
}
