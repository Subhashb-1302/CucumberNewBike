package stepDefinitions;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;

public class Hooks {
	WebDriver driver;
	Properties p;
	
	@Before
	public void setUp() throws IOException {
		driver =BaseClass.initializeBrowser();
		p=BaseClass.getProperties();
		driver.get(p.getProperty("appUrl"));
		driver.manage().window().maximize();
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
	@AfterStep
	public void addScreenShot(Scenario scenario) {
		if(scenario.isFailed()) {
			TakesScreenshot ts = (TakesScreenshot)driver;
			byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "png", scenario.getName());
		}
	}

}
