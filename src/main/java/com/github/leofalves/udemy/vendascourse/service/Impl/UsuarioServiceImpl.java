package com.github.leofalves.udemy.vendascourse.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (!username.equals("USER01") ) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		
		return User
				.builder()
				.username("USER01")
				.password(encoder.encode("123456"))
				.roles("USER", "ADMIN")
				.build();
	}

}
