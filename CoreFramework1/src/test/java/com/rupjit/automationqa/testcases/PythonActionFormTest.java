package com.rupjit.automationqa.testcases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rupjit.automationqa.base.TestBase;
import com.rupjit.automationqa.pages.HomePage;
import com.rupjit.automationqa.pages.LoginPage;
import com.rupjit.automationqa.pages.PythonActionPage;
import com.rupjit.automationqa.pages.ShellActionPage;
import com.rupjit.automationqa.pages.WorkflowDesignerPage;
import com.rupjit.automationqa.pages.WorkflowDesignerPage.WfAction;
import com.rupjit.automationqa.pages.WorkflowListingPage;

public class PythonActionFormTest extends TestBase{
	HomePage homepage;
	WorkflowListingPage wflistPage;
	WorkflowDesignerPage wfDesignerPage;
	PythonActionPage pythonActionForm;

	@BeforeMethod
	public void setUp() throws IOException {
		initialize();
		homepage=new LoginPage().login(prop.getProperty("username"), prop.getProperty("password"));
		wflistPage=homepage.navigateToWorkflowListingPage();
		wfDesignerPage=wflistPage.navigateToWfDesignerPage();
		pythonActionForm=(PythonActionPage) wfDesignerPage.dragWfActionToWfDesigner(WfAction.pythonAction);

	}
	@Test
	public void verifyActionName() {
		String actionName=pythonActionForm.getActionName();
		Assert.assertEquals(actionName, "Python Action","Wf Action name not matching");
	}
}
