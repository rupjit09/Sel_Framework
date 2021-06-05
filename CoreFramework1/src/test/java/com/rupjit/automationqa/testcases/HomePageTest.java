package com.rupjit.automationqa.testcases;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import com.rupjit.automationqa.base.CustomLogger;
import com.rupjit.automationqa.base.TestBase;
import com.rupjit.automationqa.pages.HomePage;
import com.rupjit.automationqa.pages.LoginPage;



public class HomePageTest extends TestBase{

	HomePage homepage;
	@BeforeMethod
	public void setup() throws Exception {
		//homepage=new LoginPage().login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void verifyNavigateToEntityListingPage() throws Exception {
		homepage=new LoginPage().login(prop.getProperty("username"), prop.getProperty("password"));
		CustomLogger.startTestCase(new Throwable().getStackTrace()[0].getMethodName());
		homepage.navigateToEntityListingPage();
		System.out.println("Page Titl is ");
	}
	
	@Test(priority=2)
	public void verifyNavigateToWorkflowListingPage() throws Exception {
		homepage=new LoginPage().login(prop.getProperty("username"), prop.getProperty("password"));
		homepage.navigateToWorkflowListingPage();
		System.out.println("Page Titl is ");
	}
	
	
	
}
