/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.core.usecase.acompanhamento.acompanhachain.status.anemic;

import br.com.techchallenge.fiap.neighborfood.core.domain.enums.Status;
import br.com.techchallenge.fiap.neighborfood.core.usecase.acompanhamento.acompanhachain.AcompanhamentoChain;

import static br.com.techchallenge.fiap.neighborfood.core.domain.Finals.MESSAGE_RECEBIDO;

public class AcompanhamentoChainRecebido extends AcompanhamentoChain {

    private AcompanhamentoChain statusPedidoChain;

    public AcompanhamentoChainRecebido(AcompanhamentoChain statusPedidoChain, AcompanhamentoChain statusPedidoChain1) {
        super(statusPedidoChain);
        this.statusPedidoChain = statusPedidoChain1;
    }

    @Override
    public String sms(Status status) {
        return status.equals(Status.RECEBIDO) ? MESSAGE_RECEBIDO :
                new AcompanhamentoChainPreparacao(statusPedidoChain).sms(status);
    }
}
