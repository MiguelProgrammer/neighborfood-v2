/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.application.gateways;

import br.com.techchallenge.fiap.neighborfood.adapters.inbound.response.AcompanhamentoResponse;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.pagamento.Pagamento;

public interface PagamentoGteway {

    AcompanhamentoResponse pagamento(Pagamento pagamento);
}
