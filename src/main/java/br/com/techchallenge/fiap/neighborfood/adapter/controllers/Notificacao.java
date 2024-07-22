/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.adapter.controllers;

import br.com.techchallenge.fiap.neighborfood.core.domain.dto.MimoDTO;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.MimoRequestDTO;
import br.com.techchallenge.fiap.neighborfood.core.usecase.clientes.NotificacaoUseCase;


public class Notificacao {

    private final NotificacaoUseCase notificacaoUseCase;

    public Notificacao(NotificacaoUseCase notificacaoUseCase) {
        this.notificacaoUseCase = notificacaoUseCase;
    }

    public MimoDTO enviaMimos(MimoRequestDTO mimoRequestDTO) {
        return notificacaoUseCase.enviaMimos(mimoRequestDTO);
    }

}
