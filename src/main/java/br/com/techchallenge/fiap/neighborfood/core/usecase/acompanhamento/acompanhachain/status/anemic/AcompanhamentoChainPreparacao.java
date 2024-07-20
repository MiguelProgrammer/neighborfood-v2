/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.core.usecase.acompanhamento.acompanhachain.status.anemic;

import br.com.techchallenge.fiap.neighborfood.core.domain.enums.Status;
import br.com.techchallenge.fiap.neighborfood.core.usecase.acompanhamento.acompanhachain.AcompanhamentoChain;

import static br.com.techchallenge.fiap.neighborfood.core.domain.Finals.MESSAGE_PREPARACAO;

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
