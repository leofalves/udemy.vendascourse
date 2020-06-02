package com.github.leofalves.udemy.vendascourse;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import com.github.leofalves.udemy.vendascourse.domain.entity.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
	
	@Value("${security.jwt.expiracao}")
	private String expiracao;
	
	@Value("${security.jwt.chave-assinatura}")
	private String assinatura;
	
	public String gerarToken(Usuario usuario) {
		long longExp = Long.valueOf(this.expiracao);
		LocalDateTime dataHoraExp = LocalDateTime.now().plusMinutes(longExp);
		
		// JWT usa o Date para expiração
		Instant instant = dataHoraExp.atZone(ZoneId.systemDefault()).toInstant();
		Date data = Date.from(instant);
		
		return Jwts.builder()
					.setSubject(usuario.getUsername())
					.setExpiration(data)
					.signWith(SignatureAlgorithm.HS512, this.assinatura)
					.compact();
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
	}
	
}
