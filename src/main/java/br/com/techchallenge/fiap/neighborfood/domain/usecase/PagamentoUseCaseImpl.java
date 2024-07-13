/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.domain.usecase;


import br.com.techchallenge.fiap.neighborfood.adapters.inbound.request.PedidoRequest;
import br.com.techchallenge.fiap.neighborfood.adapters.inbound.response.AcompanhamentoResponse;
import br.com.techchallenge.fiap.neighborfood.domain.model.Pagamento;
import br.com.techchallenge.fiap.neighborfood.domain.model.Pedido;
import br.com.techchallenge.fiap.neighborfood.domain.model.enums.Status;
import br.com.techchallenge.fiap.neighborfood.domain.ports.inbound.AcompanhamentoUseCasePort;
import br.com.techchallenge.fiap.neighborfood.domain.ports.inbound.PagamentoUseCasePort;
import br.com.techchallenge.fiap.neighborfood.domain.ports.outbound.PedidoUseCaseAdapterPort;

public class PagamentoUseCaseImpl implements PagamentoUseCasePort {

    private PedidoUseCaseAdapterPort pedidoUseCaseAdapterPort;
    private AcompanhamentoUseCasePort acompanhamentoUseCasePort;

    public PagamentoUseCaseImpl(PedidoUseCaseAdapterPort pedidoUseCaseAdapterPort, AcompanhamentoUseCasePort acompanhamentoUseCasePort) {
        this.pedidoUseCaseAdapterPort = pedidoUseCaseAdapterPort;
        this.acompanhamentoUseCasePort = acompanhamentoUseCasePort;
    }

    @Override
    public AcompanhamentoResponse pagamentoExecute(Pagamento pagamento) {

        Pedido pedidoDTO = pedidoUseCaseAdapterPort.findById(pagamento.getIdPedido());
        AcompanhamentoResponse response = new AcompanhamentoResponse();
        if (pedidoDTO != null) {

            pedidoUseCaseAdapterPort.salvaPagamento(pagamento.fromEntity(pagamento));

            System.out.println("Pagamento Aprovado!");

            try {

                pedidoDTO.setStatus(Status.EM_PREPARACAO);
                response.setPedidoRequest(
                        response.convertPedidoRequest(
                                pedidoUseCaseAdapterPort.commitUpdates(pedidoDTO.domainFromEntity())));
                System.out.println(acompanhamentoUseCasePort.smsExecute(pedidoDTO.getStatus()));
                response.setStatus(pedidoDTO.getStatus());
                response.setTotal(pedidoDTO.getTotal());

            } catch (RuntimeException ex) {
                System.err.println("Erro ao realizar pagamento => Pedido não encontrado!!!");
            }
        }
        return response;
    }
}
