/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.domain.usecase.acompanhachain.anemic;

import br.com.techchallenge.fiap.neighborfood.domain.model.enums.Status;
import br.com.techchallenge.fiap.neighborfood.domain.usecase.acompanhachain.AcompanhamentoChain;

public class AcompanhamentoChainPreparacaoImpl extends AcompanhamentoChain {

    private AcompanhamentoChain statusPedidoChain;

    public AcompanhamentoChainPreparacaoImpl() {
    }

    public AcompanhamentoChainPreparacaoImpl(AcompanhamentoChain statusPedidoChain) {
        this.statusPedidoChain = statusPedidoChain;
    }

    @Override
    public String sms(Status Status) {

        final String MESSAGE =
                "______________________________\n\n" +
                        "Pedido Em preparação.\n\n" +
                        "Em instantes será concluído. \n\n" +
                        "Somente aguarde, obrigado.\n\n" +
                        "______________________________\n\n";

        if (Status.equals(Status.EM_PREPARACAO)) {
            return MESSAGE;
        }
        return new AcompanhamentoChainFinalizadoImpl(statusPedidoChain).sms(Status);
    }
}
