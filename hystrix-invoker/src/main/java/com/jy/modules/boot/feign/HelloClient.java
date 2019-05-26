package com.jy.modules.boot.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <br> 客户端接口
 * <br> 编写客户端接口，可以使用Spring的＠RequestMapping
 */
@FeignClient(name = "feign-provider", fallback = HelloClient.HelloClientFallback.class)
public interface HelloClient {
    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    String hello();
    @Component
    static class HelloClientFallback implements HelloClient {
        @Override
        public String hello() {
            return "error hello";
        }
    }
}
