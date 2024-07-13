/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.domain.usecase.acompanhachain.anemic;

import br.com.techchallenge.fiap.neighborfood.domain.model.enums.Status;
import br.com.techchallenge.fiap.neighborfood.domain.usecase.acompanhachain.AcompanhamentoChain;

public class AcompanhamentoChainFinalizadoImpl extends AcompanhamentoChain {

    private AcompanhamentoChain statusPedidoChain;

    public AcompanhamentoChainFinalizadoImpl() {
    }

    public AcompanhamentoChainFinalizadoImpl(AcompanhamentoChain statusPedidoChain) {
        this.statusPedidoChain = statusPedidoChain;
    }

    @Override
    public String sms(Status Status) {

        final String MESSAGE =
                "______________________________\n\n" +
                        "Pedido Finalizado.\n\n" +
                        "Como você é nosso cliente especial, em isntantes você receberá uma notficação" +
                        " contendo alguns mimos, obrigado e volte sempre \n\n" +
                        "______________________________\n\n";

        if (Status.equals(Status.FINALIZADO)) {
            return MESSAGE;
        }
        return new AcompanhamentoChainProntoImpl(statusPedidoChain).sms(Status);
    }
}
