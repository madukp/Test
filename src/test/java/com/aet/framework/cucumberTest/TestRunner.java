package com.aet.framework.cucumberTest;


import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(
		plugin = { "pretty", "html:target/cucumber-html-report", "json:target/cucumber-report.json" }, 
		features = { "src/main/resources/cucumberFeatures/CreateItem.feature" }, 
		glue={
						"com.aet.framework.cucumberStepDefinitions"
		},
		//tags = { "@test","@BVT" }
				tags = { "@CreateItem " }
		
)


public class TestRunner {

}
