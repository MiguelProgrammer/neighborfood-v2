/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.core.usecase.acompanhamento.acompanhachain.status.anemic;

import br.com.techchallenge.fiap.neighborfood.core.domain.enums.Status;
import br.com.techchallenge.fiap.neighborfood.core.usecase.acompanhamento.acompanhachain.AcompanhamentoChain;

import static br.com.techchallenge.fiap.neighborfood.core.domain.Finals.MESSAGE_FINALIZADO;

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
