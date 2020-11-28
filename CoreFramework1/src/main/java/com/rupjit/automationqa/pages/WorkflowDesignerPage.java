package com.rupjit.automationqa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rupjit.automationqa.base.TestBase;


public class WorkflowDesignerPage extends TestBase{
	
	public enum wfAction{
		SHELL,PYTHON
	}
	
	WorkflowListingPage wfListPage;
	
	@FindBy(xpath="//div[@class='controls-wrapper']//descendant::div[@class='control-element zi-controls']")
	WebElement ControlsPanel;

	@FindBy(xpath="//div[@class='controls-wrapper']//descendant::div[@class='control-element zi-popular']")
	WebElement PopularPanel;

	@FindBy(xpath="//div[@class='controls-wrapper']//descendant::div[@class='control-element zi-input-output']")
	WebElement InputOutputPanel;

	@FindBy(xpath="//div[@class='controls-wrapper']//descendant::div[@class='control-element zi-convertors']")
	WebElement ConvertorPanel;

	@FindBy(xpath="//div[@class='controls-wrapper']//descendant::div[@class='control-element zi-transformars']")
	WebElement TransformerPanel;

	@FindBy(xpath="//div[@class='controls-wrapper']//descendant::div[@class='control-element zi-quality']")
	WebElement QualityPanel;
	
	//Popular actions
	@FindBy(xpath="//div[contains(text(),'Shell')]")
	WebElement ShellAction;
	
	@FindBy(xpath="//div[contains(text(),'Python')]")
	WebElement PythonAction;

	
	public WorkflowDesignerPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public Object dragWfActionToWfDesigner(wfAction name) {
		switch(name) {
		case SHELL:
		{
			moveMouseTo(PopularPanel);
			waitForVisibilityOfElement(ShellAction);
			dragAndDrop(ShellAction, 150, 10 );
			return new ShellActionPage();
		}
		case PYTHON:
		{
			moveMouseTo(PopularPanel);
			waitForVisibilityOfElement(PythonAction);
			dragAndDrop(PythonAction, 150, 10 );
			return new PythonActionPage();
		}
		default:
		{
			log.debug("Incorrect Workflow Action Passed as Argument");
			return "Incorrect Workflow Action Passed as Argument";
		}
		}
	}
}
