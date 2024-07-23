/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.core.usecase.estoque;

import br.com.techchallenge.fiap.neighborfood.adapter.gateways.PedidoGateway;
import br.com.techchallenge.fiap.neighborfood.adapter.gateways.UserGateway;
import br.com.techchallenge.fiap.neighborfood.config.exceptions.PedidoException;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.AcompanhamentoResponseDTO;
import br.com.techchallenge.fiap.neighborfood.core.domain.enums.Status;
import br.com.techchallenge.fiap.neighborfood.core.domain.pedido.Pedido;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class AdmUseCase {

    private final PedidoGateway pedidoGateway;
    private final UserGateway userGateway;

    final String MESSAGE = "\n\nAdmin não encontrado ou não localizado!";

    public AdmUseCase(PedidoGateway pedidoGateway, UserGateway userGateway) {
        this.pedidoGateway = pedidoGateway;
        this.userGateway = userGateway;
    }

    public List<AcompanhamentoResponseDTO> listaPedidos(Long idAdmin) {
        List<AcompanhamentoResponseDTO> listaAcomp = new ArrayList<>();


        if (ObjectUtils.isEmpty(userGateway.usuarioById(idAdmin).getId())) {
            throw new PedidoException(MESSAGE);
        }

        log.info("Listando pedidos ...\n");
        List<Pedido> listaPedidos = new ArrayList<>();

        pedidoGateway.pedidos().forEach(pd -> {
            listaPedidos.add(new Pedido().entityFromDomain(pd));
        });

        listaPedidos.forEach(pr -> {
            if (!pr.getStatus().equals(Status.FINALIZADO)) {
                listaAcomp.add(pedidoGateway.pedido(pr));
            }
        });

        log.info("\nListagem finalizada.");
        return listaAcomp;
    }

}
