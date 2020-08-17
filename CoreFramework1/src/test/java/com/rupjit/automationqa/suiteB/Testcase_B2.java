package com.rupjit.automationqa.suiteB;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.rupjit.automationqa.pages.PageSuiteB;
import com.rupjit.automationqa.util.ErrorUtil;
import com.rupjit.automationqa.util.TestUtil;

public class Testcase_B2  extends PageSuiteB{
	static boolean skip_testdata=false;
	static boolean fail_testdata=false;
	static boolean isTestcasePass=true;
	//Check runmode of testccase
			@BeforeTest
			public void checkTestcaseSkip() {
				log.debug("Checking the runmode of TestCase "+this.getClass().getSimpleName());
				if(!TestUtil.isCaseRunnable(Bsuitexls, this.getClass().getSimpleName())) {
					TestUtil.dataSetResult(Bsuitexls,"Testcases", TestUtil.getRowNum(Bsuitexls, this.getClass().getSimpleName()),"SKIP");
					log.debug("Skipping TestCase as runmode is No for testcase "+this.getClass().getSimpleName());
					throw new SkipException("Runmode is set to no for TestCase "+this.getClass().getSimpleName());
				}
			}
	@Test
	public void testcasesB2() {
		log.debug("Executing testcaseB2");
		String expectedValue="true";
		String ActualValue="false";

		try {
		Assert.assertTrue(expectedValue.equalsIgnoreCase(ActualValue), "Actual Value not matching with Expceted Value");
		}catch (Throwable t) {
			fail_testdata=true;
			ErrorUtil.addVerificationFailure(t);
		}
	}
	
	@AfterMethod
	public void reporterdataSetResult(){
	
		if(fail_testdata){
		isTestcasePass=false;
		}
		fail_testdata=false;
	}
	
	@AfterTest
	public void reportTestcaseResult(){
		if(isTestcasePass){
			TestUtil.dataSetResult(Bsuitexls,"Testcases", TestUtil.getRowNum(Bsuitexls, this.getClass().getSimpleName()),"PASS");
		}else
			TestUtil.dataSetResult(Bsuitexls,"Testcases", TestUtil.getRowNum(Bsuitexls, this.getClass().getSimpleName()),"FAIL");

	}
	
}
