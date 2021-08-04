package StepDefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class LoginApplication {

	//----creating reference for Webdriver
	WebDriver driver;
			
	@Given("^user login to the \"([^\"]*)\" application in \"([^\"]*)\" browser$")
	public void user_login_to_the_application(String url, String browser) throws Throwable {
		
		url = "https://opensource-demo.orangehrmlive.com";
		
		//---Checking browser provided in cucumber step to launch the application---
		
		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "lib/driver/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "path for driver exe");
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("InternetExplorer")) {
			System.setProperty("webdriver.ie.driver", "path for driver exe");
			driver = new InternetExplorerDriver();
		} else {
			System.out.print("Invalid Browser Name provided");
		}
		//--launching url---
		
		driver.get(url);
	}
	
	//-----Step Definition for entering credentials to login into the application
	
	@When("^user enters \"([^\"]*)\" into username element$")
	public void user_enters_into_username_element(String value) throws Throwable {
		WebElement userName= driver.findElement(By.id("txtUsername"));
		WebElement password= driver.findElement(By.id("txtPassword"));
	    File file=new File("src/test/java/resources/Credentials.properties");
	    FileInputStream fis=new FileInputStream(file);
	    Properties prop = new Properties();
	    prop.load(fis);
	    
	    if(value.equalsIgnoreCase("userName")){
	    	userName.sendKeys(prop.getProperty(value));
	    }else {
	    	password.sendKeys(prop.getProperty(value));
	    }
	}
	
	//----Click on submit button----
	@And("^user clicks on submit button$")
	public void submitButton() {
		WebElement submitBtn= driver.findElement(By.id("btnLogin"));
		submitBtn.click();
	}
	
	//----Validating the test on home page for successful login---
	@When("^user validate \"([^\"]*)\" test is visible on Homepage$")
	public void user_validate_test_is_visible_on_Homepage(String ActualVal) throws Throwable {
		
		WebElement logo=driver.findElement(By.xpath("//img[@alt='OrangeHRM']"));
		
		//Applying wait on the logo---
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@alt='OrangeHRM']")));		
		String ExpectedVal=logo.getAttribute("alt");
		Assert.assertEquals(ActualVal, ExpectedVal);
	}
	
	//----Closing the browser---
	@And("^User closes the browser$")
	public void closeBrowser() {
		driver.close();
	}
}
