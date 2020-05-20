package com.github.leofalves.udemy.vendascourse.service;

import com.github.leofalves.udemy.vendascourse.domain.entity.Pedido;
import com.github.leofalves.udemy.vendascourse.rest.dto.PedidoDTO;

public interface PedidoService {
	
	Pedido save(PedidoDTO pedidoDTO);

}
