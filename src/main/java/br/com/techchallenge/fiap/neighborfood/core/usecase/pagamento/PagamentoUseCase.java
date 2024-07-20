/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.core.usecase.pagamento;


import br.com.techchallenge.fiap.neighborfood.adapter.controllers.AcompanhamentoResponse;
import br.com.techchallenge.fiap.neighborfood.core.domain.enums.Status;
import br.com.techchallenge.fiap.neighborfood.core.domain.pagamento.Pagamento;
import br.com.techchallenge.fiap.neighborfood.core.domain.pedido.Pedido;
import br.com.techchallenge.fiap.neighborfood.infrastructure.gateways.AcompanhamentoPedidoRepositorioGateway;
import br.com.techchallenge.fiap.neighborfood.infrastructure.gateways.PedidoRepositoryGateway;

public class PagamentoUseCase {

    private final PedidoRepositoryGateway pedidoRepositoryGateway;
    private final AcompanhamentoPedidoRepositorioGateway acompanhamentoGateway;

    public PagamentoUseCase(PedidoRepositoryGateway pedidoRepositoryGateway, AcompanhamentoPedidoRepositorioGateway acompanhamentoGateway) {
        this.pedidoRepositoryGateway = pedidoRepositoryGateway;
        this.acompanhamentoGateway = acompanhamentoGateway;
    }

    public AcompanhamentoResponse pagamento(Pagamento pagamento) {

        Pedido pedidoDTO = pedidoRepositoryGateway.findById(pagamento.getIdPedido());
        AcompanhamentoResponse response = new AcompanhamentoResponse();
        if (pedidoDTO != null) {

            pedidoRepositoryGateway.salvaPagamento(pagamento.fromEntity(pagamento));

            System.out.println("Pagamento Aprovado!");

            try {

                pedidoDTO.setStatus(Status.EM_PREPARACAO);
                response.setPedidoRequest(
                        response.convertPedidoRequest(
                                pedidoRepositoryGateway.commitUpdates(pedidoDTO.domainFromEntity())));
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
