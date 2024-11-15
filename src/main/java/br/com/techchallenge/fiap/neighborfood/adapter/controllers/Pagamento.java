/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.adapter.controllers;

import br.com.techchallenge.fiap.neighborfood.core.domain.dto.AcompanhamentoResponseDTO;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.PagamentoDTO;
import br.com.techchallenge.fiap.neighborfood.core.usecase.pagamento.PagamentoUseCase;
import org.springframework.stereotype.Component;

@Component
public class Pagamento {

    private final PagamentoUseCase pagamentoUseCase;

    public Pagamento(PagamentoUseCase pagamentoUseCase) {
        this.pagamentoUseCase = pagamentoUseCase;
    }

    public AcompanhamentoResponseDTO realiza(PagamentoDTO pagamentoDTO) {
        return pagamentoUseCase.pagamento(pagamentoDTO);
    }

}
