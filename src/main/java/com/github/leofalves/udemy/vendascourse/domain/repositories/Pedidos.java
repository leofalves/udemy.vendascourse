package com.github.leofalves.udemy.vendascourse.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.leofalves.udemy.vendascourse.domain.entity.Cliente;
import com.github.leofalves.udemy.vendascourse.domain.entity.Pedido;

public interface Pedidos extends JpaRepository<Pedido, Integer>{
	List<Pedido> findByCliente(Cliente cliente);

}
