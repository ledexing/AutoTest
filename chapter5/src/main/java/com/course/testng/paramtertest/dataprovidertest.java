package com.course.testng.paramtertest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class dataprovidertest {
    @Test(dataProvider="kele")
    public void paramrettest (String name,int age){
        System.out.println("这个人叫"+name+"，年龄"+age+"岁");
    }
    @DataProvider(name ="kele")
    public Object[][] dataProvider(){
        Object[][] data =new Object[][] {
                {"kele1",3},
                {"kele2",4}
        };
        return data ;
    }
    @Test(dataProvider="keledata")
    public void paramrettest1 (String name,int age){
        System.out.println("这个人叫"+name+"，年龄"+age+"岁");
    }
    @Test(dataProvider="keledata")
    public void paramrettest2 (String name,int age){
        System.out.println("这个人叫"+name+"，年龄"+age+"岁");
    }
    @DataProvider(name ="keledata")
    public Object[][] dataProvider(Method method) {
        Object[][] data=null;
        if (method.getName().equals("paramrettest1")) {
                    data = new Object[][]{
                    {"kele3", 5},
                    {"kele4", 6}

            };
        } else if (method.getName().equals("paramrettest2")) {
                    data = new Object[][]{
                    {"kele1", 3},
                    {"kele2", 4}
            };

        }return data;
    }
}
