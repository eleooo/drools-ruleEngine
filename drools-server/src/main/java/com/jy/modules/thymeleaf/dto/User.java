package com.jy.modules.thymeleaf.dto;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = -7803592448450756480L;
	private String userName;
	
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
