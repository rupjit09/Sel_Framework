package com.rupjit.automationqa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rupjit.automationqa.base.TestBase;
import com.rupjit.automationqa.pages.CreateEntityPage;
import com.rupjit.automationqa.pages.LoginPage;

public class CreateEntityPageTest extends TestBase{
	CreateEntityPage createEntityPage;
	@BeforeMethod
	public void setup() throws Exception {
		initialize();
		createEntityPage=new LoginPage().login(prop.getProperty("username"), prop.getProperty("password")).navigateToEntityListingPage().naviagteToCreateEntityPageByForm();
	}
	
	@Test
	public void verifyBusinessAccordianExpandMinimize() throws InterruptedException {
			if(!createEntityPage.isAccordianExpanded(createEntityPage.BusinessAriaExpanded)) {
			createEntityPage.clickAccordian(createEntityPage.businessInformationAccordian);
			}
			createEntityPage.clickAccordian(createEntityPage.businessInformationAccordian);
			Assert.assertEquals(Boolean.toString(createEntityPage.isAccordianExpanded(createEntityPage.BusinessAriaExpanded)), "false","Business Info Area cannot be shrinked");
			createEntityPage.clickAccordian(createEntityPage.businessInformationAccordian);
			Assert.assertEquals(Boolean.toString(createEntityPage.isAccordianExpanded(createEntityPage.BusinessAriaExpanded)), "true","Business Info Area cannot be Expanded");
		
	}
	
	@Test
	public void verifyTechnicalInfoAccordianExpandMinimize() throws InterruptedException {
		
		if(createEntityPage.isAccordianExpanded(createEntityPage.BusinessAriaExpanded)) {
			createEntityPage.clickAccordian(createEntityPage.businessInformationAccordian);
			}
			if(!createEntityPage.isAccordianExpanded(createEntityPage.TechnicalAriaExpanded)) {
			createEntityPage.clickAccordian(createEntityPage.technicalInformationAccordian);
			}
			createEntityPage.clickAccordian(createEntityPage.technicalInformationAccordian);
			Assert.assertEquals(Boolean.toString(createEntityPage.isAccordianExpanded(createEntityPage.TechnicalAriaExpanded)), "false","Business Info Area cannot be shrinked");
			createEntityPage.clickAccordian(createEntityPage.technicalInformationAccordian);
			Assert.assertEquals(Boolean.toString(createEntityPage.isAccordianExpanded(createEntityPage.TechnicalAriaExpanded)), "true","Business Info Area cannot be Expanded");
		
	}
	
	@Test
	public void verifyAdvTechInfoAccordianExpandMinimize() throws InterruptedException {
		if(createEntityPage.isAccordianExpanded(createEntityPage.BusinessAriaExpanded)) {
			createEntityPage.clickAccordian(createEntityPage.businessInformationAccordian);
			}
			if(!createEntityPage.isAccordianExpanded(createEntityPage.AdvTecAriaExpanded)) {
			createEntityPage.clickAccordian(createEntityPage.advancedTechnicalInformationAccordian);
			}
			createEntityPage.clickAccordian(createEntityPage.advancedTechnicalInformationAccordian);
			Assert.assertEquals(Boolean.toString(createEntityPage.isAccordianExpanded(createEntityPage.AdvTecAriaExpanded)), "false","Adv Tech Info Area cannot be shrinked");
			createEntityPage.clickAccordian(createEntityPage.advancedTechnicalInformationAccordian);
			Assert.assertEquals(Boolean.toString(createEntityPage.isAccordianExpanded(createEntityPage.AdvTecAriaExpanded)), "true","Adv Tech Info Area cannot be Expanded");
		
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
}
