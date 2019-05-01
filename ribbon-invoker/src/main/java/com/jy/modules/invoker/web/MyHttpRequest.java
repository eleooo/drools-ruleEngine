package com.jy.modules.invoker.web;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;

/**
 * 自定义的请求类，用于转换URI
 */
public class MyHttpRequest implements HttpRequest {
	
	private HttpRequest sourceRequest;
	
	public MyHttpRequest(HttpRequest sourceRequest) {
		this.sourceRequest = sourceRequest;
	}

	public HttpHeaders getHeaders() {
		return sourceRequest.getHeaders();
	}

	public HttpMethod getMethod() {
		return sourceRequest.getMethod();
	}

	@Override
	public String getMethodValue() {
		return null;
	}

	/**
	 * 将URI转换
	 */
	public URI getURI() {
		try {
			URI newUri = new URI("http://localhost:1002/drools-ruleEngine/api/user/hello");
			return newUri;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sourceRequest.getURI();
	}
}
