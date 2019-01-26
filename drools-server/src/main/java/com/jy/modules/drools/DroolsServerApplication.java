package com.jy.modules.drools;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@MapperScan(value = {"com.jy.modules.*.dao"})
@ComponentScan(basePackages = {"com.jy.modules"})
public class DroolsServerApplication extends SpringBootServletInitializer {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)   {
        return application.sources(DroolsServerApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DroolsServerApplication.class, args);
    }
}

