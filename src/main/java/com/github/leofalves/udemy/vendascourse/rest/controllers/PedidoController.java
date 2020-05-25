package com.github.leofalves.udemy.vendascourse.rest.controllers;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.github.leofalves.udemy.vendascourse.domain.entity.ItemPedido;
import com.github.leofalves.udemy.vendascourse.domain.entity.Pedido;
import com.github.leofalves.udemy.vendascourse.domain.enums.StatusPedido;
import com.github.leofalves.udemy.vendascourse.rest.dto.AtualizacaoStatusPedidoDTO;
import com.github.leofalves.udemy.vendascourse.rest.dto.InformacoesItensPedidoDTO;
import com.github.leofalves.udemy.vendascourse.rest.dto.InformacoesPedidoDTO;
import com.github.leofalves.udemy.vendascourse.rest.dto.PedidoDTO;
import com.github.leofalves.udemy.vendascourse.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
	private PedidoService service;
	
	public PedidoController(PedidoService service) {
		this.service = service;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Integer save(@RequestBody PedidoDTO pedidoDTO) {
		Pedido pedido = service.save(pedidoDTO);
		return pedido.getId();
	}
	
	@GetMapping("{id}")
	public InformacoesPedidoDTO getById(@PathVariable Integer id) {
		return service.obterPedidoCompleto(id)
				.map( p -> converter(p))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado. Id: " + id));				
	}
	
	@PatchMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateStatus(@PathVariable Integer id,
							 @RequestBody AtualizacaoStatusPedidoDTO dto) {
		
		String novoStatus = dto.getNovoStatus();
		service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
		
	}
	
	private InformacoesPedidoDTO converter (Pedido pedido) {
		return InformacoesPedidoDTO.builder()
			.codigo(pedido.getId())
			.dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
			.cpf(pedido.getCliente().getCpf())
			.nomeCliente(pedido.getCliente().getNome())
			.total(pedido.getTotal())
			.status(pedido.getStatus().name())
			.itens(converter(pedido.getItens()))
			.build();
	}
	
	private List<InformacoesItensPedidoDTO> converter(List<ItemPedido> itens){
		if (CollectionUtils.isEmpty(itens)) {
			return Collections.emptyList();
		}
		
		return itens.stream().map(
				item -> InformacoesItensPedidoDTO
						.builder()
						.descricaoProduto(item.getProduto().getDescricao())
						.precoUnitario(item.getProduto().getPreco())
						.quantidade(item.getQuantidade())
						.build()					
				).collect(Collectors.toList());
	}
}
