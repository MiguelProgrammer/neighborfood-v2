/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order;

import br.com.techchallenge.fiap.neighborfood.core.domain.model.enums.Categoria;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    Set<ProdutoEntity> findByCategoria(Categoria combo);

    //    @Modifying
//    @Query("delete from ProdutoEntity p where p.nome =:nome")
    void deleteByNome(@Param("nome") String nome);
}
