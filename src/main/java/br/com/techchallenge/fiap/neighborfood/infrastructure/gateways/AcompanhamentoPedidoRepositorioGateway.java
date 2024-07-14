/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.infrastructure.gateways;

import br.com.techchallenge.fiap.neighborfood.adapters.inbound.response.AcompanhamentoResponse;
import br.com.techchallenge.fiap.neighborfood.application.gateways.AcompanhamentoGateway;
import br.com.techchallenge.fiap.neighborfood.application.usecase.order.acompanhachain.AcompanhamentoChain;
import br.com.techchallenge.fiap.neighborfood.application.usecase.order.acompanhachain.status.anemic.AcompanhamentoChainRecebido;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.enums.Status;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.PedidoRepository;

public class AcompanhamentoPedidoRepositorioGateway extends AcompanhamentoChain
        implements AcompanhamentoGateway {

    private PedidoRepository pedidoRepository;
    private AcompanhamentoChain acompanhamentoChain;

    public AcompanhamentoPedidoRepositorioGateway(PedidoRepository pedidoRepository,
                                                  AcompanhamentoChain acompanhamentoChain) {
        this.pedidoRepository = pedidoRepository;
        this.acompanhamentoChain = acompanhamentoChain;
    }

    @Override
    public AcompanhamentoResponse getOrderStatus(Long idPedido) {
        return null; //pedidoRepository.getOrderStatus(idPedido);
    }


    @Override
    public String sms(Status status) {
        return new AcompanhamentoChainRecebido(acompanhamentoChain).sms(status);
    }


    @Override
    public void fluxoStatusPedido(Long idPedido, Status Status) {
        //acompanhamentoUseCasePort.pedidoStatusExecute(idPedido, Status);
    }


    @Override
    public void pedidoStatus(Long idPedido, Status Status) {
        //acompanhamentoUseCasePort.pedidoStatusExecute(idPedido, Status);
    }
}
