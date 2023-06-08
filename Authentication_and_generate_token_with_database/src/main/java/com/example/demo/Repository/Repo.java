package com.example.demo.Repository;

import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Userdetail;
import com.example.demo.payloads.UserDto;

public interface Repo extends JpaRepository<Userdetail, Integer>
{
	
	Boolean existsByEmailAndPassword(String Email, String Password);
	
	Boolean existsByFnameAndPassword(String Fname, String Password);
	
	Boolean existsByMbNumberAndOtp(String MbNumber, String Otp);
	
	Userdetail findByMbNumber(String MbNumber);

	Userdetail findByEmail(String Email);  

//	Userdetail getByEmail(String email); mbNumber otp

}
 