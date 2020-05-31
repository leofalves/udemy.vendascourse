package com.github.leofalves.udemy.vendascourse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/// Para testes de encode/decode
	// https://bcrypt-generator.com/
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// AUTENTICAÇÃO
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.passwordEncoder(this.passwordEncoder())
			.withUser("USER01")
			.password(this.passwordEncoder().encode("123456"))
			.roles("ADMIN", "USER");
			//.authorities("MANTER CLIENTE");
	}
	
	
	// AUTORIZAÇÃO
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
				.antMatchers("/api/clientes/**")
					.hasAnyRole("USER","ADMIN")
				.antMatchers("/api/pedidos/**")
					.hasAnyRole("USER","ADMIN")
				.antMatchers("/api/produtos/**")
					.hasRole("ADMIN")
			.and()
				.httpBasic();		// PASSA A CREDENCIAL NO HEADER
	}
}
