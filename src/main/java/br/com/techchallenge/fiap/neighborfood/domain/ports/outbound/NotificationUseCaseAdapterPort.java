/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.domain.ports.outbound;

import br.com.techchallenge.fiap.neighborfood.domain.model.Mimo;
import br.com.techchallenge.fiap.neighborfood.domain.model.Notificacao;

import java.util.List;

public interface NotificationUseCaseAdapterPort {

    Mimo enviaMimos(Mimo mimoRequest);
    Notificacao notifica(Notificacao notificacao);
    List<Notificacao> findAll();
}
