package com.course.testng.paramtertest;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class paramter {
  @Test
  @Parameters({"name","age"})
   public void paramrettest (String name,int age){
    System.out.println("这个人叫"+name+"，年龄"+age+"岁");
   }
}
