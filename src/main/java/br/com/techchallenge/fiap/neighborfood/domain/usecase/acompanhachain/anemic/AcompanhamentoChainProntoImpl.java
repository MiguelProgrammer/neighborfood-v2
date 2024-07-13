/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.domain.usecase.acompanhachain.anemic;

import br.com.techchallenge.fiap.neighborfood.domain.model.enums.Status;
import br.com.techchallenge.fiap.neighborfood.domain.usecase.acompanhachain.AcompanhamentoChain;

public class AcompanhamentoChainProntoImpl extends AcompanhamentoChain {

    private AcompanhamentoChain statusPedidoChain;

    public AcompanhamentoChainProntoImpl() {
    }

    public AcompanhamentoChainProntoImpl(AcompanhamentoChain statusPedidoChain) {
        this.statusPedidoChain = statusPedidoChain;
    }

    @Override
    public String sms(Status Status) {

        final String MESSAGE =
                "______________________________\n\n" +
                        "Pedido está Pronto.\n\n" +
                        "Retire o quanto antes.\n\n" +
                        "______________________________\n\n";

        if (Status.equals(Status.PRONTO)) {
            return MESSAGE;
        }
        return null;
    }
}
