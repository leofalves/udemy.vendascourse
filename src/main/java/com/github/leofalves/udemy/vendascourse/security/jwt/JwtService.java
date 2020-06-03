package com.github.leofalves.udemy.vendascourse.security.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import com.github.leofalves.udemy.vendascourse.VendascourseApplication;
import com.github.leofalves.udemy.vendascourse.domain.entity.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
	
	@Value("${security.jwt.expiracao}")
	private String expiracao;
	
	@Value("${security.jwt.chave-assinatura}")
	private String assinatura;
	
	public String gerarToken(Usuario usuario) {
		long longExp = Long.valueOf(expiracao);
		LocalDateTime dataHoraExp = LocalDateTime.now().plusMinutes(longExp);
		
		// JWT usa o Date para expiração
		Instant instant = dataHoraExp.atZone(ZoneId.systemDefault()).toInstant();
		Date data = Date.from(instant);
		
		/*
		HashMap<String, Object> claims = new HashMap<>();		
		claims.put("email", "user01@gmail.com");
		claims.put("roles", "ADMIN");
		*/
		return Jwts.builder()
					.setSubject(usuario.getUsername())
					.setExpiration(data)
					//.setClaims(claims)
					.signWith(SignatureAlgorithm.HS512, assinatura)
					.compact();
	}
	
	private Claims obterClaims (String token) throws ExpiredJwtException {
		return Jwts
				.parser()
				.setSigningKey(assinatura)
				.parseClaimsJws(token)
				.getBody();
	}
	
	public boolean tokenValid(String token) {
		try {
			Claims claims = this.obterClaims(token);
			Date data = claims.getExpiration();
			LocalDateTime dateTimeExpiracao = data
												.toInstant().atZone(ZoneId.systemDefault())
												.toLocalDateTime();
			return !LocalDateTime.now().isAfter(dateTimeExpiracao);
		}
		catch (Exception e) {
			return false;
		}
	}
	
	
	public String obterLoginUsuario(String token) throws ExpiredJwtException {
		return (String) obterClaims(token).getSubject();
	}
	
	
	// PARA TESTAR STANDALONE Run as springboot
	public static void main(String[] args) {
		ConfigurableApplicationContext contexto = SpringApplication.run(VendascourseApplication.class);
		JwtService service = contexto.getBean(JwtService.class);
		Usuario usuario = Usuario.builder()
									.username("USER01")
									.build();
		String token = service.gerarToken(usuario);
		System.out.println("Token JWT: " + token);
		
		
		
		boolean isTokenValid = service.tokenValid(token);
		System.out.println("Token está válido? " + isTokenValid);		
		System.out.println("Usuario: " + service.obterLoginUsuario(token));
	}
	
}
