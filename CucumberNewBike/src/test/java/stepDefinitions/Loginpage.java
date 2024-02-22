package stepDefinitions;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;

public class Loginpage {
	
	WebDriver driver;
	LoginPage lp;
	Properties p;
	
	@Given("navigate to home page")
	public void navigate_to_home_page() {
		BaseClass.getLogger().info("Login.feature file execution started");
		lp=new LoginPage(BaseClass.getDriver());
	   
	}

	@When("click on login\\/signUp")
	public void click_on_login_sign_up() {
	    lp.loginPage();
	    BaseClass.getLogger().info("Clicked on login Button");
	}

	@When("click on google")
	public void click_on_google() {
	    lp.clickOnGoogle();
	    BaseClass.getLogger().info("Then clicked on google button");
	}

	@When("navigate to new window")
	public void navigate_to_new_window() {
	    lp.navigationToLoginPage();
	    BaseClass.getLogger().info("Successfully switched to child window");
	}

	@Then("Enter the Email and click on next btn")
	public void Enter_the_Email_and_click_on_next_button() throws Exception{
	  lp.emailVerification(BaseClass.getProperties().getProperty("email")); 
	  BaseClass.getLogger().info("Error message displyed has been captured");
	  
	  BaseClass.getLogger().info("Login.feature file execution Finished");
	}

}
