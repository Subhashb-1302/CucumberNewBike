package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
			features={".//Features/HomePage.feature",
						".//Features/UsedCars.feature",
						".//Features/Login.feature"},
			//feature={".//target/rerun.txt"},
			glue="stepDefinitions",
			plugin= {"pretty", "html:reports/myreport.html", "rerun:target/rerun.txt",
						"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
					},
			dryRun=false,
			monochrome=true,
			publish=true,
			tags="@sanity"
)
public class TestRunner {

}
