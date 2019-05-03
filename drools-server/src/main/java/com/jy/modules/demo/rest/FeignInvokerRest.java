package com.jy.modules.demo.rest;


import com.jy.modules.boot.feign.HelloClient;
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

    @Autowired
    private HelloClient helloClient;

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

    /**
     * <br> 在客户端接口中，分别使用了两个注解来调用同一个服务，接下来，在控制器中使用HelloClient 。
     */
    @RequestMapping(value = "/testContract", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String testContract() {
        String springResult = helloClient.springHello();
        System.out.println("使用 @RequestMapping 注解的接口返回结果：" + springResult);
        String myResult = helloClient.myHello();
        System.out.println("使用 @MyUrl 注解的接口返回结果：" + myResult);
        return "";
    }

    /**
     * 测试请求拦截器
     */
    @RequestMapping(value = "/testInterceptors", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String testInterceptors() {
        String springResult = helloClient.springHello();
        return springResult;
    }


}
