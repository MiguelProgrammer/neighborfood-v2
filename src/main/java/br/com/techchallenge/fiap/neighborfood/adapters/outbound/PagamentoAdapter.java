/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.adapters.outbound;

import br.com.techchallenge.fiap.neighborfood.adapters.inbound.response.AcompanhamentoResponse;
import br.com.techchallenge.fiap.neighborfood.domain.model.Pagamento;
import br.com.techchallenge.fiap.neighborfood.domain.ports.inbound.PagamentoUseCasePort;
import br.com.techchallenge.fiap.neighborfood.domain.ports.outbound.PagamentoUseCaseAdapterPort;
import org.springframework.stereotype.Component;

@Component
public class PagamentoAdapter implements PagamentoUseCaseAdapterPort {

    private PagamentoUseCasePort pagamentoUseCasePort;

    public PagamentoAdapter(PagamentoUseCasePort pagamentoUseCasePort) {
        this.pagamentoUseCasePort = pagamentoUseCasePort;
    }

    @Override
    public AcompanhamentoResponse pagamento(Pagamento pagamento) {
        return pagamentoUseCasePort.pagamentoExecute(pagamento);
    }
}
