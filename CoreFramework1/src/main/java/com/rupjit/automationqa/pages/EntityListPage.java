package com.rupjit.automationqa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.rupjit.automationqa.base.TestBase;

public class EntityListPage  extends TestBase{

	@FindBy()
	WebElement searchEntityTextBox;
	
	@FindBy()
	WebElement searchEntityIcon;
	
	@FindBy()
	WebElement addEntityButton;
}
