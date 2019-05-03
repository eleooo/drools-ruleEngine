package com.jy.modules.boot.contract;

import feign.Contract;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    /**
     * 返回一个自定义的注解翻译器
     * <br> 如果需要使用自己提供的Feign实现，可以在Spring的配置类中返回对应的Bean，
     */
    @Bean
    public Contract feignContract() {
        return new MyContract();
    }

    @Bean
    public RequestInterceptor getRequestInterceptorsA() {
        return new RequestInterceptor() {
            public void apply(RequestTemplate template) {
                System.out.println("这是第一个请求拦截器");
            }
        };
    }

    @Bean
    public RequestInterceptor getRequestInterceptorsB() {
        return new RequestInterceptor() {
            public void apply(RequestTemplate template) {
                System.out.println("这是第二个请求拦截器");
            }
        };
    }
}
