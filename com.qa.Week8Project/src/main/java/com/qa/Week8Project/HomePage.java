package com.qa.Week8Project;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
	
	@FindBy(xpath = "/html/body/app-root/div[1]/nav/div/ul/li[4]/a/span[2]")
	private WebElement ownersTab;
	
	@FindBy(xpath = "/html/body/app-root/div[1]/nav/div/ul/li[2]/ul/li[1]/a")
	private WebElement allTab;
	
	public void goToAll() throws InterruptedException
	{

		
	}
}
