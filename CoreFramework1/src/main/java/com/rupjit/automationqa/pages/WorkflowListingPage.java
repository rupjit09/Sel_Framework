package com.rupjit.automationqa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rupjit.automationqa.base.TestBase;


public class WorkflowListingPage  extends TestBase{

		HomePage homepage;
	@FindBy(xpath="//button[contains(text(),'WORKFLOW')]")
	WebElement addNewWorkflow;
	
	public WorkflowListingPage() {
		PageFactory.initElements(driver, this);
	}
	
	public WorkflowDesignerPage navigateToWfDesignerPage() {
		//waitForVisibilityOfElement(addNewWorkflow);
		addNewWorkflow.click();
		ngDriver.waitForAngularRequestsToFinish();
		return new WorkflowDesignerPage();
	}
}
