/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.application.gateways;

import br.com.techchallenge.fiap.neighborfood.adapters.inbound.response.AcompanhamentoResponse;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.enums.Status;

public interface AcompanhamentoGateway {

    AcompanhamentoResponse getOrderStatus(Long idPedido);
    String sms(Status Status);
    void fluxoStatusPedido(Long idPedido, Status Status);
    void pedidoStatus(Long idPedido, Status Status);
}
