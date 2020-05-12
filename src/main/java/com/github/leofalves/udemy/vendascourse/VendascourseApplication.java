package com.github.leofalves.udemy.vendascourse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.leofalves.udemy.vendascourse.domain.entity.Cliente;
import com.github.leofalves.udemy.vendascourse.domain.repository.Clientes;

@SpringBootApplication
public class VendascourseApplication {


	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes) {
		return args -> {
			Cliente c1= new Cliente();
			c1.setNome("Leo Fabiano");
			clientes.salvar(c1);
			
			Cliente c2 = new Cliente();
			c2.setNome("Elaine Silva");
			clientes.salvar(c2);
			
			List<Cliente> todosClientes = clientes.obterTodos();
			todosClientes.forEach(System.out::println);
			
			
		};
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(VendascourseApplication.class, args);
	}

}
