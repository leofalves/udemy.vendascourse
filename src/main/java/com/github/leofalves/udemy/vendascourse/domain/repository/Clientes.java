package com.github.leofalves.udemy.vendascourse.domain.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.leofalves.udemy.vendascourse.domain.entity.Cliente;

@Repository
public class Clientes {
	
	private static final String DELETE = "delete from cliente where id = (?)";
	private static String UPDATE = "update cliente set nome = (?) where id = (?)";
	private static String INSERT = "insert into cliente (nome) values (?)";
	private static String SELECT_ALL = "select * from cliente";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private EntityManager entityManager;
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		entityManager.persist(cliente);
		return cliente;
	}
	
	public Cliente atualizar(Cliente cliente) {
		jdbcTemplate.update(UPDATE, new Object[] {
						cliente.getNome(), 
						cliente.getId()
					});
		
		return cliente;
	}
	
	public void delete(Cliente cliente) {
		deletar(cliente.getId());
	}
	
	
	private void deletar(Integer id) {
		jdbcTemplate.update(DELETE, new Object[] {id});		
	}

	public List<Cliente> obterTodos(){
		return jdbcTemplate.query(SELECT_ALL, obterRowMapper());
	}
	
	public List<Cliente> obterPorNome(String nome) {
		return jdbcTemplate.query(SELECT_ALL.concat(" where nome like ?"),
									new Object[] {"%" + nome + "%"},
									obterRowMapper());
	}

	private RowMapper<Cliente> obterRowMapper() {
		return new RowMapper<Cliente>() {
			@Override
			public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("ID");
				String nome = rs.getString("NOME");
				return new Cliente(id, nome);
			}
		};
	}
}
