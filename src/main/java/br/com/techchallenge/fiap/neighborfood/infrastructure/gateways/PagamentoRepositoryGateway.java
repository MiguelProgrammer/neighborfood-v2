/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.infrastructure.gateways;

import br.com.techchallenge.fiap.neighborfood.adapter.gateways.PagamentoGateway;
import br.com.techchallenge.fiap.neighborfood.adapter.presenter.AcompanhamentoResponse;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.AcompanhamentoResponseDTO;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.PagamentoDTO;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.PagamentoRepository;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.entities.PagamentoEntity;
import org.springframework.stereotype.Component;

@Component
public class PagamentoRepositoryGateway implements PagamentoGateway {

    private AcompanhamentoResponse mapper;
    private final PagamentoRepository pagamentoRepository;
    private AcompanhamentoPedidoRepositorioGateway acompanhamentoResponse;

    public PagamentoRepositoryGateway(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    @Override
    public AcompanhamentoResponseDTO pagamento(PagamentoDTO pagamento) {
        PagamentoEntity entity = new PagamentoEntity();
        entity.setIdPedido(pagamento.getIdPedido());
        entity.setPagou(pagamento.getPagou());
        PagamentoEntity save = pagamentoRepository.save(entity);
        return acompanhamentoResponse.getOrderStatus(save.getIdPedido());
    }
}
