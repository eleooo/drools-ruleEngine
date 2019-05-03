package com.jy.modules.boot.feign;

import com.jy.modules.boot.contract.MyUrl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <br> 客户端接口
 * <br> 编写客户端接口，可以使用Spring的＠RequestMapping，或者是我们自定义的＠MyUrl注解
 */
@FeignClient(name = "feign-provider")
public interface HelloClient {

    @MyUrl(method = "GET", url = "/hello")
    String myHello();

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    String springHello();
}
