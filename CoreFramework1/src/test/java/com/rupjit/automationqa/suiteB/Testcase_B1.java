package com.rupjit.automationqa.suiteB;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rupjit.automationqa.pages.PageSuiteB;
import com.rupjit.automationqa.util.ErrorUtil;
import com.rupjit.automationqa.util.TestUtil;

public class Testcase_B1 extends PageSuiteB{
	String dataSetRunmodes[]=null;
	static int count=-1;
	static boolean skip_testdata=false;
	static boolean fail_testdata=false;
	static boolean isTestcasePass=true;
	//Check runmode of testccase
		@BeforeTest
		public void checkTestcaseSkip() {
			log.debug("Checking the runmode of TestCase "+this.getClass().getSimpleName());
			if(!TestUtil.isCaseRunnable(Bsuitexls, this.getClass().getSimpleName())) {
				TestUtil.dataSetResult(Bsuitexls,"Testcases", TestUtil.getRowNum(Asuitexls, this.getClass().getSimpleName()),"SKIP");
				log.debug("Skipping TestCase as runmode is No for testcase "+this.getClass().getSimpleName());
				throw new SkipException("Runmode is set to no for TestCase "+this.getClass().getSimpleName());
			}
			dataSetRunmodes=TestUtil.getDataSetRunmodeTest(Bsuitexls, this.getClass().getSimpleName());
		}
	@Test(dataProvider="getTestData")
	public void testcasesB1(String d1,String d2,String d3,String d4,String d5) {
		//Check the runmode of testdata abd skip if set to No
		count++;
		if(!dataSetRunmodes[count].equalsIgnoreCase("y")) {
			skip_testdata=true;
			log.debug("Runmode of the dataset "+(count+1)+" is set to No, so skipping");
			throw new SkipException("Runmode of the dataset "+(count+1)+" is set to No, so skipping");
		}
		log.debug("Executing testcase "+this.getClass().getSimpleName());
		log.debug("Fetching Testdata for "+this.getClass().getSimpleName());
		log.debug(d1+"---"+d2+"---"+d3+"---"+d4+"---"+d5);
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
		if(skip_testdata)
			TestUtil.dataSetResult(Bsuitexls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail_testdata){
			TestUtil.dataSetResult(Bsuitexls, this.getClass().getSimpleName(), count+2, "FAIL");
			isTestcasePass=false;
		}
		else
			TestUtil.dataSetResult(Bsuitexls, this.getClass().getSimpleName(), count+2, "PASS");
			
		skip_testdata=false;
		fail_testdata=false;
	}
	
	@AfterTest
	public void reportTestcaseResult(){
		if(isTestcasePass){
			TestUtil.dataSetResult(Bsuitexls,"Testcases", TestUtil.getRowNum(Bsuitexls, this.getClass().getSimpleName()),"PASS");
		}else
			TestUtil.dataSetResult(Bsuitexls,"Testcases", TestUtil.getRowNum(Bsuitexls, this.getClass().getSimpleName()),"FAIL");

	}
	
	@DataProvider
	public Object[][] getTestData(){
		return TestUtil.getData(Bsuitexls, this.getClass().getSimpleName());
	}
}
