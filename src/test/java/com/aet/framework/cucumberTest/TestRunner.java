package com.aet.framework.cucumberTest;


import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(
		plugin = { "pretty", "html:target/cucumber-html-report", "json:target/cucumber-report.json" }, 
		features = {"src/main/resources/cucumberFeatures" }, 
		glue={
					"com.aet.framework.cucumberStepDefinitions"
		}
		)
		
				
		


public class TestRunner {

}
