/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.application.gateways;

import br.com.techchallenge.fiap.neighborfood.adapters.inbound.response.AcompanhamentoResponse;

import java.util.List;

public interface AdminGateway {
    List<AcompanhamentoResponse> listaPedidos(Long idAdmin);
}
