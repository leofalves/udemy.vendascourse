package com.github.leofalves.udemy.vendascourse.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.leofalves.udemy.vendascourse.domain.entity.ItemPedido;

public interface ItensPedido extends JpaRepository<ItemPedido, Integer>{

}
