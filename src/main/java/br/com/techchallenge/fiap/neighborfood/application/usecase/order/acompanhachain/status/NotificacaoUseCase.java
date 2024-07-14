/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.application.usecase.order.acompanhachain.status;

import br.com.techchallenge.fiap.neighborfood.application.gateways.NotificationGateway;
import br.com.techchallenge.fiap.neighborfood.application.gateways.UserGateway;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.acompanhamento.Mimo;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.acompanhamento.Notificacao;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.usuario.Cliente;
import br.com.techchallenge.fiap.neighborfood.domain.dto.MimoDTO;
import br.com.techchallenge.fiap.neighborfood.infrastructure.gateways.mapper.MapperUser;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class NotificacaoUseCase {

    private final NotificationGateway notificacao;
    private final UserGateway userGateway;
    private final MapperUser mapperUser;

    public NotificacaoUseCase(NotificationGateway notificacao, UserGateway userGateway, MapperUser mapperUser) {
        this.notificacao = notificacao;
        this.userGateway = userGateway;
        this.mapperUser = mapperUser;
    }


    public MimoDTO enviaMimos(Mimo mimoRequest) {
        MimoDTO mimoDTO = new MimoDTO();
        Cliente cliente = (Cliente) userGateway.usuarioById(mimoRequest.getIdUsuario());
        if(cliente != null){
            Random gerador = new Random();
            mimoRequest.setCodigo(Long.valueOf(String.valueOf(gerador.nextInt(26))));
            mimoRequest.setDescricao("Desconto no próximo pedido. R$ " + new BigDecimal("10.90").setScale(2, RoundingMode.HALF_UP));
            mimoRequest.setIdUsuario(cliente.getId());
            Mimo mimo = notificacao.enviaMimos(mimoRequest);
            mimoDTO.setCodigo(mimo.getCodigo().toString());
            mimoDTO.setDescricao(mimo.getDescricao());
            mimoDTO.setIdCliente(mimo.getIdUsuario());
            mimoDTO.setAcao(mimo.getAcao());

            System.out.println("Mimos enviados para sua próxima compra, obrigado e volte sempre!");
        }
        return mimoDTO;
    }

    public Notificacao notifica(Mimo mimoRequest) {
        return null;
    }
}
