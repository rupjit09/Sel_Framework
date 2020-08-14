package com.rupjit.automationqa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.rupjit.automationqa.util.Xls_Reader;

public class Testbase {
public static Logger log;
public static Properties config;
public static Xls_Reader suitexls;
public static Xls_Reader Asuitexls;
public static Xls_Reader Bsuitexls;
public static Xls_Reader Csuitexls;

@Test
public static void initialize() throws IOException {
	//initialize logger for logging
	log=Logger.getLogger("devpinoyLogger");
	
	//initialize config.properties file
	log.debug("Loading Config.properties file...");
	config=new Properties();
	FileInputStream ip=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\rupjit\\automationqa\\config\\config.properties");
	config.load(ip);
	log.debug("Config.properties file loaded");
	
	//Initializing XLS file
	log.debug("Loading XLS file...");
	suitexls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\main\\java\\com\\rupjit\\automationqa\\data\\Suite.xlsx");
	Asuitexls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\main\\java\\com\\rupjit\\automationqa\\data\\A suite.xlsx");
	Bsuitexls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\main\\java\\com\\rupjit\\automationqa\\data\\B suite.xlsx");
	Csuitexls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\main\\java\\com\\rupjit\\automationqa\\data\\C suite.xlsx");
	log.debug("XLS files loaded");
}
}
