package com.github.leofalves.udemy.vendascourse.rest;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiErrors {
	
	@Getter
	private List<String> errors;
	
	
	public ApiErrors(String msg) {
		this.errors = Arrays.asList(msg);
	}

}
