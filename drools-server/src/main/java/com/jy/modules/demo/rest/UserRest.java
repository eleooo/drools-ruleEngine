package com.jy.modules.demo.rest;

import com.alibaba.fastjson.JSONObject;
import com.jy.modules.boot.exception.DroolsException;
import com.jy.modules.demo.dto.UserDTO;
import com.jy.modules.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by apple on 2019/1/16.
 */
@Api(tags = {"用户管理"}, description = "用户管理")
@RestController
@RequestMapping(value = "/api/user")
public class UserRest {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "测试主页面", notes = "测试主页面")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        return "Hello World";
    }


    @ApiOperation(value = "获取用户信息", notes = "根据id来获取用户信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String get(UserDTO user) {
        UserDTO u = userService.getNameById(user);
        return JSONObject.toJSONString(u);
    }

    @ApiOperation(value = "测试异常处理", notes = "测试异常处理")
    @RequestMapping(value = "/testHandleException", method = RequestMethod.GET)
    public void testHandleException() {
        if (true) {
            throw new DroolsException("error", 1002);
        }
    }
}
