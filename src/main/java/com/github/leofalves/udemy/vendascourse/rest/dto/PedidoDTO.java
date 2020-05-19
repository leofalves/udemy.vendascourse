package com.github.leofalves.udemy.vendascourse.rest.dto;

import java.math.BigDecimal;
import java.util.List;

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

public class PedidoDTO {
	
	private Integer cliente;
	private BigDecimal total;
	private List<ItemPedidoDTO> items;
	

}
