package br.com.techchallenge.fiap.neighborfood.application.usecase.order.acompanhachain.status.anemic;/*
 * Copyright (c) 2024. MiguelProgrammer
 */


import br.com.techchallenge.fiap.neighborfood.application.usecase.order.acompanhachain.AcompanhamentoChain;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.enums.Status;

import static br.com.techchallenge.fiap.neighborfood.core.domain.model.Finals.MESSAGE_FINALIZADO;

public class AcompanhamentoChainFinalizado extends AcompanhamentoChain {

    private AcompanhamentoChain statusPedidoChain;

    public AcompanhamentoChainFinalizado() {
    }

    public AcompanhamentoChainFinalizado(AcompanhamentoChain statusPedidoChain) {
        this.statusPedidoChain = statusPedidoChain;
    }

    @Override
    public String sms(Status status) {
        return status.equals(Status.FINALIZADO) ? MESSAGE_FINALIZADO :
        new AcompanhamentoChainPronto(statusPedidoChain).sms(status);
    }
}
