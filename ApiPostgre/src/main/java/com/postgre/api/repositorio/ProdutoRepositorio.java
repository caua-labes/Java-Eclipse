package com.postgre.api.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.postgre.api.entidades.Produto;

@Repository
public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {

}
