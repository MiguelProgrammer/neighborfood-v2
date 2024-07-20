/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.infrastructure.gateways;

import br.com.techchallenge.fiap.neighborfood.adapter.controllers.AcompanhamentoResponse;
import br.com.techchallenge.fiap.neighborfood.adapter.gateways.AcompanhamentoGateway;
import br.com.techchallenge.fiap.neighborfood.core.domain.enums.Status;
import br.com.techchallenge.fiap.neighborfood.core.usecase.acompanhamento.acompanhachain.AcompanhamentoChain;
import br.com.techchallenge.fiap.neighborfood.core.usecase.acompanhamento.acompanhachain.status.anemic.AcompanhamentoChainRecebido;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.PedidoRepository;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.entities.PedidoEntity;

import java.util.Optional;

public class AcompanhamentoPedidoRepositorioGateway extends AcompanhamentoChain
        implements AcompanhamentoGateway {

    private PedidoRepository pedidoRepository;
    private AcompanhamentoChain acompanhamentoChain;

    public AcompanhamentoPedidoRepositorioGateway(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public AcompanhamentoResponse getOrderStatus(Long idPedido) {
        Optional<PedidoEntity> pedido = pedidoRepository.findById(idPedido);
        AcompanhamentoResponse acompanhamentoResponse = new AcompanhamentoResponse();
        return acompanhamentoResponse.pedidoEntityFromResponse(pedido.get());
    }


    @Override
    public String sms(Status status) {
        return new AcompanhamentoChainRecebido(acompanhamentoChain).sms(status);
    }


    @Override
    public void fluxoStatusPedido(Long idPedido, Status status) {
        Optional<PedidoEntity> pedido = pedidoRepository.findById(idPedido);
        pedido.get().setStatus(status);
        pedidoRepository.save(pedido.get());
    }


    @Override
    public void pedidoStatus(Long idPedido, Status Status) {
        //acompanhamentoUseCasePort.pedidoStatusExecute(idPedido, Status);
    }
}
