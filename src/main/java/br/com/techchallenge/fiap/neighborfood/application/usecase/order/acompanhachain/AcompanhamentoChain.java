package br.com.techchallenge.fiap.neighborfood.application.usecase.order.acompanhachain;
/*
 * Copyright (c) 2024. MiguelProgrammer
 */


import br.com.techchallenge.fiap.neighborfood.core.domain.model.enums.Status;

public abstract class AcompanhamentoChain {

    private AcompanhamentoChain StatusPedidoChain;

    public abstract String sms(Status status);
}
