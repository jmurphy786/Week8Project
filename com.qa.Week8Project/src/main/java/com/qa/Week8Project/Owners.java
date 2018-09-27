package com.qa.Week8Project;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Owners {
	
	@FindBy(xpath = "//*[@id=\"0\"]")
	private WebElement selectOwner;
	
	
	public void goToSpecificOwner() throws InterruptedException
	{
		System.out.println(selectOwner.toString());
		
	}
}
