/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.domain.ports.inbound;

import br.com.techchallenge.fiap.neighborfood.adapters.inbound.response.AcompanhamentoResponse;

import java.util.List;

public interface AdminUseCasePort {
    List<AcompanhamentoResponse> listaPedidosExecute(Long idAdmin);
}
