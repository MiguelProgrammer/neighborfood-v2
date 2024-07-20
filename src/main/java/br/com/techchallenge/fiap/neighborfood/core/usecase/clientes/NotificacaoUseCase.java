/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.core.usecase.clientes;

import br.com.techchallenge.fiap.neighborfood.adapter.presenter.MapperUser;
import br.com.techchallenge.fiap.neighborfood.core.domain.acompanhamento.Mimo;
import br.com.techchallenge.fiap.neighborfood.core.domain.acompanhamento.Notificacao;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.MimoDTO;
import br.com.techchallenge.fiap.neighborfood.core.domain.usuario.Cliente;
import br.com.techchallenge.fiap.neighborfood.infrastructure.gateways.NotificacaoRepositoryGateway;
import br.com.techchallenge.fiap.neighborfood.infrastructure.gateways.UserRepositoryGateway;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class NotificacaoUseCase {

    private final NotificacaoRepositoryGateway notificacao;
    private final UserRepositoryGateway userRepositoryGateway;
    private final MapperUser mapperUser;

    public NotificacaoUseCase(NotificacaoRepositoryGateway notificacao, UserRepositoryGateway userRepositoryGateway, MapperUser mapperUser) {
        this.notificacao = notificacao;
        this.userRepositoryGateway = userRepositoryGateway;
        this.mapperUser = mapperUser;
    }

    public MimoDTO enviaMimos(Mimo mimoRequest) {
        MimoDTO mimoDTO = new MimoDTO();
        Cliente cliente = (Cliente) userRepositoryGateway.usuarioById(mimoRequest.getIdUsuario());
        if (cliente != null) {
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
