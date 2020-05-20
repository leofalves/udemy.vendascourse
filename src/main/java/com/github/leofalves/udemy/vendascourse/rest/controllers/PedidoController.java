package com.github.leofalves.udemy.vendascourse.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.leofalves.udemy.vendascourse.domain.entity.Pedido;
import com.github.leofalves.udemy.vendascourse.rest.dto.PedidoDTO;
import com.github.leofalves.udemy.vendascourse.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
	private PedidoService service;
	
	public PedidoController(PedidoService service) {
		this.service = service;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Integer save(@RequestBody PedidoDTO pedidoDTO) {
		Pedido pedido = service.save(pedidoDTO);
		return pedido.getId();
	}
}
