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
import com.rupjit.automationqa.pages.ShellActionPage.EnterValueAt;
import com.rupjit.automationqa.pages.ShellActionPage.GetText;
import com.rupjit.automationqa.pages.WorkflowDesignerPage;
import com.rupjit.automationqa.pages.WorkflowDesignerPage.WfAction;
import com.rupjit.automationqa.pages.WorkflowListingPage;

import io.qameta.allure.Description;

public class ShellActionFormTest extends TestBase{
	WorkflowDesignerPage wfDesignerPage;
	ShellActionPage shellActionForm;

	/*@BeforeMethod
	public void setUp() throws Exception {
		initialize();
		wfDesignerPage=new LoginPage().login(prop.getProperty("username"), prop.getProperty("password")).navigateToWorkflowListingPage().navigateToWfDesignerPage();
		shellActionForm=(ShellActionPage) wfDesignerPage.dragWfActionToWfDesigner(WfAction.shellAction);

	}*/
	@Test
	@Description("Verify the action name displayed")
	public void verifyActionName() throws Exception {
		wfDesignerPage=new LoginPage().login(prop.getProperty("username"), prop.getProperty("password")).navigateToWorkflowListingPage().navigateToWfDesignerPage();
		shellActionForm=(ShellActionPage) wfDesignerPage.dragWfActionToWfDesigner(WfAction.shellAction);

		String actionName=shellActionForm.getText(GetText.ACTIONNAME);
		Assert.assertEquals(actionName, "Shell Action","Wf Action name not matching");
	}
	@Test
	@Description("Verify that OK button is enabled only after filling the mandatary fields")
	public void verifyOKbuttonEnablement() throws Exception {
		wfDesignerPage=new LoginPage().login(prop.getProperty("username"), prop.getProperty("password")).navigateToWorkflowListingPage().navigateToWfDesignerPage();
		shellActionForm=(ShellActionPage) wfDesignerPage.dragWfActionToWfDesigner(WfAction.shellAction);

		Assert.assertEquals(shellActionForm.isOKButtonEnabled(),false,"OK button is enabled before passing mangatory fields");
		//shellActionForm.fillText(EnterValueAt.STEPNAME, "StepNameAutomation");
		shellActionForm.fillText(EnterValueAt.DESCRIPTION,"Create Via Ui Automation");
		shellActionForm.fillText(EnterValueAt.SCRIPTCONTENT,"echo \"hello\"");
		shellActionForm.fillText(EnterValueAt.ARGUMENTS,"arg1 arg2");
		Assert.assertEquals(shellActionForm.isOKButtonEnabled(),true,"OK button is not enabled after passing mangatory fields");
		}
}
