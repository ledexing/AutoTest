package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Parameter;

@RestController
@Api(value = "/",description = "这是我全部的post方法")
@RequestMapping("/v1")
public class MyPostMethod {
    private Cookie cookie;
    @RequestMapping(value = "/login",method= RequestMethod.POST)
    @ApiOperation(value = "这是一个登入成功后返回cookies的post接口",httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "username1") String username,
                        @RequestParam(value = "password1") String password){
       if (username.equals("小可乐")&&password.equals("123666")){
        cookie =new Cookie("xiaokele","3");
        response.addCookie(cookie);
        return "登入成功";
        }else
            return "用户名或密码登入错误";
    }
    @RequestMapping(value = "/getuserlist",method= RequestMethod.POST)
    @ApiOperation(value = "这是一个登入成功后返回用户信息的post接口(参数放在body中)",httpMethod = "POST")
    public String getlist(HttpServletRequest request,
                        @RequestBody User u){
        User user = new User();
        Cookie[]  cookie2=request.getCookies();
        for (Cookie a : cookie2){
            if (a.getName().equals("login")
                    && a.getValue().equals("true")
                    && u.getName().equals("小可乐"))
                user.setAge("3岁");
                user.setName("小可乐");
                user.setSex("男");
             return user.toString() ;
        }return "参数校验不正确";
    }

}
