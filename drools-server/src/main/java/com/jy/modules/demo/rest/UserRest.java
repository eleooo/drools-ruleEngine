package com.jy.modules.demo.rest;

import com.alibaba.fastjson.JSONObject;
import com.jy.modules.demo.dto.UserDTO;
import com.jy.modules.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by apple on 2019/1/16.
 */
@RestController
public class UserRest {

    @Autowired
    private UserService userService;


    @RequestMapping("/hello")
    public String index() {
        return"Hello World";
    }


    @RequestMapping("/get")
    @ResponseBody
    public String get(UserDTO user) {
        UserDTO u=userService.getNameById(user);
        return JSONObject.toJSONString(u);
    }

}
