/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.domain.usecase.acompanhachain.anemic;

import br.com.techchallenge.fiap.neighborfood.domain.model.enums.Status;
import br.com.techchallenge.fiap.neighborfood.domain.usecase.acompanhachain.AcompanhamentoChain;


public class AcompanhamentoChainRecebidoImpl extends AcompanhamentoChain {

    private AcompanhamentoChain statusPedidoChain;

    public AcompanhamentoChainRecebidoImpl() {
    }

    public AcompanhamentoChainRecebidoImpl(AcompanhamentoChain statusPedidoChain) {
        this.statusPedidoChain = statusPedidoChain;
    }

    @Override
    public String sms(Status Status) {

        final String MESSAGE = "" +
                "______________________________\n\n" +
                "Pedido 'Recebido'.\n" +
                "Em instantes será concluído. \n\n" +
                "SELECIONE:\n" +
                "1 - Realizar pagamento \n2 - Alterar pedido \n\n" +
                "______________________________\n\n";

        if (Status.equals(Status.RECEBIDO)) {
            return MESSAGE;
        }
        return new AcompanhamentoChainPreparacaoImpl(statusPedidoChain).sms(Status);
    }
}
