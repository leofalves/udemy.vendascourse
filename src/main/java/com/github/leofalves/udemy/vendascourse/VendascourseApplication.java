package com.github.leofalves.udemy.vendascourse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.leofalves.udemy.vendascourse.domain.entity.Cliente;
import com.github.leofalves.udemy.vendascourse.domain.entity.Pedido;
import com.github.leofalves.udemy.vendascourse.domain.repositories.Clientes;
import com.github.leofalves.udemy.vendascourse.domain.repositories.Pedidos;

@SpringBootApplication
public class VendascourseApplication {


	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes, @Autowired Pedidos pedidos) {
		return args -> {
			
			System.out.println("Salvando clientes");
			Cliente c1= new Cliente();
			c1.setNome("Leo Fabiano");
			clientes.save(c1);
			
			Cliente c2 = new Cliente();
			c2.setNome("Elaine Silva");
			clientes.save(c2);

			Pedido p = new Pedido(1, c2, LocalDate.now(), BigDecimal.valueOf(100));
			pedidos.save(p);
			
			Cliente cliente = clientes.findClienteFetchPedidos(c2.getId());
			System.out.println(cliente);
			System.out.println(cliente.getPedidos());
			
			System.out.println("Pedidos do cliente c2");
			List<Pedido> list = pedidos.findByCliente(c2);
			list.forEach(System.out::println);
			
			
			List<Cliente> todosClientes = clientes.findAll();
			todosClientes.forEach(System.out::println);
			
//			boolean existe = clientes.existsByNome("Elaine Silva" );
//			System.out.println("Existe cliente com nome Elaine Silva? " + existe);
			
//			
//			System.out.println("Atualizando clientes");
//			todosClientes.forEach(c -> {
//				c.setNome(c.getNome() + " Atualizado");
//				clientes.save(c);
//			});		
//			todosClientes = clientes.findAll();
//			todosClientes.forEach(System.out::println);
			
			//System.out.println("Obter por nome");
			//clientes.findByNomeLike("Atualizado").forEach(System.out::println);
			
//			System.out.println("Encontrar por nome com @Query");
//			List<Cliente> result = clientes.encontrarPorNome("Leo");
//			result.forEach(System.out::println);
//			
//			System.out.println("Encontrar por nome com @Query com SQL NATIVO");
//			List<Cliente> result2 = clientes.encontrarPorNome("Leo");
//			result2.forEach(System.out::println);
//			System.out.println("Tamanho da lista: " + result2.size());
//
//
//			clientes.deletarPorNome("Leo Fabiano");
			
//			System.out.println("Deletando clientes");
//			todosClientes.forEach(c -> {
//				clientes.delete(c);
//			});
//			
//			todosClientes = clientes.findAll();
//			if(todosClientes.isEmpty()) {
//				System.out.println("Todos clientes deletados");
//			}
//			else {
//				todosClientes.forEach(System.out::println);
//			}
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(VendascourseApplication.class, args);
	}

}
