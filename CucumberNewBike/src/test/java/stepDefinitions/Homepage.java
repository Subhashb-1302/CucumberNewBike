package stepDefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import utilities.ExcelUtilities;

public class Homepage {
	
	WebDriver driver;
	HomePage hp;
	static Properties p;
	String fileName;
	static ExcelUtilities excelUtilities;
	
	
	
	@Given("automate homepage element")
	public void automate_homepage_element() {
	    BaseClass.getLogger().info("HomePage.feature file execution started");
	    hp=new HomePage(BaseClass.getDriver());
	}

	@When("hover on the Newbikes Dropdown")
	public void hover_on_the_newbikes_dropdown() {
		hp.newBikesAssert();
		hp.hoverOnNewBike();
		BaseClass.getLogger().info("Hovered on the NewBike Dropdown");
	}

	@When("click on the Upcoming Bikes")
	public void click_on_the_upcoming_bikes() {
		hp.upcomingBikesAssert();
	    hp.clickOnUpcomingBike();
	    BaseClass.getLogger().info("Clicked on the Upcoming bikes");
	}

	@When("select manufacturer as Honda")
	public void select_manufacturer_as_honda() throws IOException {
		hp.manufacturerAssert();
	    hp.selectManufacturer(BaseClass.getProperties().getProperty("manufacturer"));
	    BaseClass.getLogger().info("Honda is Selected as Manufacturer");
	   
	}

	@Then("clicked on view more")
	public void clicked_on_view_more() {
	    hp.viewMore();
	    BaseClass.getLogger().info("Clicked on View more button");
	}

	@Then("collect all the details of upcoming bikes")
	public void collect_all_the_details_of_upcoming_bikes() throws InterruptedException, IOException {
	   List<String[]>bikeDetails= hp.getDetailsOfBikes();
	    String sheetName ="BikeDetails";
	    excelUtilities = new ExcelUtilities();
		excelUtilities.deleteExcel(fileName);

		int i = 0, j = 0;
		for (String[] bike : bikeDetails) {
			j = 0;
			for (String data : bike) {
				excelUtilities.setCellData("Data", "BikeDetails", i, j++, data);
			}
			i++;
		}
	    BaseClass.getLogger().info("All the details of Upcoming bikes are collected");
	    
	    BaseClass.getLogger().info("Homepage.feature file execution Finished");
	}

}
