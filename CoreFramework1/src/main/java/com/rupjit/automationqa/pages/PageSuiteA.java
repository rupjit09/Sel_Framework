package com.rupjit.automationqa.pages;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import com.rupjit.automationqa.base.TestBase;
import com.rupjit.automationqa.util.TestUtil;

public class PageSuiteA extends TestBase{

	@BeforeSuite
	//Runmode of 
	public void checkSuiteSkip() throws IOException, InterruptedException {
		initialize();
		log.debug("Checking the runmode of SuiteA");
		if(!TestUtil.isSuiteRunnable(suitexls, "A Suite")) {
			log.debug("Skipping Suite A as runmode is No");
			throw new SkipException("Runmode is set to no for Suite A");
		}

	}
}
