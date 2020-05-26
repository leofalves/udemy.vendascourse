package com.github.leofalves.udemy.vendascourse.domain.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nome", length = 100)
	@NotEmpty(message = "Nome do cliente é obrigatório")
	private String nome;
	
	@Column(name = "cpf", length = 11)
	@NotEmpty(message = "CPF é obrigatório")
	@CPF(message = "Entre com um CPF válido")
	private String cpf;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private Set<Pedido> pedidos;
	
	
	public Cliente(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
}
