package com.rupjit.automationqa.testcases;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rupjit.automationqa.base.TestBase;
import com.rupjit.automationqa.pages.HomePage;
import com.rupjit.automationqa.pages.LoginPage;
import com.rupjit.automationqa.pages.ShellActionPage;
import com.rupjit.automationqa.pages.WorkflowDesignerPage;
import com.rupjit.automationqa.pages.WorkflowDesignerPage.WfAction;
import com.rupjit.automationqa.pages.WorkflowListingPage;
import com.rupjit.automationqa.pages.ShellActionPage.EnterValueAt;

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
	//@Test
	public void dragShellAction() {
		wfDesignerPage.dragWfActionToWfDesigner(WfAction.shellAction);
	}
	//@Test
	public void dragPythonAction() {
		wfDesignerPage.dragWfActionToWfDesigner(WfAction.pythonAction);
	}
	@Test
	public void saveShellWf() {
		wfDesignerPage.dragWfActionToWfDesigner(WfAction.start);
		wfDesignerPage.dragWfActionToWfDesigner(WfAction.shellAction);
		ShellActionPage sh=new ShellActionPage();
		sh.fillText(EnterValueAt.STEPNAME, "StepNameAutomation");
		sh.fillText(EnterValueAt.DESCRIPTION,"Create Via Ui Automation");
		sh.fillText(EnterValueAt.SCRIPTCONTENT,"echo \"hello\"");
		sh.clickOKButton();
		wfDesignerPage.joinWfActionPorts(WfAction.start, WfAction.shellAction);
		wfDesignerPage.joinWfActionPorts(WfAction.shellAction, WfAction.stop);
		wfDesignerPage.setWfName("test123");
		
	}
	
	@AfterMethod
	public void closeBrowser() {
		//driver.quit();
	}
}
