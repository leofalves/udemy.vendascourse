package com.github.leofalves.udemy.vendascourse.service;

import java.util.Optional;

import com.github.leofalves.udemy.vendascourse.domain.entity.Pedido;
import com.github.leofalves.udemy.vendascourse.rest.dto.PedidoDTO;

public interface PedidoService {
	
	Pedido save(PedidoDTO pedidoDTO);
	
	Optional<Pedido> obterPedidoCompleto(Integer id);

}
