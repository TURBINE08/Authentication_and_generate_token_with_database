package com.example.demo.payloads;

import org.springframework.stereotype.Component;

@Component
public class JwtRequest 
{ 

	//eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJEdXJnZXNoIiwiZXhwIjoxNjc5OTY5MzkwLCJpYXQiOjE2Nzk5MzMzOTB9.-vox8eP_TRhvsa9QO1kIlFN-dZfyUoKPs0E43HwnPSNYbtPoqtkbX9_gvI4wsaumBnl7eJGoyg-_Fewh-qR5JA

//	private  String username;
//	private  String password;
	
	private  String username = "Durgesh";
	private  String password = "Durgesh@123";
	 
	 
	 public JwtRequest()
	 {
		 
	 }
	 
	 public JwtRequest(String username, String password)
	 {
		 this.username = username;
		 this.password = password;
	 }

	public String getUsername() {
		
		System.err.println("*****1******");
		return username;
	}

	public void setUsername(String username) {
		System.err.println("*****2******");
		this.username = username;
	}

	public String getPassword() {
		System.err.println("*****3******");
		return password;
	}

	public void setPassword(String password) {
		System.err.println("*****4******");
		this.password = password;
	}
	 
	 @Override
	public String toString()
	{
	return "JwtRequest{" +
				"username'=" + username+'\''+
				",password='"+password+'\''+
				'}';
	}




}
