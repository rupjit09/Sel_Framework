package com.rupjit.automationqa.testcases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rupjit.automationqa.base.TestBase;
import com.rupjit.automationqa.pages.HomePage;
import com.rupjit.automationqa.pages.LoginPage;
import com.rupjit.automationqa.pages.ShellActionPage;
import com.rupjit.automationqa.pages.WorkflowDesignerPage;
import com.rupjit.automationqa.pages.WorkflowDesignerPage.wfAction;
import com.rupjit.automationqa.pages.WorkflowListingPage;

public class ShellActionFormTest extends TestBase{
	HomePage homepage;
	WorkflowListingPage wflistPage;
	WorkflowDesignerPage wfDesignerPage;
	ShellActionPage shellActionForm;

	@BeforeMethod
	public void setUp() throws IOException {
		initialize();
		homepage=new LoginPage().login(prop.getProperty("username"), prop.getProperty("password"));
		wflistPage=homepage.navigateToWorkflowListingPage();
		wfDesignerPage=wflistPage.navigateToWfDesignerPage();
		shellActionForm=(ShellActionPage) wfDesignerPage.dragWfActionToWfDesigner(wfAction.SHELL);

	}
	@Test
	public void verifyActionName() {
		String actionName=shellActionForm.getActionName();
		Assert.assertEquals(actionName, "Shell Action","Wf Action name not matching");
	}
}
