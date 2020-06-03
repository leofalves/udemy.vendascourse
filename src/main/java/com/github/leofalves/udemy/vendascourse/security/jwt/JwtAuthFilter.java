package com.github.leofalves.udemy.vendascourse.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.github.leofalves.udemy.vendascourse.service.Impl.UsuarioServiceImpl;

public class JwtAuthFilter extends OncePerRequestFilter{

	private JwtService jwtService;	
	private UsuarioServiceImpl usuarioService;
		
	public JwtAuthFilter(JwtService jwtService, UsuarioServiceImpl usuarioService) {
		this.jwtService = jwtService;
		this.usuarioService = usuarioService;
	}

	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response, 
									FilterChain filterChain) throws ServletException, IOException {
		
		String authorization = request.getHeader("Authorization");
		
		if(authorization != null && authorization.startsWith("Bearer")) {
			String token = authorization.split(" ")[1];
			boolean isValid = jwtService.tokenValid(token);
			
			if(isValid) {
				String username = jwtService.obterLoginUsuario(token);
				UserDetails user = usuarioService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken userAuth = new 
							UsernamePasswordAuthenticationToken(user,
																null, 
																user.getAuthorities());
				// diz ao usuerAuth que é uma autenticação Web
				userAuth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				//Joga o usuário userAuth para dentro do Contexto do Spring
				SecurityContextHolder.getContext().setAuthentication(userAuth);
				
			}
			
		}
		
		filterChain.doFilter(request, response);
	}

}
