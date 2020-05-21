package com.github.leofalves.udemy.vendascourse.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.leofalves.udemy.vendascourse.exception.RegraNegocioException;
import com.github.leofalves.udemy.vendascourse.rest.ApiErrors;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApplicationControllerAdvice {

	@ExceptionHandler(RegraNegocioException.class)
	public ApiErrors handleRegraNegocioException(RegraNegocioException ex) {
		String msg = ex.getMessage();
		return new ApiErrors(msg);
	}
}
