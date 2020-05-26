package com.github.leofalves.udemy.vendascourse.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
{
	"cliente": 1,
	"total": 150,
	"items": [
		{
			"produto": 1,
			"quantidade": 7
		}
	]
}
 * */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
	
	@NotNull(message = "Código do cliente é obrigatório")
	private Integer cliente;
	
	@NotNull(message = "Total do pedido é obrigatório")
	private BigDecimal total;
	
	
	private List<ItemPedidoDTO> items;
	

}
