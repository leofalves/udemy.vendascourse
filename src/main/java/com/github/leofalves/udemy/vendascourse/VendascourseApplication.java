package com.github.leofalves.udemy.vendascourse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
// POR DEFAULT JÁ FAZ O SCAN EM TODOS OS SUBPACOTES ABAIXO DE com.github.leofalves.udemy.vendascourse
@ComponentScan(basePackages= {
		"com.github.leofalves.udemy.vendascourse",					
		"com.github.leofalves.udemy.vendascourse.repository",
		"com.github.leofalves.udemy.vendascourse.service",
		"com.outrabiblioteca.projeto"})
@RestController
public class VendascourseApplication {

	@Value("${spring.application.name}")
	private String applicationName;
	
	@Cachorro
	private Animal animal;
	
	@Bean(name = "executarAnimal")
	public CommandLineRunner executar() {
		return args-> {
			this.animal.fazerBarulho();
		};
	}
	
	@GetMapping("/hello")
	public String HelloWorld() {
		return "Hello World from " + applicationName;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(VendascourseApplication.class, args);
	}

}
