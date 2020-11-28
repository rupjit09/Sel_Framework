package com.rupjit.automationqa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rupjit.automationqa.base.TestBase;

public class ShellActionPage extends TestBase{

	@FindBy(xpath="(//div[@class='sidebar-header'])[1]//descendant::div[@class='header-title']")
	WebElement ActionName;

	
	public ShellActionPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String getActionName() {
		return ActionName.getText();
	}
	
}

