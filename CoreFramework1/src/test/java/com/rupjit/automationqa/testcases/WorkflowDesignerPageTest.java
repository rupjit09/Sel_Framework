package com.rupjit.automationqa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.rupjit.automationqa.base.TestBase;
import com.rupjit.automationqa.listeners.TestAllureListener;
import com.rupjit.automationqa.pages.LoginPage;
import com.rupjit.automationqa.pages.PythonActionPage;
import com.rupjit.automationqa.pages.ShellActionPage;
import com.rupjit.automationqa.pages.WorkflowDesignerPage;
import com.rupjit.automationqa.pages.WorkflowDesignerPage.WfAction;
import com.rupjit.automationqa.pages.ShellActionPage.EnterValueAt;
@Listeners({TestAllureListener.class})
public class WorkflowDesignerPageTest extends TestBase{
	WorkflowDesignerPage wfDesignerPage;
	@BeforeMethod
	public void setup() throws Exception {
		//wfDesignerPage=new LoginPage().login(prop.getProperty("username"), prop.getProperty("password")).navigateToWorkflowListingPage().navigateToWfDesignerPage();
	}
	
	@Test
	public void dragShellAction() throws Exception {
		wfDesignerPage=new LoginPage().login(prop.getProperty("username"), prop.getProperty("password")).navigateToWorkflowListingPage().navigateToWfDesignerPage();
		wfDesignerPage.dragWfActionToWfDesigner(WfAction.shellAction);
		String shellActionName=new ShellActionPage().getActionName();
		Assert.assertEquals("Shell Action", shellActionName,"Action name is not matching");
	}
	@Test
	public void dragPythonAction() throws Exception {
		wfDesignerPage=new LoginPage().login(prop.getProperty("username"), prop.getProperty("password")).navigateToWorkflowListingPage().navigateToWfDesignerPage();
		wfDesignerPage.dragWfActionToWfDesigner(WfAction.pythonAction);
		String pythonActionName=new PythonActionPage().getActionName();
		Assert.assertEquals("Python Action", pythonActionName,"Action name is not matching");
	}
	@Test
	public void saveShellWf() throws Exception {
		wfDesignerPage=new LoginPage().login(prop.getProperty("username"), prop.getProperty("password")).navigateToWorkflowListingPage().navigateToWfDesignerPage();
		wfDesignerPage.dragWfActionToWfDesigner(WfAction.start);
		wfDesignerPage.dragWfActionToWfDesigner(WfAction.shellAction);
		ShellActionPage sh=new ShellActionPage();
		sh.fillText(EnterValueAt.STEPNAME, "StepNameAutomation");
		sh.fillText(EnterValueAt.DESCRIPTION,"Create Via Ui Automation");
		sh.fillText(EnterValueAt.SCRIPTCONTENT,"echo \"hello\"");
		sh.clickOKButton();
		wfDesignerPage.joinWfActionPorts(WfAction.start, WfAction.shellAction);
		wfDesignerPage.joinWfActionPorts(WfAction.shellAction, WfAction.stop);
		long nanos = java.lang.System.nanoTime();
		long threadId = Thread.currentThread().getId();
		String Wfname="UIAUTO_Shell"+nanos+threadId;
		wfDesignerPage.setWfName(Wfname);
		//wfDesignerPage.clickSaveWFButton();
	}
}
