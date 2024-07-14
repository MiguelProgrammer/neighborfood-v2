/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.application.gateways;

import br.com.techchallenge.fiap.neighborfood.core.domain.model.pedido.Produto;

import java.util.Set;

public interface ProdutoGateway {

    void repoemEstoque(Set<Produto> produtos);
    Produto findById(Long idProduto);
    void deleteById(Long idProduto);
    void deleteAll(Set<Produto> produtos);
    void deleteByNome(String nome);
}
