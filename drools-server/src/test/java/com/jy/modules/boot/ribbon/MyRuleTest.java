package com.jy.modules.boot.ribbon;

import java.util.ArrayList;
import java.util.List;

import com.jy.modules.boot.ribbon.client.MyRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;

/**
 * <br> 使用编码的方式
 * <br> 让请求的的客户端使用我们定义的负载规则
 */
public class MyRuleTest {

	public static void main(String[] args) {
		// 创建负载均衡器
		BaseLoadBalancer lb = new BaseLoadBalancer();
		// 设置自定义的负载规则
		lb.setRule(new MyRule(lb));
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
