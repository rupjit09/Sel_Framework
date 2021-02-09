package com.rupjit.automationqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rupjit.automationqa.base.TestBase;

public class EntityListPage  extends TestBase{

	@FindBy()
	WebElement searchEntityTextBox;
	
	@FindBy()
	WebElement searchEntityIcon;
	
	@FindBy(xpath="//button[@id='addEntity']")
	WebElement addEntityButton;
	
	@FindBy(xpath="//span[text()='Add Using Form']")
	WebElement addEntityUsingForm;
	
	@FindBy(xpath="//span[text()='Upload File']")
	WebElement addEntityUploadFile;
	
	public EntityListPage() {
		PageFactory.initElements(driver, this);
	}
	
	public CreateEntityPage naviagteToCreateEntityPageByForm() {
		addEntityButton.click();
		addEntityUsingForm.click();
		return new CreateEntityPage();
	}
	
	public void naviagteToCreateEntityPageByUploadFile() {
		
	}
}
