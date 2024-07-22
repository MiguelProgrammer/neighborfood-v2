/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.adapter.gateways;

import br.com.techchallenge.fiap.neighborfood.core.domain.acompanhamento.Mimo;
import br.com.techchallenge.fiap.neighborfood.core.domain.acompanhamento.Notificacao;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.MimoDTO;

import java.util.List;

public interface NotificacaoGateway {

    Mimo enviaMimos(MimoDTO mimoRequest);

    Notificacao notifica(Notificacao notificacao);

    List<Notificacao> findAll();
}
