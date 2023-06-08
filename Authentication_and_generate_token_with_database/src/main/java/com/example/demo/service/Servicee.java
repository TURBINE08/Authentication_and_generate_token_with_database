package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;

import com.example.demo.entity.Userdetail;
import com.example.demo.payloads.UserDto;

public interface Servicee 
{
	UserDto createUser(UserDto userdto);
	
	UserDto updateUser(UserDto userdto);
	
	UserDto PostUser(UserDto userdto, Integer id);
	
//	UserDto getById(Integer id);
	
	List<UserDto>getById(Integer id);
	
	List<UserDto> getAll();
	
	void deleteById(Integer Id);

	void deleteAll();
	
	Boolean login(String Email, String Password);
	
	Userdetail getByEmail(String Email);
	
	Userdetail getByMbNumber(String MbNumber);
	
	Userdetail uppdateUser(Userdetail userdetail);
	
	Boolean existsByMbNumberAndOtp(String MbNumber, String Otp);
	
//	void deleteAll();
	
}
