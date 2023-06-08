package com.example.demo.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDto 
{
	
	private Integer id;
	private String fname;
	private String lname;
	private String email;
	private String password;
	private String mbNumber;
	private String otp;

}
