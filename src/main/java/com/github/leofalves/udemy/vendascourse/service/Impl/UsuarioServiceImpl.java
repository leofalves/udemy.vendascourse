package com.github.leofalves.udemy.vendascourse.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.leofalves.udemy.vendascourse.domain.entity.Usuario;
import com.github.leofalves.udemy.vendascourse.domain.repositories.UsuarioRepository;
import com.github.leofalves.udemy.vendascourse.exception.SenhaInvalidaException;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UsuarioRepository repository;
	
	public Usuario salvar(Usuario usuario) {
		return repository.save(usuario);
	}
	
	public UserDetails autenticar(Usuario usuario) {
		UserDetails user = loadUserByUsername(usuario.getUsername());
		boolean senhasBatem = encoder.matches(usuario.getPassword(),user.getPassword());
		if(senhasBatem) {
			return user;
		}
		throw new SenhaInvalidaException();
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario user = repository.findByUsername(username)
									.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
	
		
		String [] roles = user.isAdmin() ?
				new String[] {"ADMIN", "USER"} : new String[] {"USER"};
				
		return User
				.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.roles(roles)
				.build();
	}

}
