package com.jy.modules.invoker.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

@WebFilter(urlPatterns = "/*", filterName = "hystrixFilter")
public class HystrixFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HystrixRequestContext context = HystrixRequestContext
				.initializeContext();
		try {
			chain.doFilter(request, response);
		} finally {
			context.shutdown();
		}
	}

	@Override
	public void destroy() {
	}
}
