/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.domain.usecase.acompanhachain;

import br.com.techchallenge.fiap.neighborfood.domain.model.enums.Status;

public abstract class AcompanhamentoChain {

    private AcompanhamentoChain StatusPedidoChain;

    public abstract String sms(Status Status);
}
