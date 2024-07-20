/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.adapter.gateways;

import br.com.techchallenge.fiap.neighborfood.core.domain.pedido.Produto;

import java.util.Set;

public interface EstoqueGateway {

    void repoemEstoque(Set<Produto> produtos);
    Produto findById(Long idProduto);
    void deleteById(Long idProduto);
    void deleteAll(Set<Produto> produtos);
    void deleteByNome(String nome);
    Object gerenciaEstoque(Long idAdmin);
}
