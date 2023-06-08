package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Userdetail;
import com.example.demo.payloads.JwtRequest;
import com.example.demo.payloads.JwtResponse;
import com.example.demo.payloads.Login;
import com.example.demo.serviceImpl.JwtCustomUserDetailsService;
import com.example.demo.util.Jwtutil;
//
//import com.example.demo.helper.JwtUtil;
//import com.example.demo.service.CustomUserDetailsService;

//import com.example.demo.model.JwtRequest;
//import com.example.demo.model.JwtResponse;
@RestController
public class JwtController  
{


	@Autowired
	private Userdetail userdetail;
	
	@Autowired
	private Jwtutil jwtUtil;
	
	@Autowired
	private Login login;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private  JwtCustomUserDetailsService customUserDetailsService;
//	@RequestMapping(value = "/token", method = RequestMethod.POST)
	
//	@PostMapping("/token") //** on for token
//	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception//** on for token
//	public String generateToken(Userdetail jwtRequestt) throws Exception
	public String generateToken(JwtRequest jwtRequest) throws Exception //*** off for token 

	{
//		System.err.println("*********"); 
//		System.out.println(jwtRequestt);
		try 
		{
//			System.err.println("*********");
			this.authenticationManager.authenticate
			(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
//			System.err.println("5555555555");
		}
		catch(UsernameNotFoundException e)
		{
//			System.err.println("*****66666****");
			e.printStackTrace();
			throw new Exception("Bad Credential");
		}
		System.err.println(jwtRequest.getUsername());
		UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
		
		String token = this.jwtUtil.generateToken(userDetails);
		System.err.println("JWT " + token);
		
//		return ResponseEntity.ok(new JwtResponse(token));// it convert json // ** on for token
		
		return token; //** off for token
		
		
		
		
	}
}
