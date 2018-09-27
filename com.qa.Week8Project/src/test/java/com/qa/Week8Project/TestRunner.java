package com.qa.Week8Project;


import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "C:\\Users\\Admin\\Documents\\assessment.feature")
public class TestRunner {
	 Result result = JUnitCore.runClasses(TestSuite.class);
}

