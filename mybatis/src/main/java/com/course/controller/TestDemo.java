package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@Log4j
@Api(value = "v1",description = "这是我的第一个连数据库查询的接口")
@RequestMapping("v1")
public class TestDemo {
    //获取一个执行SQL的对象
    @Autowired
    private SqlSessionTemplate template;
    @RequestMapping(value ="/getUserCount",method = RequestMethod.GET)
    @ApiOperation(value = "这是一个获取用户注册数量的接口",httpMethod = "GET")
    public int getUserCount(){
       return template.selectOne("getUserCount");
    }

    @RequestMapping(value ="/addUser",method = RequestMethod.POST)
    @ApiOperation(value = "这是一个创建用户的接口",httpMethod = "POST")
    public int adduser(@RequestBody User user){
        return template.insert("addUser",user);
    }
    @RequestMapping(value ="/updateUser",method = RequestMethod.POST)
    @ApiOperation(value = "这是一个修改用户账户密码的接口",httpMethod = "POST")
    public int updateUser(@RequestBody User user){
        return template.update("updateUser",user);
    }
    @RequestMapping(value ="/deleteUser",method = RequestMethod.POST)
    @ApiOperation(value = "这是一个删除用户账户的接口",httpMethod = "POST")
    public int delUser(@RequestParam String name){
        return template.update("deleteUser",name);
    }
}
