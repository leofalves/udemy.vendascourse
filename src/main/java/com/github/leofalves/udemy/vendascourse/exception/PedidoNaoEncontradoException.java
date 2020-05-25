package com.github.leofalves.udemy.vendascourse.exception;

import java.util.function.Supplier;

public class PedidoNaoEncontradoException extends RuntimeException {
	public PedidoNaoEncontradoException() {
		super("Pedido não encontrado");
	}

}
