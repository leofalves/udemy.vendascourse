package com.github.leofalves.udemy.vendascourse.rest.controllers;

import java.util.List;

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
import com.github.leofalves.udemy.vendascourse.domain.entity.Produto;
import com.github.leofalves.udemy.vendascourse.domain.repositories.Produtos;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	private Produtos produtos;
	
	public ProdutoController(Produtos produtos) {
		this.produtos = produtos;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto save(@RequestBody Produto produto) {
		return produtos.save(produto);
	}
	
	@GetMapping("/{id}")
	public Produto getProdutoById(@PathVariable Integer id) {
		return produtos.findById(id)
						.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
																		"Produto não encontrado ID: " + id));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id, @RequestBody Produto produto) {
		produtos.findById(id)
						.map(produtoExistente -> {
												produto.setId(produtoExistente.getId());
												produtos.save(produto);
												 return produto;
												})
						.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
																		"Produto não encontrado. ID: " + id));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		produtos.findById(id)
						.map(produto -> {
								produtos.delete(produto);
								return produto;
						}).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
																		"Produto não encontrado. ID: " + id));
	}
	
	
	@GetMapping
	public List<Produto> find(Produto filtro) {
		
		ExampleMatcher matcher = ExampleMatcher
									.matching()
									.withIgnoreCase()
									.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		
		Example example = Example.of(filtro, matcher);
		
		return produtos.findAll(example);
	}
}