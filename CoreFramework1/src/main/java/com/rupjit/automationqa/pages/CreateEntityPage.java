package com.rupjit.automationqa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.rupjit.automationqa.base.TestBase;

public class CreateEntityPage extends TestBase{
	
	@FindBy(xpath="//span[contains(text(),'Business Information')]")
	WebElement businessInformationAccordian;
	
	@FindBy(xpath="//span[text()=' Technical Information ']")
	WebElement technicalInformationAccordian;
	
	@FindBy(xpath="//span[contains(text(),'Advanced Technical Information')]")
	WebElement advancedTechnicalInformationAccordian;

	@FindBy(xpath="//span[contains(text(),'Business Information')]//preceding-sibling::span")
	WebElement businessInformationChevron;
	
	@FindBy(xpath="//span[text()=' Technical Information ']//preceding-sibling::span")
	WebElement technicalInformationChevron;
	
	@FindBy(xpath="//span[contains(text(),'Advanced Technical Information')]//preceding-sibling::span")
	WebElement advancedTechnicalInformationChevron;
	
	//Business Information Fields
	@FindBy(xpath="//*[@header=\"Business Information\"]//div[contains(@class,'ui-accordion-content')]")
	WebElement LabelsTextBox;
}

