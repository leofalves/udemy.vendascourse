package com.github.leofalves.udemy.vendascourse.rest.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.github.leofalves.udemy.vendascourse.domain.entity.Cliente;
import com.github.leofalves.udemy.vendascourse.domain.repositories.Clientes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/clientes")
@Api("API de Clientes")
public class ClienteController {
	
	private Clientes clientes;
	
	public ClienteController(Clientes clientes) {
		this.clientes = clientes;
	}
	
	@GetMapping("/{id}")
	@ApiOperation("Obter detalhes de um cliente")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Cliente encontrado"),
			@ApiResponse(code = 404, message = "Cliente não encontrado")
	})
	public Cliente getClienteById(
								@PathVariable 
								@ApiParam(value = "Id do Cliente", example = "1222") Integer id) {
		return clientes.findById(id)
						.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
																		"Cliente não encontrado"));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Salva um novo cliente")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Cliente salvo com sucesso"),
			@ApiResponse(code = 404, message = "Erro de Validação")
	})
	public Cliente save(@RequestBody @Valid Cliente cliente) {
		return clientes.save(cliente);	
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		clientes.findById(id)
					.map(cliente -> {
										clientes.delete(cliente);
										return cliente;
									})
					.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, 
																"Cliente não encontrado. ID: " + id));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id, @RequestBody @Valid Cliente cliente) {
		
		clientes.findById(id)
				.map(clienteExistente -> {
											cliente.setId(clienteExistente.getId());
											clientes.save(cliente);
											return cliente;
										})
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, 
															"Cliente não encontrado. ID: " + id));		
	}
	
	@GetMapping
	public List<Cliente> find(Cliente filtro) {
		
		ExampleMatcher matcher = ExampleMatcher
									.matching()
									.withIgnoreCase()
									.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		
		Example example = Example.of(filtro, matcher);
		
		return clientes.findAll(example);
	}
}