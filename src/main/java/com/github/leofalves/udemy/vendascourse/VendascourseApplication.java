package com.github.leofalves.udemy.vendascourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VendascourseApplication {

	@GetMapping("/hello")
	public String HelloWorld() {
		return "Hello World";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(VendascourseApplication.class, args);
	}

}
