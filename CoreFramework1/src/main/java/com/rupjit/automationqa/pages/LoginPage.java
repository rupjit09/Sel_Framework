package com.rupjit.automationqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rupjit.automationqa.base.TestBase;


public class LoginPage extends TestBase{

	@FindBy(id="username")
	WebElement username;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(xpath="//button[contains(text(),'Login')]")
	WebElement loginBtn;
	
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
	public HomePage login(String un,String pwd) throws Exception{
		username.sendKeys(un);
		password.sendKeys(pwd);
		ngDriver.waitForAngularRequestsToFinish();
		waitForInvisibilityOfElement(driver.findElement(By.xpath("//div[@class='ui-blockui-document ui-blockui ui-widget-overlay']")));
		loginBtn.click();
		return new HomePage();
	}
}
