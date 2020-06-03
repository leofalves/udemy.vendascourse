package com.github.leofalves.udemy.vendascourse.rest.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.github.leofalves.udemy.vendascourse.domain.entity.Usuario;
import com.github.leofalves.udemy.vendascourse.exception.SenhaInvalidaException;
import com.github.leofalves.udemy.vendascourse.rest.dto.CredenciaisDTO;
import com.github.leofalves.udemy.vendascourse.rest.dto.TokenDTO;
import com.github.leofalves.udemy.vendascourse.rest.dto.UsuarioDTO;
import com.github.leofalves.udemy.vendascourse.security.jwt.JwtService;
import com.github.leofalves.udemy.vendascourse.service.Impl.UsuarioServiceImpl;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/usuarios")
@RestController
@RequiredArgsConstructor
public class UsuarioController {

	private final UsuarioServiceImpl service;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioDTO salvar(@RequestBody @Valid Usuario usuario) {
		// #TODO Criar DTO para retornar sem a senha
		String senhaCripto = passwordEncoder.encode(usuario.getPassword());
		usuario.setPassword(senhaCripto);
		return this.converter(service.salvar(usuario));
	}
	
	public UsuarioDTO converter(Usuario usuario) {
		return UsuarioDTO.builder()
				.username(usuario.getUsername())
				.id(usuario.getId())
				.admin(usuario.isAdmin())
				.build();
	}
	
	@PostMapping("/auth")
	public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais) {
		try {
			Usuario usuario = Usuario.builder()
										.username(credenciais.getUsername())
										.password(credenciais.getPassword())
										.build();
			
			UserDetails usuarioAutenticado = service.autenticar(usuario);
			String token = jwtService.gerarToken(usuario);
			return new TokenDTO(usuario.getUsername(), token);
			
		} catch (UsernameNotFoundException | SenhaInvalidaException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}		
	}
}
