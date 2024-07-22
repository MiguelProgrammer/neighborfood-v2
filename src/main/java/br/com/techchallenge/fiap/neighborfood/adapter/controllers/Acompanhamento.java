/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.adapter.controllers;

import br.com.techchallenge.fiap.neighborfood.core.domain.dto.AcompanhamentoResponseDTO;
import br.com.techchallenge.fiap.neighborfood.core.usecase.acompanhamento.AcompanhamentoUseCase;


public class Acompanhamento {

    private final AcompanhamentoUseCase acompanhamentoUseCase;

    public Acompanhamento(AcompanhamentoUseCase acompanhamentoUseCase) {
        this.acompanhamentoUseCase = acompanhamentoUseCase;
    }

    public AcompanhamentoResponseDTO statusDoPedido(Long idPedido) {
        return acompanhamentoUseCase.getOrderStatus(idPedido);
    }

}
