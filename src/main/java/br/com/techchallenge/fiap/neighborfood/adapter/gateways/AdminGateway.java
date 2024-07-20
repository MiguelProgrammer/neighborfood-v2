/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.adapter.gateways;

import br.com.techchallenge.fiap.neighborfood.adapter.controllers.AcompanhamentoResponse;

import java.util.List;

public interface AdminGateway {
    List<AcompanhamentoResponse> listaPedidos(Long idAdmin);
}
