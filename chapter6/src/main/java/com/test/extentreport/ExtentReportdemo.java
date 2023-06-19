package com.test.extentreport;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
public class ExtentReportdemo {
    @Test
    public void testcase1(){
        Assert.assertEquals(1,1);
    }
    @Test
    public void testcase2(){
        Assert.assertEquals(2,1);
    }
    @Test
    public void testcase3(){
        Assert.assertEquals("sda","sda");
    }
    @Test
    public void testcase4(){
        Reporter.log("这是我用reporter.long打的日志");
        throw new RuntimeException("这还是我抛出的异常信息");
    }
}
