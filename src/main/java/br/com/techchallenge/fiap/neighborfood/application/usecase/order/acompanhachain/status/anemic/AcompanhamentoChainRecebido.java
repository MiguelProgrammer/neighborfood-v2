package br.com.techchallenge.fiap.neighborfood.application.usecase.order.acompanhachain.status.anemic;
/*
 * Copyright (c) 2024. MiguelProgrammer
 */

import br.com.techchallenge.fiap.neighborfood.application.usecase.order.acompanhachain.AcompanhamentoChain;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.enums.Status;

import static br.com.techchallenge.fiap.neighborfood.core.domain.model.Finals.MESSAGE_RECEBIDO;

public class AcompanhamentoChainRecebido extends AcompanhamentoChain {

    private AcompanhamentoChain statusPedidoChain;

    public AcompanhamentoChainRecebido() {
    }

    public AcompanhamentoChainRecebido(AcompanhamentoChain statusPedidoChain) {
        this.statusPedidoChain = statusPedidoChain;
    }

    @Override
    public String sms(Status status) {
        return status.equals(Status.RECEBIDO) ? MESSAGE_RECEBIDO :
        new AcompanhamentoChainPreparacao(statusPedidoChain).sms(status);
    }
}
