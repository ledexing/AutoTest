package com.course.testng;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class zhujieceshi {

    @Test
    public void testCase1() {
        System.out.println("测试一下");
    }

    @Test(enabled = false)
    public void testCase11() {
        System.out.println("测试一下不执行");
    }

    @AfterMethod
    public void testCase2() {
        System.out.println("测试一下后面");

    }

    @BeforeMethod
    public void testCase3() {
        System.out.println("测试一下前面");
    }

//    @Test(expectedExceptions = RuntimeException.class)
//    public void testCase4() {
//        System.out.println("测试异常，执行结果失败failed");
//    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testCase5() {
        System.out.println("测试异常，执行结果成功success");
        throw new RuntimeException();
    }

}
