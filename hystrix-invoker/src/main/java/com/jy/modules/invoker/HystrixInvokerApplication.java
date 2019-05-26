package com.jy.modules.invoker;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients(basePackages = {"com.jy.modules.boot.feign"})
//开启Hystrix控制台
@EnableHystrixDashboard
@ComponentScan(basePackages = {"com.jy.modules"})
public class HystrixInvokerApplication extends SpringBootServletInitializer {

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * spring cloud2 hystrix没有/actuator/hystrix.stream路径解决
     **/
    @Bean
    public ServletRegistrationBean getServlet(){

        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();

        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);

        registrationBean.setLoadOnStartup(1);

        registrationBean.addUrlMappings("/actuator/hystrix.stream");

        registrationBean.setName("HystrixMetricsStreamServlet");

        return registrationBean;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HystrixInvokerApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(HystrixInvokerApplication.class, args);
    }

}
