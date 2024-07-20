/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.adapter.gateways;

import br.com.techchallenge.fiap.neighborfood.adapter.controllers.AcompanhamentoResponse;
import br.com.techchallenge.fiap.neighborfood.core.domain.enums.Status;

public interface AcompanhamentoGateway {

    AcompanhamentoResponse getOrderStatus(Long idPedido);
    String sms(Status Status);
    void fluxoStatusPedido(Long idPedido, Status Status);
    void pedidoStatus(Long idPedido, Status Status);
}
