package com.qa.Week8Project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;



public class SeleniumTests {
	
	WebDriver driver = null;


	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Documents\\chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println(driver);

	}	
	
	@Test
	public void seleniumTest() throws InterruptedException {
		System.out.println(driver);
		try {
		driver.get("http://10.0.10.10:4200/petclinic/welcome");
		HomePage home = PageFactory.initElements(driver, HomePage.class);
		home.goToAll();	
		System.out.println(driver.getTitle());
		}
	    catch (Exception e) {
         e.printStackTrace();
	  	}
	}

	@After
	public void endSetup() {
		driver.close();
		driver.quit();
	}
}
