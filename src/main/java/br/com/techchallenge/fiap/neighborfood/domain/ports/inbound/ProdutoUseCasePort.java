/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.domain.ports.inbound;

import br.com.techchallenge.fiap.neighborfood.domain.model.Produto;

import java.util.Set;

public interface ProdutoUseCasePort {

    Object gerenciaEstoqueExecute(Long idAmdin);

    Object repoemEstoqueExecute(Set<Produto> produtos);

    Object findById(Long idProduto);

    void deleteById(Long idProduto);

    void deleteAllExecute(Set<Produto> lista);
}
