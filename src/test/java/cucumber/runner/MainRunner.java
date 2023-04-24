package cucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/cucumber/features", glue = {"cucumber.steps"},
        monochrome = true,
        plugin = {"pretty","junit:target/JUnitReports/report.xml",
                "json:target/JSONReports/report.json",
                "html:target/HTMLReports.html"}, tags = "@smoke")
public class MainRunner {
}
