/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.core.usecase.acompanhamento;

import br.com.techchallenge.fiap.neighborfood.adapter.gateways.PedidoGateway;
import br.com.techchallenge.fiap.neighborfood.config.exceptions.PedidoException;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.AcompanhamentoResponseDTO;
import br.com.techchallenge.fiap.neighborfood.core.domain.enums.Status;
import br.com.techchallenge.fiap.neighborfood.core.domain.pedido.Pedido;
import br.com.techchallenge.fiap.neighborfood.core.usecase.acompanhamento.acompanhachain.AcompanhamentoChain;
import br.com.techchallenge.fiap.neighborfood.core.usecase.acompanhamento.acompanhachain.status.anemic.AcompanhamentoChainRecebido;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AcompanhamentoUseCase {

    private final PedidoGateway pedidoGateway;
    private AcompanhamentoChain statusPedidoChain;

    public AcompanhamentoUseCase(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    public AcompanhamentoResponseDTO getOrderStatus(Long idPedido) {

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
