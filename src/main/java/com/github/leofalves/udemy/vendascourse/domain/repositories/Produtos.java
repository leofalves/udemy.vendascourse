package com.github.leofalves.udemy.vendascourse.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.leofalves.udemy.vendascourse.domain.entity.Produto;

public interface Produtos extends JpaRepository<Produto, Integer> {

}
