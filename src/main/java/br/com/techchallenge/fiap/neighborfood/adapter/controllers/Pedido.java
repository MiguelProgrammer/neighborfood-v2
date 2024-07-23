/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.adapter.controllers;

import br.com.techchallenge.fiap.neighborfood.adapter.inbound.PedidoRequest;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.AcompanhamentoResponseDTO;
import br.com.techchallenge.fiap.neighborfood.core.usecase.pedido.PedidoUseCase;
import org.springframework.stereotype.Component;

@Component
public class Pedido {

    private final PedidoUseCase pedidoUseCase;

    public Pedido(PedidoUseCase pedidoUseCase) {
        this.pedidoUseCase = pedidoUseCase;
    }

    public Object menu() {
        return pedidoUseCase.menuOpcionais();
    }

    public AcompanhamentoResponseDTO pedido(PedidoRequest pedidoRequest) {
        return pedidoUseCase.pedido(pedidoRequest);
    }

    public AcompanhamentoResponseDTO atualiza(PedidoRequest pedidoRequest) {
        return pedidoUseCase.atualizarPedido(pedidoRequest);
    }
}
