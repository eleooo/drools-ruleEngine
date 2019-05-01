package com.jy.modules.boot.ribbon.client;

import java.util.List;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

/**
 * <br> 自定义的规则类
 * <br> 在自定义的规则类中,实现choose方法调用了ILoadBalancer的getAllServers方法，返回全部服务器，本例只返回了第一个服务器。
 *
 */
public class MyRule implements IRule {
	
	ILoadBalancer lb;
	
	public MyRule() {
	}
	
	public MyRule(ILoadBalancer lb) {
		this.lb = lb;
	}

	public Server choose(Object key) {
		// 获取全部的服务器
		List<Server> servers = lb.getAllServers();
		// 只返回第一个Server对象
		return servers.get(0);
	}

	public void setLoadBalancer(ILoadBalancer lb) {
		this.lb = lb;
	}

	public ILoadBalancer getLoadBalancer() {
		return this.lb;
	}
}
