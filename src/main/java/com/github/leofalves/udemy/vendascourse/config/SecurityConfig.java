package com.github.leofalves.udemy.vendascourse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.github.leofalves.udemy.vendascourse.security.jwt.JwtAuthFilter;
import com.github.leofalves.udemy.vendascourse.security.jwt.JwtService;
import com.github.leofalves.udemy.vendascourse.service.Impl.UsuarioServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UsuarioServiceImpl usuarioService;
	
	@Autowired
	JwtService jwtService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public OncePerRequestFilter jwtFilter() {
		return new JwtAuthFilter(jwtService, usuarioService);
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioService)
			.passwordEncoder(this.passwordEncoder());
	}
	
	
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
				.antMatchers(HttpMethod.POST, "/api/usuarios/**")
					.permitAll()
				.antMatchers("/h2-console/**")
					.permitAll()
				.anyRequest()
					.authenticated()
				.and().headers().frameOptions().sameOrigin()	// PARA FUNCIONAR OS FRAMES DO CONSOLE DO BANCO H2
			.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // TORNA A APLICAÇÃO STATELESS
			.and()
				.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class); // ADICIONA O FILTRO INTERCEPTADOR ANTES DO FILTRO USERNAMEPASSWORD -- O FILTRO JWTFILTER INSERE O USUARIO DENTRO DO CONTEXTO
	}
}
