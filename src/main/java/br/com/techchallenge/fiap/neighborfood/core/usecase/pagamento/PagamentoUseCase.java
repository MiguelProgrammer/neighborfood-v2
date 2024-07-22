/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.core.usecase.pagamento;


import br.com.techchallenge.fiap.neighborfood.adapter.gateways.AcompanhamentoGateway;
import br.com.techchallenge.fiap.neighborfood.adapter.gateways.PedidoGateway;
import br.com.techchallenge.fiap.neighborfood.adapter.presenter.AcompanhamentoResponse;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.AcompanhamentoResponseDTO;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.PagamentoDTO;
import br.com.techchallenge.fiap.neighborfood.core.domain.enums.Status;
import br.com.techchallenge.fiap.neighborfood.core.domain.pedido.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PagamentoUseCase {

    private final PedidoGateway pedidoGateway;
    private final AcompanhamentoGateway acompanhamentoGateway;

    public PagamentoUseCase(PedidoGateway pedidoGateway, AcompanhamentoGateway acompanhamentoGateway) {
        this.pedidoGateway = pedidoGateway;
        this.acompanhamentoGateway = acompanhamentoGateway;
    }

    public AcompanhamentoResponseDTO pagamento(PagamentoDTO pagamento) {

        Pedido pedidoDTO = pedidoGateway.findById(pagamento.getIdPedido());
        AcompanhamentoResponse response = new AcompanhamentoResponse();
        if (pedidoDTO != null) {

            pedidoGateway.salvaPagamento(pagamento);

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
        return response.pedidoFromResponse();
    }
}
