package com.rupjit.automationqa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.paulhammant.ngwebdriver.NgWebDriver;
import com.rupjit.automationqa.listeners.WebEventListener;
import com.rupjit.automationqa.util.Xls_Reader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
public static Logger log;
//public static Properties config;
public static Xls_Reader suitexls;
public static Xls_Reader Asuitexls;
public static Xls_Reader Bsuitexls;
public static Xls_Reader Csuitexls;
public static boolean isInitialized=false;

//
//public static WebDriver driver;
public static Properties prop;
//public static JavascriptExecutor js;
//public static NgWebDriver ngDriver;
//public static Actions action;
//public static WebDriverWait wait;
//public static EventFiringWebDriver eventDriver;
//public static WebEventListener eventListener;
//public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
BrowserFactory bf=new BrowserFactory();


public void initialize() throws IOException, InterruptedException {
	if(!isInitialized) {
	//initialize logger for logging
	log=Logger.getLogger("devpinoyLogger");
	
	//initialize config.properties file
	log.debug("Loading Config.properties file...");
	prop=new Properties();
	FileInputStream ip=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\rupjit\\automationqa\\config\\config.properties");
	prop.load(ip);
	log.debug("Config.properties file loaded");
	
	//Initializing XLS file
	log.debug("Loading XLS file...");
	suitexls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\main\\java\\com\\rupjit\\automationqa\\data\\Suite.xlsx");
	Asuitexls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\main\\java\\com\\rupjit\\automationqa\\data\\A suite.xlsx");
	Bsuitexls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\main\\java\\com\\rupjit\\automationqa\\data\\B suite.xlsx");
	Csuitexls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\main\\java\\com\\rupjit\\automationqa\\data\\C suite.xlsx");
	log.debug("XLS files loaded");
	isInitialized=true;
	}}
	
	@BeforeMethod
	public void LaunchApplication() throws IOException, InterruptedException {
		initialize();
		String browser=prop.getProperty("browser");
		String url=prop.getProperty("url");
		DriverFactory.getInstance().setDriver(new BrowserFactory().createBrowserInstance(browser));
		WebDriver driver=DriverFactory.getInstance().getDriver();
	EventFiringWebDriver eventDriver =new EventFiringWebDriver(driver);
	//Create object of WebEventListener class to register it with EventFiringWebDriver
	WebEventListener eventListener=new WebEventListener();
	eventDriver.register(eventListener);
	driver=eventDriver;
	driver.manage().window().maximize();
	driver.manage().timeouts().pageLoadTimeout(Long.parseLong(prop.getProperty("page_load_timeout")), TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(Long.parseLong(prop.getProperty("implicit_wait")), TimeUnit.SECONDS);
	driver.manage().timeouts().setScriptTimeout(120, TimeUnit.SECONDS);
	driver.manage().deleteAllCookies();
	//js=(JavascriptExecutor) driver;
	//ngDriver=new NgWebDriver(js);
	driver.navigate().to(url);
	//ngDriver.waitForAngularRequestsToFinish();
	getNgWebDriverInstance().waitForAngularRequestsToFinish();
	Thread.sleep(3000);
}
	
	@AfterMethod
	public void tearDown() {
		DriverFactory.getInstance().closeBrowser();
	}

/*public static synchronized WebDriver getDriver() {
	return tdriver.get();
}*/

public EventFiringWebDriver getEventFiringDriver(WebDriver driver) {
	EventFiringWebDriver eventDriver =new EventFiringWebDriver(driver);
	//Create object of WebEventListener class to register it with EventFiringWebDriver
	WebEventListener eventListener=new WebEventListener();
	eventDriver.register(eventListener);
	//driver=eventDriver;
	return eventDriver;
	}

public JavascriptExecutor getJSExecutorInstance() {
	JavascriptExecutor js=(JavascriptExecutor) DriverFactory.getInstance().getDriver();
	return js;
}
public NgWebDriver getNgWebDriverInstance() {
	NgWebDriver ngDriver=new NgWebDriver(getJSExecutorInstance());
	return ngDriver;
}
public void moveMouseTo(WebElement element) {
	Actions action1=new Actions(getEventFiringDriver(DriverFactory.getInstance().getDriver()));
	action1.moveToElement(element).build().perform();
}

public void dragAndDrop(WebElement source,int xOffset,int yOffset) {
	Actions action2=new Actions(getEventFiringDriver(DriverFactory.getInstance().getDriver()));
	action2.moveToElement(source).build().perform();
	action2.dragAndDropBy(source, xOffset, yOffset).build().perform();
}

public void dragAndDrop(WebElement source,WebElement target) {
	Actions action3=new Actions(getEventFiringDriver(DriverFactory.getInstance().getDriver()));
	action3.moveToElement(source).build().perform();
	action3.dragAndDrop(source, target).build().perform();
}

public void waitForVisibilityOfElement(WebElement element) {
	WebDriverWait wait1=new WebDriverWait(getEventFiringDriver(DriverFactory.getInstance().getDriver()), 30);
	wait1.until(ExpectedConditions.visibilityOf(element));
}
public void waitForElementToBeClickable(WebElement element) {
	WebDriverWait wait2=new WebDriverWait(getEventFiringDriver(DriverFactory.getInstance().getDriver()), 30);
	wait2.until(ExpectedConditions.elementToBeClickable(element));
}
public void waitForInvisibilityOfElement(WebElement element) {
	WebDriverWait wait3=new WebDriverWait(getEventFiringDriver(DriverFactory.getInstance().getDriver()), 30);
	wait3.until(ExpectedConditions.invisibilityOf(element));
}
public void javaScriptClick(WebElement element) throws Exception {
	try {
		if (element.isEnabled() && element.isDisplayed()) {
			System.out.println("Clicking on element with using java script click");

			((JavascriptExecutor) DriverFactory.getInstance().getDriver()).executeScript("arguments[0].click();", element);
		} else {
			System.out.println("Unable to click on element");
		}
	} catch (StaleElementReferenceException e) {
		System.out.println("Element is not attached to the page document "+ e.getStackTrace());
	} catch (NoSuchElementException e) {
		System.out.println("Element was not found in DOM "+ e.getStackTrace());
	} catch (Exception e) {
		System.out.println("Unable to click on element "+ e.getStackTrace());
	}
}

}
