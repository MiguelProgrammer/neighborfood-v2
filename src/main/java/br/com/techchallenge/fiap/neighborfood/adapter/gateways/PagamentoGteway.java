/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.adapter.gateways;

import br.com.techchallenge.fiap.neighborfood.adapter.controllers.AcompanhamentoResponse;
import br.com.techchallenge.fiap.neighborfood.core.domain.pagamento.Pagamento;

public interface PagamentoGteway {

    AcompanhamentoResponse pagamento(Pagamento pagamento);
}
