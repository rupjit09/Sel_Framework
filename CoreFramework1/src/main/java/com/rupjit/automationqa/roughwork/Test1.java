package com.rupjit.automationqa.roughwork;

import com.rupjit.automationqa.util.TestUtil;
import com.rupjit.automationqa.util.Xls_Reader;

public class Test1 {

	public static void main(String[] args) {

		Xls_Reader suitenames=new Xls_Reader(System.getProperty("user.dir")+"\\src\\main\\java\\com\\rupjit\\automationqa\\data\\Suite.xlsx");
		System.out.println("A suite runnable >> "+TestUtil.isSuiteRunnable(suitenames, "A suite"));
		System.out.println("B suite runnable >> "+TestUtil.isSuiteRunnable(suitenames, "B suite"));
		System.out.println("C suite runnable >> "+TestUtil.isSuiteRunnable(suitenames, "C suite"));

		Xls_Reader Asuite=new Xls_Reader(System.getProperty("user.dir")+"\\src\\main\\java\\com\\rupjit\\automationqa\\data\\A suite.xlsx");
		System.out.println("TestCase A1 runnable >> "+TestUtil.isCaseRunnable(Asuite, "TestCase A1"));

		
		Xls_Reader Csuite=new Xls_Reader(System.getProperty("user.dir")+"\\src\\main\\java\\com\\rupjit\\automationqa\\data\\C suite.xlsx");
		System.out.println("TestCase C1 runnable >> "+TestUtil.isCaseRunnable(Asuite, "TestCase C1"));

	}

}
