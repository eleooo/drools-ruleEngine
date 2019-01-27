package com.jy.modules.thymeleaf.service;

import com.jy.modules.thymeleaf.dto.User;
import org.springframework.stereotype.Service;

@Service(value = "myService")
public class MyService {

	public User getUser(String name) {
		User u = new User();
		u.setUserName(name);
		u.setPassword("password");
		return u;
	}
}
