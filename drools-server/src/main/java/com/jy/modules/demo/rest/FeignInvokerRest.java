package com.jy.modules.demo.rest;


import com.jy.modules.boot.feign.Person;
import com.jy.modules.boot.feign.PersonClient;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Rest客户端Feign调用"}, description = "Rest客户端Feign调用")
@RestController
@RequestMapping(value = "/api/feignInvoker")
@Configuration
public class FeignInvokerRest {

    @Autowired
    private PersonClient personClient;

    @RequestMapping(value = "/invokeHello", method = RequestMethod.GET)
    public String invokeHello() {
        return personClient.hello();
    }

    @RequestMapping(value = "/router", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person router() {
        // 调用服务提供者的接口
        Person p = personClient.getPerson(2);
        return p;
    }
}
