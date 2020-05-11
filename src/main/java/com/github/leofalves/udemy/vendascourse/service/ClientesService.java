package com.github.leofalves.udemy.vendascourse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.leofalves.udemy.vendascourse.model.Cliente;
import com.github.leofalves.udemy.vendascourse.repository.ClientesRepository;

@Service
public class ClientesService {

	private ClientesRepository clientesRespository;
	
	// INJEÇÃO DE DEPENDÊNCIA - NÃO PRECISA INSTANCIAR DIRETAMENTE
	// INJEÇÃO VIA CONSTRUTOR / PODE SER VIA METHOD SET
	@Autowired
	public ClientesService(ClientesRepository clientesRepository) {
		this.clientesRespository = clientesRepository;
	}
	
	public void salvar(Cliente cliente) {
		validarCliente(cliente);
		
		// ClientesRepository é uma dependência de ClientesService
		// NÃO CRIAR DESTA FORMA, INSTANCIANDO NA CLASSE - TEM QUE USAR A INJEÇÃO DE DEPENDENCIA
		//ClientesRepository clientesRepo = new ClientesRepository();
		//clientesRepo.persistir(cliente);
		
		
		// USANDO A INJEÇÃO DE DEPENDÊNCIA
		this.clientesRespository.persistir(cliente);
	}
	
	public void validarCliente(Cliente cliente) {
		// validar
	}
}
