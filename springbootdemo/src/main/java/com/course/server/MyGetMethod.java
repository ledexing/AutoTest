package com.course.server;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.sun.net.httpserver.spi.HttpServerProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;



import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我全部的get方法")
public class MyGetMethod {
    @RequestMapping(value = "/getcookies",method= RequestMethod.GET)
    @ApiOperation(value = "这是一个返回cookies的get接口",httpMethod = "GET")
    public String getCookies(HttpServletResponse response){
        Cookie cookie=new Cookie("login","truele");
        response.addCookie(cookie);
        return "这是我的第一个接口";
    }
    /*
    开发一个需要验证cookies的get接口
    * */
    @RequestMapping(value = "/get/cookies",method= RequestMethod.GET)
    @ApiOperation(value = "这是一个入参要cookies的get接口",httpMethod = "GET")
    public String getHttpCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return "需要带上cookie请求过来";
        }
        for (Cookie a : cookies) {
            if (a.getName().equals("login") && a.getValue().equals("truele")) {
                return "cookies验证成功，进入页面";
            } else {
                return "cookie验证失败";
            }
        }
        return "什么情况";
    }
    /*
    开发一个带参数请求的get接口
    第一种url:ip/prot/set/param?&name=*&age=*
    获取商品信息
    */
    @RequestMapping(value = "/set/param",method= RequestMethod.GET)
    @ApiOperation(value = "这是一个带参数的方式1的get接口",httpMethod = "GET")
    public Map<String,Integer> setParam(@RequestParam String name,@RequestParam Integer age) {
        Map<String,Integer> mylist= new HashMap<>();
        mylist.put("小可乐",3);
        mylist.put("中可乐",4);
        mylist.put("大可乐",5);
        Map<String,Integer> mylist1= new HashMap<>();
        mylist1.put("芬达",99);
        if (!name.equals("可乐")&&age!=1 ){
            return mylist1;
        }
        return mylist;

    }
    /*
    开发一个带参数请求的get接口
    第一种url:ip/prot/set/param/name/age
    */
    @RequestMapping(value = "/set/param2/{name}/{age}")
    @ApiOperation(value = "这是一个带参数的方式2的get接口",httpMethod = "GET")
    public Map<String,Integer> setParam2(@PathVariable String name, @PathVariable Integer age) {
        Map<String,Integer> mylist2= new HashMap<>();
        mylist2.put("小可乐",3);
        mylist2.put("中可乐",4);
        mylist2.put("大可乐",5);
        return mylist2;

    }
    /*
   开发一个带参数请求的get还需验证cookies的接口
   第一种url:ip/prot/set/param?&name=*&age=*
   获取商品信息
   */
    @RequestMapping(value = "/set/with/param",method= RequestMethod.GET)
    @ApiOperation(value = "这是一个带参数同时验证cookies的get接口",httpMethod = "GET")
    public Map<String,Integer> setParam3(@RequestParam String name,@RequestParam Integer age,HttpServletRequest request) {
        Map<String,Integer> mylist= new HashMap<>();
        mylist.put("小可乐",3);
        mylist.put("中可乐",4);
        mylist.put("大可乐",5);
        Map<String,Integer> mylist1= new HashMap<>();
        mylist1.put("芬达",99);
        Map<String,Integer> mylist2= new HashMap<>();
        mylist2.put("雪碧",100);
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return mylist1;
        }
        for (Cookie a : cookies) {
            if (a.getName().equals("login") && a.getValue().equals("truele")&&name.equals("可乐")&&age==1 ) {
                    return mylist;
                } else {
                return mylist1;
            }
        }
        return mylist2;
    }
}
