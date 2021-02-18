package com.rupjit.automationqa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rupjit.automationqa.base.TestBase;
import com.rupjit.automationqa.pages.LoginPage;
import com.rupjit.automationqa.pages.PythonActionPage;
import com.rupjit.automationqa.pages.WorkflowDesignerPage;
import com.rupjit.automationqa.pages.WorkflowDesignerPage.WfAction;

public class PythonActionFormTest extends TestBase{
	WorkflowDesignerPage wfDesignerPage;
	PythonActionPage pythonActionForm;

	@BeforeMethod
	public void setUp() throws Exception {
		//initialize();
		//wfDesignerPage=new LoginPage().login(prop.getProperty("username"), prop.getProperty("password")).navigateToWorkflowListingPage().navigateToWfDesignerPage();
		//pythonActionForm=(PythonActionPage) wfDesignerPage.dragWfActionToWfDesigner(WfAction.pythonAction);

	}
	@Test
	public void verifyActionName() throws Exception {
		wfDesignerPage=new LoginPage().login(prop.getProperty("username"), prop.getProperty("password")).navigateToWorkflowListingPage().navigateToWfDesignerPage();
		pythonActionForm=(PythonActionPage) wfDesignerPage.dragWfActionToWfDesigner(WfAction.pythonAction);

		String actionName=pythonActionForm.getActionName();
		Assert.assertEquals(actionName, "Python Action","Wf Action name not matching");
	}
}
