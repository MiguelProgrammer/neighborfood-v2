/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.core.usecase.clientes;

import br.com.techchallenge.fiap.neighborfood.adapter.gateways.UserGateway;
import br.com.techchallenge.fiap.neighborfood.adapter.presenter.MapperUser;
import br.com.techchallenge.fiap.neighborfood.core.domain.acompanhamento.Mimo;
import br.com.techchallenge.fiap.neighborfood.core.domain.acompanhamento.Notificacao;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoUseCase {

    private final UserGateway userGateway;
    private final MapperUser mapperUser = new MapperUser();

    public NotificacaoUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public Notificacao notifica(Mimo mimoRequest) {
        return null;
    }
}
