package com.example.demo.jwtconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class MyConfig 
{
	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
}
