/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.adapter.controllers;

import br.com.techchallenge.fiap.neighborfood.core.domain.dto.AcompanhamentoResponseDTO;
import br.com.techchallenge.fiap.neighborfood.core.usecase.estoque.AdmUseCase;
import br.com.techchallenge.fiap.neighborfood.core.usecase.estoque.EstoqueUseCase;

import java.util.List;

public class Estoque {

    private final EstoqueUseCase estoqueUseCase;
    private final AdmUseCase admUseCase;

    public Estoque(EstoqueUseCase estoqueUseCase, AdmUseCase admUseCase) {
        this.estoqueUseCase = estoqueUseCase;
        this.admUseCase = admUseCase;
    }

    public Object cadastraProduto(Long idAdmin) {
        return estoqueUseCase.gerenciaEstoque(idAdmin);
    }

    public List<AcompanhamentoResponseDTO> listaPedidos(Long idAdmin) {
        return admUseCase.listaPedidos(idAdmin);
    }

}
