/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.application.usecase.user;

import br.com.techchallenge.fiap.neighborfood.adapters.inbound.response.AcompanhamentoResponse;
import br.com.techchallenge.fiap.neighborfood.application.gateways.PedidoGateway;
import br.com.techchallenge.fiap.neighborfood.config.exception.UsuarioException;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.enums.Status;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.pedido.Pedido;
import br.com.techchallenge.fiap.neighborfood.infrastructure.gateways.UserRepositoryGateway;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AdmUseCase {

    private final PedidoGateway pedidoGateway;
    private final UserRepositoryGateway userRepositoryGateway;

    final String MESSAGE = "\n\nAdmin não encontrado ou não localizado!";

    public AdmUseCase(PedidoGateway pedidoGateway, UserRepositoryGateway userRepositoryGateway) {
        this.pedidoGateway = pedidoGateway;
        this.userRepositoryGateway = userRepositoryGateway;
    }

    public List<AcompanhamentoResponse> listaPedidos(Long idAdmin) {
        List<AcompanhamentoResponse> listaAcomp = new ArrayList<>();

        if (ObjectUtils.isEmpty(userRepositoryGateway.usuarioById(idAdmin))) {
            throw new UsuarioException(MESSAGE);
        }

        log.info("Listando pedidos ...\n");
        List<Pedido> listaPedidos = new ArrayList<>();

        pedidoGateway.pedidosExecute().forEach(pd -> {
            listaPedidos.add(new Pedido().entityFromDomain(pd));
        });

        listaPedidos.forEach(pr -> {
            if(pr.getStatus().equals(Status.FINALIZADO)) {
                log.info(pr.toString());
            } else {
                log.info(pr.toStringAberto());
            }
            listaAcomp.add(pedidoGateway.pedido(pr));
        });

        log.info("\nListagem finalizada.");
        return listaAcomp;
    }

}
