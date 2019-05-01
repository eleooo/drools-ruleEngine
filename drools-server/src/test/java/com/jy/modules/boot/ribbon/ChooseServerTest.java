package com.jy.modules.boot.ribbon;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.ArrayList;
import java.util.List;

/**
 * <br> 使用负载均衡来选择服务器
 * <br> Ribbon的负载均衡接口定义了服务器的操作，主要用于进行服务器的选择
 * <br> 以下代码使用了BaseLoadBalancer这个负载均衡，将两个服务器对象加入负载均衡服务器中，再调用6次chooseServer方法。
 * Created by apple on 2019/5/1.
 */
public class ChooseServerTest {
    public static void main(String[] args) {
        // 创建负载均衡器
        ILoadBalancer lb = new BaseLoadBalancer();
        // 添加服务器
        List<Server> servers = new ArrayList<Server>();
        servers.add(new Server("localhost", 8080));
        servers.add(new Server("localhost", 8081));
        lb.addServers(servers);
        // 进行6次服务器选择
        for(int i = 0; i < 6; i++) {
            Server s = lb.chooseServer(null);
            System.out.println(s);
        }
    }
}
