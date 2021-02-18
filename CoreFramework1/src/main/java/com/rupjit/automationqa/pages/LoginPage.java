package com.rupjit.automationqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rupjit.automationqa.base.DriverFactory;
import com.rupjit.automationqa.base.TestBase;


public class LoginPage extends TestBase{

	@FindBy(id="username")
	WebElement username;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(xpath="//button[contains(text(),'Login')]")
	WebElement loginBtn;
	
	
	public LoginPage() {
		PageFactory.initElements(DriverFactory.getInstance().getDriver(), this);
	}
	
	public String validateLoginPageTitle() {
		return DriverFactory.getInstance().getDriver().getTitle();
	}
	
	public HomePage login(String un,String pwd) throws Exception{
		username.sendKeys(un);
		password.sendKeys(pwd);
		new TestBase().getNgWebDriverInstance().waitForAngularRequestsToFinish();
		waitForInvisibilityOfElement(DriverFactory.getInstance().getDriver().findElement(By.xpath("//div[@class='ui-blockui-document ui-blockui ui-widget-overlay']")));
		loginBtn.click();
		return new HomePage();
	}
}
