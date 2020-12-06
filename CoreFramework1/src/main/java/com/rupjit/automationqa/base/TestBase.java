package com.rupjit.automationqa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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


public static void initialize() throws IOException {
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
		driver = new ChromeDriver();
	}else if(browsername.equalsIgnoreCase("firefox")) {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	}
	eventDriver =new EventFiringWebDriver(driver);
	//Create object of WebEventListener class to register it with EventFiringWebDriver
	eventListener=new WebEventListener();
	eventDriver.register(eventListener);
	driver=eventDriver;
	
	driver.manage().window().maximize();
	driver.manage().timeouts().pageLoadTimeout(Long.parseLong(prop.getProperty("page_load_timeout")), TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(Long.parseLong(prop.getProperty("implicit_wait")), TimeUnit.SECONDS);
	driver.manage().deleteAllCookies();
	driver.get(prop.getProperty("url"));
	js=(JavascriptExecutor) driver;
	ngDriver=new NgWebDriver(js);
}


public static void moveMouseTo(WebElement element) {
	action=new Actions(driver);
	action.moveToElement(element).build().perform();
}

public static void dragAndDrop(WebElement source,int xOffset,int yOffset) {
	action=new Actions(driver);
	action.dragAndDropBy(source, xOffset, yOffset).build().perform();
}

public static void waitForVisibilityOfElement(WebElement element) {
	wait=new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.visibilityOf(element));
}
}
