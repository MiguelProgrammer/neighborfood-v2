/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.framework.web;


import _generated_sources_swagger_pagamento.NeighborfoodApi;
import br.com.techchallenge.fiap.neighborfood.adapter.controllers.AcompanhamentoResponse;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.AcompanhamentoResponseDTO;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.PagamentoDTO;
import br.com.techchallenge.fiap.neighborfood.core.domain.pagamento.Pagamento;
import br.com.techchallenge.fiap.neighborfood.core.usecase.pagamento.PagamentoUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PagamentoController implements NeighborfoodApi {

    private PagamentoUseCase pagamentoUseCase;


    /**
     * POST /neighborfood/pagamento : Realiza o pagamento do pedido
     * Realiza pagamento
     *
     * @param pagamentoDTO (required)
     * @return pagamento realizado com sucesso. (status code 200)
     * or Id inválido (status code 400)
     * or Pedido não encontrado (status code 404)
     */
    @Override
    public ResponseEntity<AcompanhamentoResponseDTO> payment(PagamentoDTO pagamentoDTO) {
        Pagamento pagamento = new Pagamento();
        pagamento.setIdPedido(pagamentoDTO.getIdPedido());
        pagamento.setPagou(pagamentoDTO.getPagou());
        AcompanhamentoResponse response = pagamentoUseCase.pagamento(pagamento);
        return ResponseEntity.ok(response.pedidoFromResponse());
    }

}
