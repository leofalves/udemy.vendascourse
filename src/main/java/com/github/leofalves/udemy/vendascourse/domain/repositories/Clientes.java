package com.github.leofalves.udemy.vendascourse.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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
	 * void deleteByNome(String nome)
	 * 
	 * */
	
	boolean existsByNome(String nome);
	
	@Query(value = " select c from Cliente c where c.nome like :nome ")
	List<Cliente> encontrarPorNome(@Param("nome") String nome);
	
	// Da pra usar SQL ao invés do HQL / JPQL
	@Query(value = " select * from cliente where nome like '%:nome%' ", nativeQuery = true)
	List<Cliente> encontrarPorNomeSQL(@Param("nome") String nome);
	
	@Query(" delete from Cliente c where c.nome = :nome ")
	@Modifying
	@Transactional
	void deletarPorNome(@Param("nome") String nome);
	
	@Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id ")
	Cliente findClienteFetchPedidos(@Param("id") Integer id);
	
}
