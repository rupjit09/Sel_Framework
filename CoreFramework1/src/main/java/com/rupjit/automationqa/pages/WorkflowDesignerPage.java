package com.rupjit.automationqa.pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rupjit.automationqa.base.DriverFactory;
import com.rupjit.automationqa.base.TestBase;


public class WorkflowDesignerPage extends TestBase{
	public enum WfAction{
		start,shellAction,pythonAction,stop
	}
	
	WorkflowListingPage wfListPage;
	@FindBy(xpath="//div[@class='z-inplace-editor']//descendant::div")
	WebElement wfNameBox;
	
	@FindBy(xpath="//button[@id='save-wf']")
	WebElement saveWfButton;
	
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
	
	//ControlPanel Actions
	@FindBy(xpath="//div[contains(text(),'Start')]")
	WebElement StartAction;
	
	//Popular actions
	@FindBy(xpath="//div[contains(text(),'Shell')]")
	WebElement ShellAction;
	
	@FindBy(xpath="//div[contains(text(),'Python')]")
	WebElement PythonAction;

	
	public WorkflowDesignerPage() {
		PageFactory.initElements(DriverFactory.getInstance().getDriver(), this);
	}
	
	
	public Object dragWfActionToWfDesigner(WfAction name) {
		switch(name) {
		case start:
		{
			moveMouseTo(ControlsPanel);
			try {
			waitForVisibilityOfElement(StartAction);
			}catch (TimeoutException e) {
				moveMouseTo(ControlsPanel);
				waitForVisibilityOfElement(StartAction);
			}
			dragAndDrop(StartAction, 150, 10 );
			new TestBase().getNgWebDriverInstance().waitForAngularRequestsToFinish();
			return new ShellActionPage();
		}
		
		case shellAction:
		{
			moveMouseTo(PopularPanel);
			try {
			waitForVisibilityOfElement(ShellAction);
			}catch (TimeoutException e) {
			moveMouseTo(PopularPanel);
			waitForVisibilityOfElement(ShellAction);
			}
			dragAndDrop(ShellAction, 150, 10 );
			return new ShellActionPage();
		}
		case pythonAction:
		{
			moveMouseTo(PopularPanel);
			try {
			waitForVisibilityOfElement(PythonAction);
			}catch(TimeoutException e) {
				moveMouseTo(PopularPanel);
				waitForVisibilityOfElement(PythonAction);
			}
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
	
	private static HashMap<String, WebElement> getNodeLocation(String actionName){
		WebElement action=DriverFactory.getInstance().getDriver().findElement(By.xpath("//*[contains(@class,'zdp_"+actionName+"')][1]"));
		int xnode1=Integer.parseInt(action.getAttribute("x"));
		int ynode1=Integer.parseInt(action.getAttribute("y"));
		int cx_input=xnode1;
		int cy_input=ynode1+20;
		int cx_output=xnode1+40;
		int cy_output=ynode1+20;
		HashMap<String,WebElement> ports=new HashMap<String, WebElement>();

		if(!actionName.equalsIgnoreCase("start")) {
		WebElement inputPort=DriverFactory.getInstance().getDriver().findElement(By.xpath("//*[@class='draw2d_InputPort' and @cx='"+cx_input+"' and @cy='"+cy_input+"']"));
		ports.put("inputPort", inputPort);
		}
		if(!actionName.equalsIgnoreCase("stop")) {
		WebElement outputPort=DriverFactory.getInstance().getDriver().findElement(By.xpath("//*[@class='draw2d_OutputPort' and @cx='"+cx_output+"' and @cy='"+cy_output+"']"));
		ports.put("outputPort", outputPort);
		}
		return ports;				
	}
	
	public void joinWfActionPorts(WfAction fromSourceNode,WfAction toDestinationNode){		
		WebElement source=	getNodeLocation(fromSourceNode.toString()).get("outputPort");
		WebElement target=getNodeLocation(toDestinationNode.toString()).get("inputPort");
		dragAndDrop(source, target);	 
	}
	
	public void setWfName(String Wfname) throws InterruptedException {
		Actions action= new Actions(DriverFactory.getInstance().getDriver());
		action.moveToElement(wfNameBox).click().build().perform();
		action.keyDown(Keys.CONTROL).sendKeys(String.valueOf('\u0061')).build().perform();
		Thread.sleep(2000);
		action.keyUp(Keys.CONTROL).build().perform();
		action.moveToElement(wfNameBox).sendKeys(Keys.BACK_SPACE).build().perform();
		Thread.sleep(2000);
		action.moveToElement(wfNameBox).click().sendKeys(Wfname).build().perform();
		}
	
	public void clickSaveWFButton() {
		saveWfButton.click();
		new TestBase().getNgWebDriverInstance().waitForAngularRequestsToFinish();
	}
}
