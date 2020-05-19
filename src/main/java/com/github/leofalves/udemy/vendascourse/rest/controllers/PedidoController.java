package com.github.leofalves.udemy.vendascourse.rest.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.leofalves.udemy.vendascourse.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
	private PedidoService service;
	
	public PedidoController(PedidoService service) {
		this.service = service;
	}
}
