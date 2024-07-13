/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.domain.usecase;

import br.com.techchallenge.fiap.neighborfood.adapters.inbound.response.AcompanhamentoResponse;
import br.com.techchallenge.fiap.neighborfood.config.exception.PedidoException;
import br.com.techchallenge.fiap.neighborfood.domain.model.Pedido;
import br.com.techchallenge.fiap.neighborfood.domain.model.enums.Status;
import br.com.techchallenge.fiap.neighborfood.domain.ports.inbound.AcompanhamentoUseCasePort;
import br.com.techchallenge.fiap.neighborfood.domain.ports.outbound.PedidoUseCaseAdapterPort;
import br.com.techchallenge.fiap.neighborfood.domain.usecase.acompanhachain.AcompanhamentoChain;
import br.com.techchallenge.fiap.neighborfood.domain.usecase.acompanhachain.anemic.AcompanhamentoChainRecebidoImpl;

import java.util.Date;


public class AcompanhamentoUseCaseImpl implements AcompanhamentoUseCasePort {

    private PedidoUseCaseAdapterPort pedidoUseCaseAdapterPort;
    private AcompanhamentoChain statusPedidoChain;

    public AcompanhamentoUseCaseImpl(PedidoUseCaseAdapterPort pedidoUseCaseAdapterPort, AcompanhamentoChain statusPedidoChain) {
        this.pedidoUseCaseAdapterPort = pedidoUseCaseAdapterPort;
        this.statusPedidoChain = statusPedidoChain;
    }

    @Override
    public AcompanhamentoResponse getOrderStatusExecute(Long idPedido) {

        Pedido pedido = new Pedido();
        try {
            pedido = pedidoUseCaseAdapterPort.findById(idPedido);

            if (pedido.getStatus().equals(Status.EM_PREPARACAO)) {
                this.pedidoStatusExecute(idPedido, Status.PRONTO);
                pedido.setStatus(Status.PRONTO);
            } else if (pedido.getStatus().equals(Status.PRONTO)) {
                this.fluxoStatusPedidoExecute(idPedido, Status.FINALIZADO);
                pedido.setStatus(Status.FINALIZADO);
            }
        } catch (Exception ex) {
            throw new PedidoException("Pedido não encontrado!");
        }
        return pedidoUseCaseAdapterPort.pedido(pedido);
    }

    @Override
    public String smsExecute(Status status) {
        return new AcompanhamentoChainRecebidoImpl(statusPedidoChain).sms(status);
    }


    @Override
    public void fluxoStatusPedidoExecute(Long idPedido, Status status) {
        Pedido pedidoDTO = pedidoUseCaseAdapterPort.findById(idPedido);
        pedidoDTO.setStatus(status);
        if (pedidoDTO.getStatus().equals(Status.FINALIZADO)) {
            pedidoDTO.setDataPedidoFim(new Date());
        }
        pedidoUseCaseAdapterPort.commitUpdates(pedidoDTO.domainFromEntity());

        System.out.println(this.smsExecute(pedidoDTO.getStatus()));
    }


    @Override
    public void pedidoStatusExecute(Long idPedido, Status status) {
        Pedido pedidoDTO = pedidoUseCaseAdapterPort.findById(idPedido);
        pedidoDTO.setStatus(status);
        Pedido pedidoDTO1 = pedidoUseCaseAdapterPort.commitUpdates(pedidoDTO.domainFromEntity());
        System.out.println(this.smsExecute(pedidoDTO1.getStatus()));
    }

}
