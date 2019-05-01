package com.jy.modules.boot.ribbon;

import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.niws.client.http.RestClient;

/**
 * <br> 使用配置的方式
 * <br> Ribbon自带的负载规则：
 * <br> > RandomRule: 随机选择可用的服务器
 * <br> > RoundRobinRule: 系统默认的规则，通过简单地轮询服务列表来选择服务器
 * <br> > RetryRule: 含有重试的选择逻辑
 * <br> > ZoneAvoidanceRule: 该规则以区域、可用服务器为基础进行服务器选择。使用Zone对服务器进行分类，可以理解为机架或者机房
 *
 * <br> 让请求的的客户端使用我们定义的负载规则
 */
public class MyRuleConfigTest {

    public static void main(String[] args) throws Exception {

        // 设置请求的服务器
        ConfigurationManager.getConfigInstance().setProperty(
                "my-client.ribbon.listOfServers",
                "localhost:1001,localhost:1002");
        // 配置规则处理类
        ConfigurationManager.getConfigInstance().setProperty(
                "my-client.ribbon.NFLoadBalancerRuleClassName",
                RandomRule.class.getName());
        // 获取REST请求客户端
        RestClient client = (RestClient) ClientFactory
                .getNamedClient("my-client");
        // 创建请求实例
        HttpRequest request = HttpRequest.newBuilder().uri("/drools-ruleEngine/api/user/hello").build();
        // 发 送10次请求到服务器中
        for (int i = 0; i < 6; i++) {
            HttpResponse response = client.executeWithLoadBalancer(request);
            String result = response.getEntity(String.class);
            System.out.println(result);
        }
    }

}
