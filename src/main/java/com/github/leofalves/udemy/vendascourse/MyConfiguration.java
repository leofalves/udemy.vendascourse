package com.github.leofalves.udemy.vendascourse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

	@Bean(name = "applicationName")
	public String applicationName() {
		return "Sistema de vendas do curso Udemy";
	}
	
}
