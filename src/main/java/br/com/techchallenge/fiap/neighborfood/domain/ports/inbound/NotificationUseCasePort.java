/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.domain.ports.inbound;

import br.com.techchallenge.fiap.neighborfood.domain.dto.MimoDTO;
import br.com.techchallenge.fiap.neighborfood.domain.model.Mimo;
import br.com.techchallenge.fiap.neighborfood.domain.model.Notificacao;

public interface NotificationUseCasePort {

    MimoDTO enviaMimosExecute(Mimo mimoRequest);
    Notificacao notificaExecute(Mimo mimoRequest);
}
