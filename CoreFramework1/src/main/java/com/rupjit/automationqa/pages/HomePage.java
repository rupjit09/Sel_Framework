package com.rupjit.automationqa.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rupjit.automationqa.base.DriverFactory;
import com.rupjit.automationqa.base.TestBase;

public class HomePage  extends TestBase{
	@FindBy(xpath="//div[@class='masthead']//descendant::input[@id='searchText']")
	WebElement globalSearchTextEntryBox;
	
	@FindBy(xpath="//div[@class='masthead']//descendant::button[@id='searchButton']")
	WebElement globalSearchIcon;
	
	@FindBy(xpath="//ul[@id='zdp-menu']//descendant::div[contains(text(),'Catalog')]")
	WebElement catalog;
	
	@FindBy(xpath="//ul[@id='zdp-menu']//descendant::div[contains(text(),'Control')]")
	WebElement control;
	
	@FindBy(xpath="//ul[@id='zdp-menu']//descendant::div[contains(text(),'CONSUME')]")
	WebElement consume;
	
	@FindBy(xpath="//ul[@id='zdp-menu']//descendant::div[contains(text(),'MONITOR')]")
	WebElement monitor;
	
	@FindBy(xpath="//ul[@id='zdp-menu']//descendant::div[contains(text(),'SETTINGS')]")
	WebElement settings;
	
	@FindBy(xpath="//ul[@id='zdp-menu']//descendant::div[contains(text(),'FANBASE')]")
	WebElement fanbase;
	
	//Options in CATALOG that appears only on hovering the mouse
		@FindBy(xpath="//div[@class='submenu on-load']//descendant::span[contains(text(),'Entities')]")
		WebElement Entities;
		
		@FindBy(xpath="//div[@class='submenu on-load']//descendant::span[contains(text(),'Database Wizard')]")
		WebElement Database_Wizard;
		
		@FindBy(xpath="//div[@class='submenu on-load']//descendant::span[contains(text(),'File Wizard')]")
		WebElement File_Wizard;
		
		@FindBy(xpath="//div[@class='submenu on-load']//descendant::span[contains(text(),'Stream')]")
		WebElement Stream;
		
		@FindBy(xpath="//div[@class='submenu on-load']//descendant::span[contains(text(),'File Patterns')]")
		WebElement File_Patterns;
		
		@FindBy(xpath="//div[@class='submenu on-load']//descendant::span[contains(text(),'Managed Entities')]")
		WebElement Managed_Entities;
		
		@FindBy(xpath="//div[@class='submenu on-load']//descendant::span[contains(text(),'View Managed Lists')]")
		WebElement ViewManagedLists;
		
	//Options in CONSUME that appears only on hovering the mouse
		@FindBy(xpath="//div[@class='submenu on-load']//descendant::span[contains(text(),'Manage Workflow')]")
		WebElement ManageWorkflow;
		
	public HomePage() {
		PageFactory.initElements(DriverFactory.getInstance().getDriver(), this);
	}
	
	public String HomePageTitle() {
		return DriverFactory.getInstance().getDriver().getTitle();
	}
	
	public EntityListPage navigateToEntityListingPage() {
		new TestBase().moveMouseTo(catalog);
		//moveMouseTo(catalog);
		try {
		waitForVisibilityOfElement(Entities);
		}catch(TimeoutException e){
			moveMouseTo(catalog);
			waitForVisibilityOfElement(Entities);
		}
		Entities.click();
		new TestBase().getNgWebDriverInstance().waitForAngularRequestsToFinish();
		return new EntityListPage();
	}
	
	public WorkflowListingPage navigateToWorkflowListingPage() {
		moveMouseTo(consume);
		try {
		waitForVisibilityOfElement(ManageWorkflow);
		}catch (TimeoutException e) {
			moveMouseTo(consume);	
			waitForVisibilityOfElement(ManageWorkflow);
		}
		ManageWorkflow.click();
		new TestBase().getNgWebDriverInstance().waitForAngularRequestsToFinish();
		return new WorkflowListingPage();
	}
}
