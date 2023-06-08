package com.example.demo.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Userdetail;
import com.example.demo.exception.ResourceNotFoundException;
@Service
public class JwtCustomUserDetailsService implements UserDetailsService
{
//	@Autowired
//	private Userdetail userdetaill;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		if(userName.equals("Durgesh"))
		{
			return new User("Durgesh","Durgesh@123",new ArrayList<>());
		}else
		{
			throw new UsernameNotFoundException("user not found");
//			throw new ResourceNotFoundException("user not found", userName, null);
		}
	}
	
	
	
//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		System.err.println("****1111*****"); 
//		System.err.println(email);
//		if(email.equals(userdetaill.getEmail()))
//		{
//			System.err.println("****2*****");
//
//			return new User(userdetaill.getEmail(),userdetaill.getPassword(),new ArrayList<>());
//		}else
//		{
//			System.err.println("****3*****");
//			throw new UsernameNotFoundException("user **not found");
//		}
//	}

}
