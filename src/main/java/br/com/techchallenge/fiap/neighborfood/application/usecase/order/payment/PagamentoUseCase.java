/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.application.usecase.order.payment;


import br.com.techchallenge.fiap.neighborfood.adapters.inbound.response.AcompanhamentoResponse;
import br.com.techchallenge.fiap.neighborfood.application.gateways.PedidoGateway;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.enums.Status;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.pagamento.Pagamento;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.pedido.Pedido;
import br.com.techchallenge.fiap.neighborfood.infrastructure.gateways.AcompanhamentoPedidoRepositorioGateway;

public class PagamentoUseCase {

    private final PedidoGateway pedidoGateway;
    private final AcompanhamentoPedidoRepositorioGateway acompanhamentoGateway;

    public PagamentoUseCase(PedidoGateway pedidoGateway, AcompanhamentoPedidoRepositorioGateway acompanhamentoGateway) {
        this.pedidoGateway = pedidoGateway;
        this.acompanhamentoGateway = acompanhamentoGateway;
    }

    public AcompanhamentoResponse pagamento(Pagamento pagamento) {

        Pedido pedidoDTO = pedidoGateway.findById(pagamento.getIdPedido());
        AcompanhamentoResponse response = new AcompanhamentoResponse();
        if (pedidoDTO != null) {

            pedidoGateway.salvaPagamento(pagamento.fromEntity(pagamento));

            System.out.println("Pagamento Aprovado!");

            try {

                pedidoDTO.setStatus(Status.EM_PREPARACAO);
                response.setPedidoRequest(
                        response.convertPedidoRequest(
                                pedidoGateway.commitUpdates(pedidoDTO.domainFromEntity())));
                System.out.println(acompanhamentoGateway.sms(pedidoDTO.getStatus()));
                response.setStatus(pedidoDTO.getStatus());
                response.setTotal(pedidoDTO.getTotal());

            } catch (RuntimeException ex) {
                System.err.println("Erro ao realizar pagamento => Pedido não encontrado!!!");
            }
        }
        return response;
    }
}
