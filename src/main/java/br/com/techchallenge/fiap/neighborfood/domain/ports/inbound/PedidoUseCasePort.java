/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.domain.ports.inbound;

import br.com.techchallenge.fiap.neighborfood.adapters.inbound.request.PedidoRequest;
import br.com.techchallenge.fiap.neighborfood.adapters.inbound.response.AcompanhamentoResponse;
import br.com.techchallenge.fiap.neighborfood.domain.model.Item;

import java.util.Set;

public interface PedidoUseCasePort {

    Object menuOpcionaisExecute();

    AcompanhamentoResponse pedidoExecute(PedidoRequest pedido);

    AcompanhamentoResponse atualizarPedidoExecute(PedidoRequest pedido);

    void removeItensExecute(Set<Item> itens);
}
