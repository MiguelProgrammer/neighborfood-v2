/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.application.gateways;

import br.com.techchallenge.fiap.neighborfood.adapters.inbound.response.AcompanhamentoResponse;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.pedido.Item;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.pedido.Pedido;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.pedido.Produto;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.enums.Categoria;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.entities.PagamentoEntity;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.entities.PedidoEntity;

import java.util.List;
import java.util.Set;

public interface PedidoGateway {

    Set<Produto> menuOpcionais(Categoria combo);
    AcompanhamentoResponse pedido(Pedido pedido);
    AcompanhamentoResponse atualizarPedido(Pedido pedido);
    Pedido commitUpdates(PedidoEntity pedidoEntity);
    void saveItens(Item item);
    void removeItens(Set<Item> itens);
    Set<Item> findAllById(Long id);
    Set<Item> findAllByIdPedido(Long id);
    Pedido findByIdPedido(Long id);
    Pedido findById(Long id);
    void salvaPagamento(PagamentoEntity entity);
    List<PedidoEntity> pedidosExecute();
}
