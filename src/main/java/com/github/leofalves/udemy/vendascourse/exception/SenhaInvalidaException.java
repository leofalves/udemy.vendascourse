package com.github.leofalves.udemy.vendascourse.exception;

public class SenhaInvalidaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SenhaInvalidaException() {
		super("Senha inválida");
	}
}
