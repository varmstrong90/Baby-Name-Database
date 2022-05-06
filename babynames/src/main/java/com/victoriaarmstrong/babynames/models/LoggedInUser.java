package com.victoriaarmstrong.babynames.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoggedInUser {
	
	@NotEmpty(message="You must provide your E-mail address to log in")
	@Email(message="Please enter a valid E-mail address")
	private String email;
	
	@NotEmpty(message="Password is required")
	@Size(min=8, max=200, message = "Password should be at least 8 characters long")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
}

