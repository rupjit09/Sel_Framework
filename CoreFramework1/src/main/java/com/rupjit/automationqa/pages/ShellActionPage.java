package com.rupjit.automationqa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.rupjit.automationqa.base.TestBase;

public class ShellActionPage extends TestBase{
	
	public enum GetText{
		ACTIONNAME,STEPNAME,DESCRIPTION,SCRIPTCONTENTRADIOBUTTON,SCRIPTFILERADIOBUTTON,SCRIPTCONTENT,
		ARGUMENTS,ENV_VARIABES,ONCLUSTER,KERBEROSTICKET,PROCEED
	}
	
	public enum EnterValueAt{
		STEPNAME,DESCRIPTION,SCRIPTCONTENT,ARGUMENTS,ENVIRONMENTVARIABLES
	}
	
	public enum SelectRadioButtons{
		USESCRIPTCONTENT,USESCRIPTFILE
	}
	
	public enum SelectCheckBox{
		EXECUTONCLUSTER,GENERATE_KERB_TICKET,PROCEED_ON_FAILURE
	}

	@FindBy(xpath="(//div[@class='sidebar-header'])[1]//descendant::div[@class='header-title']")
	WebElement ActionName;
	
	@FindBy(xpath="(//div[@class='sidebar-header'])[1]//descendant::div[contains(@class,'zi-close')]")
	WebElement TopCancelButton;
	
	@FindBy(xpath="//z-wf-action-property[@class='ng-star-inserted']//button[contains(@class,'submit')]")
	WebElement OKButton;
	
	@FindBy(xpath="//z-wf-action-property[@class='ng-star-inserted']//button[contains(@class,'zi-cancel')]")
	WebElement BottomCancelButton;
	
	//@FindBy(xpath="")
	//WebElement VerticalScrollBar;
	
	@FindBy(css="rect.draw2d_shape_zdp_shellAction")
	WebElement ShellActionIcon;

	@FindBy(xpath="//input[@formcontrolname='stepName']")
	WebElement StepNameTextBox;
	
	@FindBy(xpath="//input[@formcontrolname='stepName']//preceding-sibling::div[@class='field-label']")
	WebElement StepNameLabel;
	
	@FindBy(xpath="//input[@formcontrolname='stepName']//parent::*//child::span[contains(@class,'help-block')]")
	WebElement StepNameRequiredWarningMsg;
	
	@FindBy(xpath="//textarea[contains(@class,'ui-inputtextarea-resizable')]")
	WebElement DescriptionTextBox;
	
	@FindBy(xpath="//textarea[contains(@class,'ui-inputtextarea-resizable')]//preceding-sibling::div[@class='field-label']")
	WebElement DescriptionLabel;
	
	@FindBy(xpath="//p-radiobutton[@value='scriptContent']")
	WebElement ScriptContentRadioButton;
	
	@FindBy(xpath="//p-radiobutton[@value='scriptFile']")
	WebElement ScriptFileRadioButton;
	
	@FindBy(xpath="//div[@class='ace_content']")
	WebElement ScriptContentTextBox;
	
	@FindBy(xpath="//ace-editor[contains(@class,'ace-editor')]//preceding-sibling::div[@class='field-label']")
	WebElement ScriptContentLabel;
	
	@FindBy(xpath="//div[contains(text(),'Arguments:')]//following-sibling::input[@type='text']")
	WebElement ArgumentTextBox;
	
	@FindBy(xpath="//div[contains(text(),'Arg')]")
	WebElement ArgumentLabel;
	
	@FindBy(xpath="//div[contains(text(),'Environment Variables:')]//following-sibling::input[@type='text']")
	WebElement EnvVariableTextBox;
	
	@FindBy(xpath="//div[contains(text(),'Environment Variables:')]")
	WebElement EnvVariableLabel;
	
	@FindBy(xpath="//label[contains(text(),'Execute On Cluster')]")
	WebElement ExecuteOnClusterCheckboxLabel;
	
	@FindBy(xpath="//label[contains(text(),'Execute On Cluster')]//parent::*//child::span[contains(@class,'ui-chkbox-icon')]")
	WebElement ExecuteOnClusterCheckbox;
	
	@FindBy(xpath="//label[contains(text(),'Generate Kerberos Ticket')]")
	WebElement GenerateKerbTicketCheckboxLabel;
	
	@FindBy(xpath="//label[contains(text(),'Generate Kerberos Ticket')]//preceding-sibling::div[contains(@class,'ui-chkbox')]")
	WebElement GenerateKerbTicketCheckbox;
	
	@FindBy(xpath="//label[contains(text(),'Proceed to Next Step on Failure')]")
	WebElement ProceedOnFailureCheckboxLabel;
	
	@FindBy(xpath="//label[contains(text(),'Proceed to Next Step on Failure')]//preceding-sibling::div[contains(@class,'ui-chkbox')]")
	WebElement ProceedOnFailureCheckbox;
	
	
	
	
	public ShellActionPage() {
		PageFactory.initElements(driver, this);
		}
	
	public String getActionName() {
		return ActionName.getText();
	}
	
	public String getText(GetText Text) {
		WebElement labelLocator = null;
		switch(Text) {
		case ACTIONNAME:
			labelLocator=ActionName;
			break;
		case STEPNAME:
			labelLocator=StepNameLabel;
			break;
		case DESCRIPTION:
			labelLocator=DescriptionLabel;
			break;
		case SCRIPTCONTENTRADIOBUTTON:
			labelLocator=ScriptContentLabel;
			break;
		case SCRIPTFILERADIOBUTTON:
			labelLocator=ActionName;
			break;
		case SCRIPTCONTENT:
			labelLocator=ScriptContentLabel;
			break;
		case ARGUMENTS:
			labelLocator=ArgumentLabel;
			break;
		case ENV_VARIABES:
			labelLocator=EnvVariableLabel;
			break;
		case ONCLUSTER:
			labelLocator=ExecuteOnClusterCheckboxLabel;
			break;
		case KERBEROSTICKET:
			labelLocator=GenerateKerbTicketCheckboxLabel;
			break;
		case PROCEED:
			labelLocator=ProceedOnFailureCheckboxLabel;
			break;
		default:
			log.debug("Incorrect Option Passed");
			
		}
		return labelLocator.getText();
		
	}
	public void fillText(EnterValueAt enterValueAt,String TextToEnter) {
		WebElement textFillLocator=null;
		switch(enterValueAt) {
		case STEPNAME:
			textFillLocator=StepNameTextBox;
			break;
		case DESCRIPTION:
			textFillLocator=DescriptionTextBox;
			break;
		case SCRIPTCONTENT:
			textFillLocator=ScriptContentTextBox;
			action.moveToElement(textFillLocator).click().sendKeys(TextToEnter).build().perform();
			break;
		case ARGUMENTS:
			textFillLocator=ArgumentTextBox;
			break;
		case ENVIRONMENTVARIABLES:
			textFillLocator=EnvVariableTextBox;
			break;
		default:
			log.debug("Incorrect Option Passed");
			break;
		}
		if(!enterValueAt.toString().equalsIgnoreCase("SCRIPTCONTENT")){
		textFillLocator.sendKeys(TextToEnter);
		Assert.assertEquals(TextToEnter, textFillLocator.getAttribute("value"),"Entered Text not matching the text fetched");
		}
	}
	
	public void selectRadioButton(SelectRadioButtons radioButton) {
		WebElement radioButtonLocator=null;
		switch(radioButton) {
			case USESCRIPTCONTENT:
				radioButtonLocator=ScriptContentRadioButton;
				break;
			case USESCRIPTFILE:
				radioButtonLocator=ScriptFileRadioButton;
				break;
			default:
				log.debug("Incorrect Option Passed");
				break;
				
		}
		radioButtonLocator.click();
	}
	
	public void selectCheckbox(SelectCheckBox checkBoxSelect) {
		WebElement CheckBoxLocator=null;
		switch(checkBoxSelect) {
		case EXECUTONCLUSTER:
			CheckBoxLocator=ExecuteOnClusterCheckbox;
			break;
		case GENERATE_KERB_TICKET:
			CheckBoxLocator=GenerateKerbTicketCheckbox;
			break;
		case PROCEED_ON_FAILURE:
			CheckBoxLocator=ProceedOnFailureCheckbox;	
			break;
		default:
			log.debug("Incorrect Option Passed");
			break;
		}
		CheckBoxLocator.click();
	}
	
	public boolean isOKButtonEnabled() {
		return OKButton.isEnabled();
		
	}
	
	public void clickOKButton() {
		OKButton.click();
		
	}
	
}

