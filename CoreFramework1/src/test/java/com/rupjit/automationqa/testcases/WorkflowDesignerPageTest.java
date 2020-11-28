package com.rupjit.automationqa.testcases;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rupjit.automationqa.base.TestBase;
import com.rupjit.automationqa.pages.HomePage;
import com.rupjit.automationqa.pages.LoginPage;
import com.rupjit.automationqa.pages.WorkflowDesignerPage;
import com.rupjit.automationqa.pages.WorkflowDesignerPage.wfAction;
import com.rupjit.automationqa.pages.WorkflowListingPage;

public class WorkflowDesignerPageTest extends TestBase{
	HomePage homepage;
	WorkflowListingPage wflistPage;
	WorkflowDesignerPage wfDesignerPage;
	
	@BeforeMethod
	public void setUp() throws IOException {
		initialize();
		homepage=new LoginPage().login(prop.getProperty("username"), prop.getProperty("password"));
		wflistPage=homepage.navigateToWorkflowListingPage();
		wfDesignerPage=wflistPage.navigateToWfDesignerPage();

	}
	@Test
	public void dragShellAction() {
		wfDesignerPage.dragWfActionToWfDesigner(wfAction.SHELL);
	}
	@Test
	public void dragPythonAction() {
		wfDesignerPage.dragWfActionToWfDesigner(wfAction.PYTHON);
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
}
