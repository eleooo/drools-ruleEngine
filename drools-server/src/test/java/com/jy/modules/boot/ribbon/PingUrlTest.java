package com.jy.modules.boot.ribbon;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * <br> 在负载均衡器中，提供了Ping机制，每隔一段时间，会去Ping服务器，判断服务器是否存活。
 * <br> 使用了代码的方法来设置负载均衡器使用PingUrl，设置了每隔2秒就向两个服务器发起请求。
 *
 */
public class PingUrlTest {

	private static Logger logger = LoggerFactory.getLogger(PingUrlTest.class);

	public static void main(String[] args) throws Exception {
		// 创建负载均衡器
		BaseLoadBalancer lb = new BaseLoadBalancer();
		// 添加服务器
		List<Server> servers = new ArrayList<Server>();
		// 8080端口连接正常
		servers.add(new Server("localhost", 1000));
		// 一个不存在的端口
		servers.add(new Server("localhost", 1002));
		lb.addServers(servers);
		// 设置IPing实现类
		lb.setPing(new PingUrl());
		// 设置Ping时间间隔为2秒
		lb.setPingInterval(2);
		Thread.sleep(6000);
		for(Server server : lb.getAllServers()) {
			logger.info("请求地址:{},状态:{}",server.getHostPort(),server.isAlive());
		}
	}

}
