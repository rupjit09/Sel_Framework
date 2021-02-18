package com.rupjit.automationqa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rupjit.automationqa.base.DriverFactory;
import com.rupjit.automationqa.base.TestBase;


public class WorkflowListingPage  extends TestBase{

		HomePage homepage;
	@FindBy(xpath="//button[contains(text(),'WORKFLOW')]")
	WebElement addNewWorkflow;
	
	public WorkflowListingPage() {
		PageFactory.initElements(DriverFactory.getInstance().getDriver(), this);
	}
	
	public WorkflowDesignerPage navigateToWfDesignerPage() {
		//waitForVisibilityOfElement(addNewWorkflow);
		addNewWorkflow.click();
		new TestBase().getNgWebDriverInstance().waitForAngularRequestsToFinish();
		return new WorkflowDesignerPage();
	}
}
