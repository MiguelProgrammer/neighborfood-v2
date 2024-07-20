/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.infrastructure.gateways;

import br.com.techchallenge.fiap.neighborfood.adapter.controllers.AcompanhamentoResponse;
import br.com.techchallenge.fiap.neighborfood.adapter.gateways.PagamentoGteway;
import br.com.techchallenge.fiap.neighborfood.core.domain.pagamento.Pagamento;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.PagamentoRepository;
import org.springframework.stereotype.Component;

@Component
public class PagamentoRepositoryGateway implements PagamentoGteway {

    private final PagamentoRepository pagamentoRepository;

    public PagamentoRepositoryGateway(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    @Override
    public AcompanhamentoResponse pagamento(Pagamento pagamento) {
        return null;//pagamentoRepository.save(pagamento);
    }
}
