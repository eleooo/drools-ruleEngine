package com.jy.modules.boot.ribbon;

import java.util.List;

import com.netflix.client.ClientFactory;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.client.http.RestClient;

/**
 * <br> 在负载均衡器中，提供了Ping机制，每隔一段时间，会去Ping服务器，判断服务器是否存活。
 * <br> 使用了配置的方法来设置负载均衡器使用PingUrl，设置了每隔2秒就向两个服务器发起请求。
 *
 */
public class PingUrlConfigTest {

	public static void main(String[] args) throws Exception {
		// 设置请求的服务器
		ConfigurationManager.getConfigInstance().setProperty(
				"my-client.ribbon.listOfServers",
				"localhost:1000,localhost:1002");
		// 配置Ping处理类
		ConfigurationManager.getConfigInstance().setProperty(
				"my-client.ribbon.NFLoadBalancerPingClassName",
				PingUrl.class.getName());
		// 配置Ping时间间隔
		ConfigurationManager.getConfigInstance().setProperty(
				"my-client.ribbon.NFLoadBalancerPingInterval",
				2);		
		// 获取REST请求客户端
		RestClient client = (RestClient) ClientFactory
				.getNamedClient("my-client");
		Thread.sleep(6000);
		// 获取全部服务器
		List<Server> servers = client.getLoadBalancer().getAllServers();
		System.out.println(servers.size());
		// 输出状态
		for(Server s : servers) {
			System.out.println(s.getHostPort() + " 状态：" + s.isAlive());
		}
	}
}
