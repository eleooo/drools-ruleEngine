package com.jy.modules.invoker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.jy.modules"})
public class RibbonInvokerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonInvokerApplication.class, args);
	}

}
