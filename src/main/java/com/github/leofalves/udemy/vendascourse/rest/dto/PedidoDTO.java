package com.github.leofalves.udemy.vendascourse.rest.dto;

import java.math.BigDecimal;
import java.util.List;

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
	
	private Integer cliente;
	private BigDecimal total;
	private List<ItemPedidoDTO> items;
	

}
