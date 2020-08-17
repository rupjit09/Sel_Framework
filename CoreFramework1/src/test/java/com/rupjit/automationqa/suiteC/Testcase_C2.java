package com.rupjit.automationqa.suiteC;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.rupjit.automationqa.pages.PageSuiteC;
import com.rupjit.automationqa.util.ErrorUtil;
import com.rupjit.automationqa.util.TestUtil;

public class Testcase_C2 extends PageSuiteC{
	static boolean skip_testdata=false;
	static boolean fail_testdata=false;
	static boolean isTestcasePass=true;
	//Check runmode of testccase
		@BeforeTest
		public void checkTestcaseSkip() {
			log.debug("Checking the runmode of TestCase "+this.getClass().getSimpleName());
			if(!TestUtil.isCaseRunnable(Csuitexls, this.getClass().getSimpleName())) {
				TestUtil.dataSetResult(Csuitexls,"Testcases", TestUtil.getRowNum(Csuitexls, this.getClass().getSimpleName()),"SKIP");
				log.debug("Skipping TestCase as runmode is No for testcase "+this.getClass().getSimpleName());
				throw new SkipException("Runmode is set to no for TestCase "+this.getClass().getSimpleName());
			}
		}
	@Test
	public void testcasesC2() {
		log.debug("Executing testcaseC2");
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
			TestUtil.dataSetResult(Csuitexls,"Testcases", TestUtil.getRowNum(Csuitexls, this.getClass().getSimpleName()),"PASS");
		}else
			TestUtil.dataSetResult(Csuitexls,"Testcases", TestUtil.getRowNum(Csuitexls, this.getClass().getSimpleName()),"FAIL");

	}
}
