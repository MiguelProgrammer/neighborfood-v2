/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.core.usecase.estoque;

import br.com.techchallenge.fiap.neighborfood.adapter.controllers.AcompanhamentoResponse;
import br.com.techchallenge.fiap.neighborfood.core.domain.enums.Status;
import br.com.techchallenge.fiap.neighborfood.core.domain.pedido.Pedido;
import br.com.techchallenge.fiap.neighborfood.infrastructure.gateways.PedidoRepositoryGateway;
import br.com.techchallenge.fiap.neighborfood.infrastructure.gateways.UserRepositoryGateway;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AdmUseCase {

    private final PedidoRepositoryGateway pedidoRepositoryGateway;
    private final UserRepositoryGateway userRepositoryGateway;

    final String MESSAGE = "\n\nAdmin não encontrado ou não localizado!";

    public AdmUseCase(PedidoRepositoryGateway pedidoRepositoryGateway, UserRepositoryGateway userRepositoryGateway) {
        this.pedidoRepositoryGateway = pedidoRepositoryGateway;
        this.userRepositoryGateway = userRepositoryGateway;
    }

    public List<AcompanhamentoResponse> listaPedidos(Long idAdmin) {
        List<AcompanhamentoResponse> listaAcomp = new ArrayList<>();


        if (ObjectUtils.isEmpty(userRepositoryGateway.usuarioById(idAdmin))) {
            log.info(MESSAGE);
        }

        log.info("Listando pedidos ...\n");
        List<Pedido> listaPedidos = new ArrayList<>();

        pedidoRepositoryGateway.pedidos().forEach(pd -> {
            listaPedidos.add(new Pedido().entityFromDomain(pd));
        });

        listaPedidos.forEach(pr -> {
            if (pr.getStatus().equals(Status.FINALIZADO)) {
                log.info(pr.toString());
            } else {
                log.info(pr.toStringAberto());
            }
            listaAcomp.add(pedidoRepositoryGateway.pedido(pr));
        });

        log.info("\nListagem finalizada.");
        return listaAcomp;
    }

}
