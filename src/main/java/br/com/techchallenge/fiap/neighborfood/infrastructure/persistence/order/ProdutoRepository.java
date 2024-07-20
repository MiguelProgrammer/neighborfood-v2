/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order;

import br.com.techchallenge.fiap.neighborfood.core.domain.enums.Categoria;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    Set<ProdutoEntity> findByCategoria(Categoria combo);
    void deleteByNome(String nome);
}
