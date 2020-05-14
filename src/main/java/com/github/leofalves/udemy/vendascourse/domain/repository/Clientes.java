package com.github.leofalves.udemy.vendascourse.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.leofalves.udemy.vendascourse.domain.entity.Cliente;

public interface Clientes extends JpaRepository<Cliente, Integer> {

	List<Cliente> findByNomeLike(String nome);
	
	/*
	 * Exemplos de outros Query Methods
	 * 
	 * 
	 * List<Cliente> findByNomeOrId(String nome, Integer id);
	 * 
	 * List<Cliente> findByNomeLikeOrId(String nome, Integer id);
	 * 
	 * List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);
	 * 
	 * Cliente findOneByNome(String nome)
	 * 
	 * boolean existsByNome(String nome)
	 * 
	 * */
	
	boolean existsByNome(String nome);
	
}
