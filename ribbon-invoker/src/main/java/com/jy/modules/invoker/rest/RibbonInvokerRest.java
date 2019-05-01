package com.jy.modules.invoker.rest;

import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * <br> 负载均衡测试 ribbon-loadBalanced
 * <br> RestTemplate负载均衡
 * <br> RestTemplate本是spring-web项目中的一个REST客户端，它遵循REST的设计原则，提供简单的API让我们去调用HTTP 服务。
 * <br> RestTemplate 本身不具有负载均衡的功能，加入＠LoadBalanced 注解后， RestTemplate实例就具有负载均衡的功能。
 * <br> 在SpringCloud中，使用＠LoadBalanced修饰的RestTemplate，
 * <br> 在Spring容器启动时，会为这些被修饰过的RestTemplate添加拦截器 ，拦截器中使用了LoadBalancerClient来处理请求，
 * <br> LoadBalancerClient本来就是 Spring封装的负载均衡客户端，通过这样间接处理，使得RestTemplate拥有了负载均衡的功能。
 */
@RestController
@Configuration
@RequestMapping(value = "/api/loadBalanced")
public class RibbonInvokerRest {

    @LoadBalanced
    //@MyLoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * <br> 浏览器访问的请求
     * <br> 负载均衡测试: 测试主页面
     * <br> 模块-drools-server＠com.jy.modules.demo.rest.UserRest#index()
     */
    @RequestMapping(value = "/router", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String router() {
        RestTemplate restTpl = getRestTemplate();
        // 根据名称调用服务
        String json = restTpl.getForObject("http://DROOLS-SERVER/drools-ruleEngine/api/user/hello",
                String.class);
        return json;
    }

    @Autowired
    private LoadBalancerClient loadBalancer;

    /**
     * <br> 负载均衡测试: 查找服务器实例
     */
    @RequestMapping(value = "/findServiceInstance", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceInstance findServiceInstance() {
        //查找服务器实例
        ServiceInstance si = loadBalancer.choose("drools-server");
        return si;
    }

    @Autowired
    private SpringClientFactory factory;

    /**
     * <br> 负载均衡测试: 输出默认配置
     * <br> 直接获取 Spring Cloud 默认环境中各个Ribbon的实现类
     * <br> 使用了SpringClientFactory， 通过该实例可获取各个默认的实现类以及配置 ，分别输出了默认配置以及drools-server配置
     * <br> 根据输出可知，drools-server客户端使用的负载规则类以及Ping类，是我们自定义的实现类.
     */
    @RequestMapping(value = "/defaultValue", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String defaultValue() {
        System.out.println("==== 输出默认配置：");
        // 获取默认的配置
        ZoneAwareLoadBalancer alb = (ZoneAwareLoadBalancer) factory
                .getLoadBalancer("default");
        System.out.println("IClientConfig: "
                + factory.getLoadBalancer("default").getClass().getName());
        System.out.println("IRule: " + alb.getRule().getClass().getName());
        System.out.println("IPing: " + alb.getPing().getClass().getName());
        System.out.println("ServerList: "
                + alb.getServerListImpl().getClass().getName());
        System.out.println("ServerListFilter: "
                + alb.getFilter().getClass().getName());
        System.out.println("ILoadBalancer: " + alb.getClass().getName());
        System.out.println("PingInterval: " + alb.getPingInterval());
        System.out.println("==== 输出drools-server配置：");
        // 获取 cloud-provider 的配置
        ZoneAwareLoadBalancer alb2 = (ZoneAwareLoadBalancer) factory
                .getLoadBalancer("drools-server");
        System.out.println("IClientConfig: "
                + factory.getLoadBalancer("drools-server").getClass()
                .getName());
        System.out.println("IRule: " + alb2.getRule().getClass().getName());
        System.out.println("IPing: " + alb2.getPing().getClass().getName());
        System.out.println("ServerList: "
                + alb2.getServerListImpl().getClass().getName());
        System.out.println("ServerListFilter: "
                + alb2.getFilter().getClass().getName());
        System.out.println("ILoadBalancer: " + alb2.getClass().getName());
        System.out.println("PingInterval: " + alb2.getPingInterval());
        return "";
    }

}
