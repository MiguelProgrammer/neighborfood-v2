/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.application.usecase.order.acompanhachain.status;

import br.com.techchallenge.fiap.neighborfood.adapters.inbound.response.AcompanhamentoResponse;
import br.com.techchallenge.fiap.neighborfood.application.gateways.PedidoGateway;
import br.com.techchallenge.fiap.neighborfood.application.usecase.order.acompanhachain.AcompanhamentoChain;
import br.com.techchallenge.fiap.neighborfood.application.usecase.order.acompanhachain.status.anemic.AcompanhamentoChainRecebido;
import br.com.techchallenge.fiap.neighborfood.config.exception.PedidoException;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.enums.Status;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.pedido.Pedido;

import java.util.Date;


public class AcompanhamentoUseCase {

    private final PedidoGateway pedidoGateway;
    private final AcompanhamentoChain statusPedidoChain;

    public AcompanhamentoUseCase(PedidoGateway pedidoGateway, AcompanhamentoChain statusPedidoChain) {
        this.pedidoGateway = pedidoGateway;
        this.statusPedidoChain = statusPedidoChain;
    }

    public AcompanhamentoResponse getOrderStatus(Long idPedido) {

        Pedido pedido;
        try {
            pedido = pedidoGateway.findById(idPedido);

            if (pedido.getStatus().equals(Status.EM_PREPARACAO)) {
                this.pedidoStatus(idPedido, Status.PRONTO);
                pedido.setStatus(Status.PRONTO);
            } else if (pedido.getStatus().equals(Status.PRONTO)) {
                this.fluxoStatusPedido(idPedido, Status.FINALIZADO);
                pedido.setStatus(Status.FINALIZADO);
            }
        } catch (Exception ex) {
            throw new PedidoException("Pedido não encontrado!");
        }
        return pedidoGateway.pedido(pedido);
    }

    public String smsExecute(Status status) {
        return new AcompanhamentoChainRecebido(statusPedidoChain).sms(status);
    }

    public void fluxoStatusPedido(Long idPedido, Status status) {

        Pedido pedidoDTO = pedidoGateway.findById(idPedido);
        pedidoDTO.setStatus(status);

        if (pedidoDTO.getStatus().equals(status.FINALIZADO)) {
            pedidoDTO.setDataPedidoFim(new Date());
        }

        pedidoGateway.commitUpdates(pedidoDTO.domainFromEntity());
        System.out.println(this.smsExecute(pedidoDTO.getStatus()));
    }


    public void pedidoStatus(Long idPedido, Status Status) {
        Pedido pedidoDTO = pedidoGateway.findById(idPedido);
        pedidoDTO.setStatus(Status);
        Pedido pedidoDTO1 = pedidoGateway.commitUpdates(pedidoDTO.domainFromEntity());
        System.out.println(this.smsExecute(pedidoDTO1.getStatus()));
    }

}
