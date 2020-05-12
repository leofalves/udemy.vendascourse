package com.github.leofalves.udemy.vendascourse;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class MyConfiguration {

	@Bean(name = "applicationName")
	public String applicationName() {
		return "Sistema de vendas do curso Udemy da classe MyConfiguration";
	}
	
	@Bean
	public CommandLineRunner executar() {
		return args -> {
			System.out.println("Rodando a Configuração de Desenvolvimento");
		};
	}
}
