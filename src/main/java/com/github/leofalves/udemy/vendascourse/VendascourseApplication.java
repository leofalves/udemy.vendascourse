package com.github.leofalves.udemy.vendascourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.leofalves.udemy.vendascourse.domain.entity.Cliente;
import com.github.leofalves.udemy.vendascourse.domain.repositories.Clientes;

@SpringBootApplication
public class VendascourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendascourseApplication.class, args);
	}

}
