package com.rupjit.automationqa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rupjit.automationqa.base.DriverFactory;
import com.rupjit.automationqa.base.TestBase;

public class CreateEntityPage extends TestBase{
	
	@FindBy(xpath="//span[contains(text(),'Business Information')]")
	public WebElement businessInformationAccordian;
	
	@FindBy(xpath="//span[text()=' Technical Information ']")
	public WebElement technicalInformationAccordian;
	
	@FindBy(xpath="//span[contains(text(),'Advanced Technical Information')]")
	public WebElement advancedTechnicalInformationAccordian;

	@FindBy(xpath="//span[contains(text(),'Business Information')]//preceding-sibling::span")
	WebElement businessInformationChevron;
	
	@FindBy(xpath="//span[text()=' Technical Information ']//preceding-sibling::span")
	WebElement technicalInformationChevron;
	
	@FindBy(xpath="//span[contains(text(),'Advanced Technical Information')]//preceding-sibling::span")
	WebElement advancedTechnicalInformationChevron;
	
	@FindBy(xpath="//span[contains(text(),'Business Information')]/parent::*[contains(@aria-expanded,'')]")
	public WebElement BusinessAriaExpanded;
	
	@FindBy(xpath="//span[text()=' Technical Information ']/parent::*[contains(@aria-expanded,'')]")
	public WebElement TechnicalAriaExpanded;
	
	@FindBy(xpath="//span[contains(text(),'Advanced Technical Information')]/parent::*[contains(@aria-expanded,'')]")
	public WebElement AdvTecAriaExpanded;
	
	//Business Information Fields
	@FindBy(xpath="//*[@formcontrolname='labelInput']//descendant::input")
	WebElement LabelsTextBox;
	
	
	public CreateEntityPage() {
		PageFactory.initElements(DriverFactory.getInstance().getDriver(), this);
	}
	
	public Boolean isAccordianExpanded(WebElement accordion) {
		return Boolean.parseBoolean(accordion.getAttribute("aria-expanded"));
		}
	
	public void clickAccordian(WebElement accordianName) throws InterruptedException {
		waitForElementToBeClickable(accordianName);
		accordianName.click();
		Thread.sleep(1000);
	}
}

