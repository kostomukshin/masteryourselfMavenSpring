package com.example.masteryourself.model;

import org.springframework.lang.NonNull;


public class SignUpForm {
    @NonNull
    private String username = "";

    @NonNull
    private String password = "";


    @NonNull
    private String passwordCheck = "";

    @NonNull
    private String role = "USER";
    
    @NonNull
    private String email;

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

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
