package com.github.leofalves.udemy.vendascourse.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.leofalves.udemy.vendascourse.domain.entity.Cliente;

public interface Clientes extends JpaRepository<Cliente, Integer> {

	List<Cliente> findByNomeLike(String nome);

}
