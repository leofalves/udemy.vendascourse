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
			.roles("USER");
			//.authorities("MANTER CLIENTE");
	}
	
	
	// AUTORIZAÇÃO
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
				.antMatchers("/api/clientes/**")
				.authenticated() 						// SOMENTE USUÁRIOS AUTENTICADOS
				//.hasRole("USER"); 					// USUÁRIOS AUTENTICADOS E COM A ROLE: USER
				//.hasAuthority("MANTER CLIENTE");		// USUÁRIOS AUTENTICADOS E COM A AUTHORITY: MANTER CLIENTE
				//.permitAll();							// PERMITIDO PARA TODOS (SEM SEGURANÇA)
			.and()
				//.formLogin("/meu-login.html"); 			// PODE TER UM FORMULARIO PERSONALIZADO OU O DEFAULT DO SPRING
				.formLogin();
		
			/*
			 * No formulário de login personalizado precisa de um form conforme abaixo:
			 * <form method="post">
			 * 		<input type="text" name="username">
			 * 		<input type="secret" name="password">
			 * 		<button type="submit">
			 * </form>
			 * 
			 * */
	
	}
}
