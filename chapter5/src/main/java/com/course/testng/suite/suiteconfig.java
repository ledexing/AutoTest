package com.course.testng.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class suiteconfig {
    @AfterSuite
    public void testCase2(){
        System.out.println("测试一下后面Suite");

    }
    @BeforeSuite
    public void testCase3(){
        System.out.println("测试一下前面Suite");
    }
}
