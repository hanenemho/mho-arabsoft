package org.sid.catservice.dto;

import lombok.Data;

public class UserDto {

	public UserDto() {
		// TODO Auto-generated constructor stub
	}
	
	private String username;
	
	private String password;
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
