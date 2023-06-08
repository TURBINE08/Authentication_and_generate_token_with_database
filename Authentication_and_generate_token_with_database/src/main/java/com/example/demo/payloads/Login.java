package com.example.demo.payloads;

import org.springframework.stereotype.Component;

@Component
public class Login 
{
	private String Email;
	private String Password;
	private String MbNumber;
	private String Otp;
	


	public Login(String email, String password, String mbNumber, String otp) {
		super();
		Email = email;
		Password = password;
		MbNumber = mbNumber;
		Otp = otp;
	}


	public String getOtp() {
		return Otp;
	}


	public void setOtp(String otp) {
		Otp = otp;
	}


	public String getMbNumber() {
		return MbNumber;
	}


	public void setMbNumber(String mbNumber) {
		MbNumber = mbNumber;
	}


	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	
	

}
