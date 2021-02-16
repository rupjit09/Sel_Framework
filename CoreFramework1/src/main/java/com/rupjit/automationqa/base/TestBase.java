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
public static WebDriver driver;
public static Properties prop;
public static JavascriptExecutor js;
public static NgWebDriver ngDriver;
public static Actions action;
public static WebDriverWait wait;
public static EventFiringWebDriver eventDriver;
public static WebEventListener eventListener;
public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();


public static void initialize() throws IOException, InterruptedException {
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
	}
	
	//
	String browsername=prop.getProperty("browser");
	if(browsername.equalsIgnoreCase("chrome"))
	{
		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("enable-automation");
		//options.addArguments("--headless");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-browser-side-navigation");
		options.addArguments("--disable-extensions");
		options.addArguments("--dns-prefetch-disable");
		options.addArguments("--disable-gpu");
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		driver = new ChromeDriver(options);
	}else if(browsername.equalsIgnoreCase("firefox")) {
		WebDriverManager.firefoxdriver().setup();
		FirefoxOptions options = new FirefoxOptions().addPreference("security.insecure_field_warning.contextual.enabled", false);
		driver = new FirefoxDriver(options);
		
	}
	eventDriver =new EventFiringWebDriver(driver);
	//Create object of WebEventListener class to register it with EventFiringWebDriver
	eventListener=new WebEventListener();
	eventDriver.register(eventListener);
	driver=eventDriver;
	
	driver.manage().window().maximize();
	driver.manage().timeouts().pageLoadTimeout(Long.parseLong(prop.getProperty("page_load_timeout")), TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(Long.parseLong(prop.getProperty("implicit_wait")), TimeUnit.SECONDS);
	driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
	driver.manage().deleteAllCookies();
	tdriver.set(driver);
	js=(JavascriptExecutor) driver;
	ngDriver=new NgWebDriver(js);
	driver.get(prop.getProperty("url"));
	ngDriver.waitForAngularRequestsToFinish();
	Thread.sleep(3000);
}

public static synchronized WebDriver getDriver() {
	return tdriver.get();
}


public static void moveMouseTo(WebElement element) {
	action=new Actions(driver);
	action.moveToElement(element).build().perform();
}

public static void dragAndDrop(WebElement source,int xOffset,int yOffset) {
	action=new Actions(driver);
	action.moveToElement(source).build().perform();
	action.dragAndDropBy(source, xOffset, yOffset).build().perform();
}

public static void dragAndDrop(WebElement source,WebElement target) {
	action=new Actions(driver);
	action.moveToElement(source).build().perform();
	action.dragAndDrop(source, target).build().perform();
}

public static void waitForVisibilityOfElement(WebElement element) {
	wait=new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.visibilityOf(element));
}
public static void waitForElementToBeClickable(WebElement element) {
	wait=new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.elementToBeClickable(element));
}
public static void waitForInvisibilityOfElement(WebElement element) {
	wait=new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.invisibilityOf(element));
}
public static void javaScriptClick(WebElement element) throws Exception {
	try {
		if (element.isEnabled() && element.isDisplayed()) {
			System.out.println("Clicking on element with using java script click");

			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
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
