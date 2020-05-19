package com.github.leofalves.udemy.vendascourse.service.Impl;

import org.springframework.stereotype.Service;

import com.github.leofalves.udemy.vendascourse.domain.repositories.Pedidos;
import com.github.leofalves.udemy.vendascourse.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {
	
	private Pedidos repository;
	
	public PedidoServiceImpl(Pedidos repo) {
		this.repository = repo;
	}
}
