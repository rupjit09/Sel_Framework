package com.rupjit.automationqa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rupjit.automationqa.base.TestBase;
import com.rupjit.automationqa.pages.LoginPage;


public class LoginPageTest extends TestBase{
	LoginPage loginpage;
	
	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		initialize();
		loginpage=new LoginPage(); 
	}
	
	@Test
	public void loginPageTitleTest() {
		String title=loginpage.validateLoginPageTitle();
		Assert.assertEquals(title, "Arena");
	}
	@Test
	public void loginTest() throws Exception {
		loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		ngDriver.waitForAngularRequestsToFinish();
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
}
