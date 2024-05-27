package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", plugin = "json:target/jsonReports/cucumber-reports.json", glue = {
		"stepDefinations" }, tags = ("@AddPlace")) // ,tags=("@DeletePlace")
public class TestRunner {

}
