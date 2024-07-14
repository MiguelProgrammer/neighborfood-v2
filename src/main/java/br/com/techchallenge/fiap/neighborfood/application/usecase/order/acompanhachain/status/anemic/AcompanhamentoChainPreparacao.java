package br.com.techchallenge.fiap.neighborfood.application.usecase.order.acompanhachain.status.anemic;/*
 * Copyright (c) 2024. MiguelProgrammer
 */


import br.com.techchallenge.fiap.neighborfood.application.usecase.order.acompanhachain.AcompanhamentoChain;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.enums.Status;

import static br.com.techchallenge.fiap.neighborfood.core.domain.model.Finals.MESSAGE_PREPARACAO;

public class AcompanhamentoChainPreparacao extends AcompanhamentoChain {

    private AcompanhamentoChain statusPedidoChain;

    public AcompanhamentoChainPreparacao() {
    }

    public AcompanhamentoChainPreparacao(AcompanhamentoChain statusPedidoChain) {
        this.statusPedidoChain = statusPedidoChain;
    }

    @Override
    public String sms(Status status) {
        return status.equals(Status.EM_PREPARACAO) ? MESSAGE_PREPARACAO :
                new AcompanhamentoChainFinalizado(statusPedidoChain).sms(status);
    }
}
