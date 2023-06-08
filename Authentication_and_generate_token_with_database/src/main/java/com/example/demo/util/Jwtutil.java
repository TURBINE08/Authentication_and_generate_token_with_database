package com.example.demo.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class Jwtutil 
{



//	private static final long = -2550185165626007488L;
	
	public static final long JWT_TOKEN_VALIDITY = 5*60*30;
	
	private String secret = "java";
	// Retrive username from jwt token
	public String GetUsernameFromToken(String token)
	{
		return getClaimFromToken(token, Claims::getSubject);
	}

	//Retrive expiration date from jwt token
	public Date getExpirationDateFromToken(String token)
	{
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	//it provide claim from token
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver)
	{
		final Claims claims =  getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	//it provide all claim from token
	private Claims getAllClaimsFromToken(String token)
	{
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	//check token is expired or not
	private Boolean isTokenExpired(String token)
	{
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	//generate token from user
	public String generateToken(UserDetails userDetails)
	{
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}
		
	
	//while creating the token
	//1. define claims of the token, like Issuer, Expiration, Subject, and the Id
	//2. siglgorith the jwt using the HS512 and secret key.
	//3. according to jws Compact serilozation
	//4. compacting of the JWT to a URL-safe string
	private String doGenerateToken(Map<String, Object> claims, String subject) 
	{
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
	
	
	public Boolean validateToken(String token, UserDetails userDetails)
	{
		final String username = GetUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}



}
