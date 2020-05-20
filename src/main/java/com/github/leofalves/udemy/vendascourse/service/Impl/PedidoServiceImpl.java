package com.github.leofalves.udemy.vendascourse.service.Impl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.leofalves.udemy.vendascourse.domain.entity.Cliente;
import com.github.leofalves.udemy.vendascourse.domain.entity.ItemPedido;
import com.github.leofalves.udemy.vendascourse.domain.entity.Pedido;
import com.github.leofalves.udemy.vendascourse.domain.entity.Produto;
import com.github.leofalves.udemy.vendascourse.domain.repositories.Clientes;
import com.github.leofalves.udemy.vendascourse.domain.repositories.ItensPedido;
import com.github.leofalves.udemy.vendascourse.domain.repositories.Pedidos;
import com.github.leofalves.udemy.vendascourse.domain.repositories.Produtos;
import com.github.leofalves.udemy.vendascourse.exception.RegraNegocioException;
import com.github.leofalves.udemy.vendascourse.rest.dto.ItemPedidoDTO;
import com.github.leofalves.udemy.vendascourse.rest.dto.PedidoDTO;
import com.github.leofalves.udemy.vendascourse.service.PedidoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {
	
	// Com a annotation RequiredArgsConstructor do longbok, colocando os atributos como final, já serão gerados e injetados no construtor 
	private final Pedidos pedidosRepository;
	private final Clientes clientesRepository;
	private final Produtos produtosRepository;
	private final ItensPedido itensPedidoRepository;
	

	@Override
	@Transactional
	public Pedido save(PedidoDTO pedidoDTO) {
		
		Integer idCliente = pedidoDTO.getCliente();
		
		Cliente cliente = clientesRepository
				.findById(idCliente)
				.orElseThrow( () -> new RegraNegocioException("Código de cliente inválido: " + idCliente));
		
		Pedido pedido = new Pedido();
		pedido.setTotal(pedidoDTO.getTotal());
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(cliente);
		pedidosRepository.save(pedido);
		
		List<ItemPedido> list = convertItems(pedido, pedidoDTO.getItems());
		itensPedidoRepository.saveAll(list);
		
		pedido.setItens(list);
		
		return pedido;
	}
	
	public List<ItemPedido> convertItems(Pedido pedido, List<ItemPedidoDTO> items) {
		if (items.isEmpty()) {
			throw new RegraNegocioException("Nenhum item para o pedido");
		}
		
		return items
				.stream()
				.map( dto -> {
					Integer idProduto = dto.getProduto();
					Produto prod = produtosRepository.findById(idProduto)
							.orElseThrow( () ->  new RegraNegocioException("Produto não encontrado: " + idProduto));
					
					ItemPedido item = new ItemPedido();
					item.setQuantidade(dto.getQuantidade());
					item.setPedido(pedido);
					item.setProduto(prod);
					return item;					
				}).collect(Collectors.toList());
	}
}
