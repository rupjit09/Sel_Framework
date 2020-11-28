package com.rupjit.automationqa.pages;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import com.rupjit.automationqa.base.TestBase;
import com.rupjit.automationqa.util.TestUtil;

public class PageSuiteC extends TestBase{
	@BeforeSuite
	//Runmode of 
	public void checkSuiteSkip() throws IOException {
		initialize();
		log.debug("Checking the runmode of SuiteC");
		if(!TestUtil.isSuiteRunnable(suitexls, "C Suite")) {
			log.debug("Skipping Suite C as runmode is No");
			throw new SkipException("Runmode is set to no for Suite C");
		}

	}
}
