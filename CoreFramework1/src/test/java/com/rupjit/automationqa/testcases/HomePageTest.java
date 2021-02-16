package com.rupjit.automationqa.testcases;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import com.rupjit.automationqa.base.TestBase;
import com.rupjit.automationqa.pages.HomePage;
import com.rupjit.automationqa.pages.LoginPage;



public class HomePageTest extends TestBase{

	HomePage homepage;
	@BeforeMethod
	public void setup() throws Exception {
		initialize();
		homepage=new LoginPage().login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void verifyNavigateToEntityListingPage() {
		homepage.navigateToEntityListingPage();
		System.out.println("Page Titl is "+driver.getTitle());
	}
	
	@Test(priority=2)
	public void verifyNavigateToWorkflowListingPage() {
		homepage.navigateToWorkflowListingPage();
		System.out.println("Page Titl is "+driver.getTitle());
	}
	
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
}
