package com.github.leofalves.udemy.vendascourse.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesItensPedidoDTO {
	private String descricaoProduto;
	private BigDecimal precoUnitario;
	private Integer quantidade;

}
