/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.core.usecase.acompanhamento.acompanhachain.status.anemic;

import br.com.techchallenge.fiap.neighborfood.core.domain.enums.Status;
import br.com.techchallenge.fiap.neighborfood.core.usecase.acompanhamento.acompanhachain.AcompanhamentoChain;

import static br.com.techchallenge.fiap.neighborfood.core.domain.Finals.MESSAGE_PRONTO;

public class AcompanhamentoChainPronto extends AcompanhamentoChain {

    public AcompanhamentoChainPronto(AcompanhamentoChain statusPedidoChain) {
        super(statusPedidoChain);
    }

    @Override
    public String sms(Status status) {
        return status.equals(Status.PRONTO) ? MESSAGE_PRONTO : null;
    }
}
