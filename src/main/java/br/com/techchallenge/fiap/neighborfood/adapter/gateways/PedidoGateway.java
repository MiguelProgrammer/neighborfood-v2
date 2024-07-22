/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.adapter.gateways;

import br.com.techchallenge.fiap.neighborfood.core.domain.dto.AcompanhamentoResponseDTO;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.PagamentoDTO;
import br.com.techchallenge.fiap.neighborfood.core.domain.enums.Categoria;
import br.com.techchallenge.fiap.neighborfood.core.domain.pedido.Item;
import br.com.techchallenge.fiap.neighborfood.core.domain.pedido.Pedido;
import br.com.techchallenge.fiap.neighborfood.core.domain.pedido.Produto;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.entities.PedidoEntity;

import java.util.List;
import java.util.Set;

public interface PedidoGateway {

    Set<Produto> menuOpcionais(Categoria combo);
    AcompanhamentoResponseDTO pedido(Pedido pedido);
    AcompanhamentoResponseDTO atualizarPedido(Pedido pedido);
    Pedido commitUpdates(PedidoEntity pedidoEntity);
    void saveItens(Item item);
    void removeItens(Set<Item> itens);
    Set<Item> findAllById(Long id);
    Set<Item> findAllByIdPedido(Long id);
    Pedido findByIdPedido(Long id);
    Pedido findById(Long id);
    void salvaPagamento(PagamentoDTO entity);
    List<PedidoEntity> pedidos();
}
