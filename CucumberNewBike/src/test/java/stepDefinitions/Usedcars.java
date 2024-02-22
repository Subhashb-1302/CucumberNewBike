package stepDefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.UsedCars;
import utilities.ExcelUtilities;

public class Usedcars {
	
	static WebDriver driver;
	UsedCars uc;
	static ExcelUtilities excelUtilities;
	
	@Given("Hover on the Usedcar dropdown")
	public void hover_on_the_usedcar_dropdown() {
	   uc=new UsedCars(BaseClass.getDriver());
	   BaseClass.getLogger().info("UsedCars.feature file execution Started");
	  // uc.scrollUp();
	   uc.usedCarsAssert();
	   uc.usedCars();
	   BaseClass.getLogger().info("Hovered on the Usedcars Dropdown");
	}

	@When("Click on the chennai location")
	public void click_on_the_chennai_location() {
	   uc.clickOnChennai();
	   BaseClass.getLogger().info("Successfuly clicked on the chennai location");
	   uc.brand();
	   BaseClass.getLogger().info("Scroll till popular brand list displays");
	}

	@Then("Collect all the brand available")
	public void collect_all_the_brand_available() throws IOException {
	    String[] brands=uc.brandPopular();
	    int i=0;
	    for(String brand:brands) {
	    	excelUtilities=new ExcelUtilities();
	    	excelUtilities.setCellData("Data", "PopularBrands", i++, 0, brand);
	    }
	    BaseClass.getLogger().info("Colleact all Popular brands available in UsedCars");
	}

	@Then("Click on Home button")
	public void click_on_home_button() throws InterruptedException {
	   uc.home();
	   BaseClass.getLogger().info("Clicked on the Home button to redirect HomePage");
	   
	   BaseClass.getLogger().info("UsedCars.feature file execution Finished");
	}
}
