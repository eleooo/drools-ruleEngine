package com.jy.modules.boot.ribbon.client;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;

/**
 * <br> 实现自定义Ping
 * <br> 先实现IPing接口，然后再通过配置来设定具体的Ping实现类
 */
public class MyPing implements IPing {

	public boolean isAlive(Server server) {
		System.out.println("这是自定义Ping实现类：" + server.getHostPort());
		return true;
	}
}
