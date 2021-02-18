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
		//initialize();
		//loginpage=new LoginPage(); 
	}
	
	@Test
	public void loginPageTitleTest() {
		loginpage=new LoginPage(); 
		String title=loginpage.validateLoginPageTitle();
		Assert.assertEquals(title, "Arena");
	}
	@Test
	public void loginTest() throws Exception {
		loginpage=new LoginPage(); 
		loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		new TestBase().getNgWebDriverInstance().waitForAngularRequestsToFinish();
	}
	
	@Test
	public void loginFailTest() throws Exception {
		loginpage=new LoginPage(); 
		loginpage.login("admin", "noasd");
		Assert.assertEquals("A", "B");
		new TestBase().getNgWebDriverInstance().waitForAngularRequestsToFinish();
	}
	
}
