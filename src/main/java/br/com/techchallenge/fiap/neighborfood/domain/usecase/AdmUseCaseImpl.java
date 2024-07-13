/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.domain.usecase;

import br.com.techchallenge.fiap.neighborfood.adapters.inbound.response.AcompanhamentoResponse;
import br.com.techchallenge.fiap.neighborfood.adapters.outbound.repository.UserAdapter;
import br.com.techchallenge.fiap.neighborfood.config.exception.AdminException;
import br.com.techchallenge.fiap.neighborfood.domain.model.Pedido;
import br.com.techchallenge.fiap.neighborfood.domain.model.enums.Status;
import br.com.techchallenge.fiap.neighborfood.domain.ports.inbound.AdminUseCasePort;
import br.com.techchallenge.fiap.neighborfood.domain.ports.outbound.AdminUseCaseAdapterPort;
import br.com.techchallenge.fiap.neighborfood.domain.ports.outbound.PedidoUseCaseAdapterPort;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AdmUseCaseImpl implements AdminUseCasePort {

    private PedidoUseCaseAdapterPort pedidoUseCaseAdapterPort;
    private UserAdapter userAdapter;

    final String MESSAGE = "\n\nAdmin não encontrado ou não localizado!";

    public AdmUseCaseImpl(PedidoUseCaseAdapterPort pedidoUseCaseAdapterPort, UserAdapter userAdapter) {
        this.pedidoUseCaseAdapterPort = pedidoUseCaseAdapterPort;
        this.userAdapter = userAdapter;
    }

    @Override
    public List<AcompanhamentoResponse> listaPedidosExecute(Long idAdmin) {
        List<AcompanhamentoResponse> listaAcomp = new ArrayList<>();

        if (ObjectUtils.isEmpty(userAdapter.adminById(idAdmin))) {
            throw new AdminException(MESSAGE);
        }

        log.info("Listando pedidos ...\n");
        List<Pedido> listaPedidos = new ArrayList<>();

        pedidoUseCaseAdapterPort.pedidosExecute().forEach(pd -> {
            listaPedidos.add(new Pedido().entityFromDomain(pd));
        });


        listaPedidos.forEach(pr -> {
            if(pr.getStatus().equals(Status.FINALIZADO)) {
                log.info(pr.toString());
            } else {
                log.info(pr.toStringAberto());
            }
            listaAcomp.add(pedidoUseCaseAdapterPort.pedido(pr));
        });
        log.info("\nListagem finalizada.");

        return listaAcomp;
    }

}
