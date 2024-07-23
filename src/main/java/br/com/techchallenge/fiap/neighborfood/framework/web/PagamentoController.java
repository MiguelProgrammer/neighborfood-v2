/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.framework.web;


import _generated_sources_swagger_pagamento.NeighborfoodApi;
import br.com.techchallenge.fiap.neighborfood.adapter.controllers.PagamentoControllerDomain;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.AcompanhamentoResponseDTO;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.PagamentoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PagamentoController implements NeighborfoodApi {

    private PagamentoControllerDomain pagamento;

    public PagamentoController(PagamentoControllerDomain pagamento) {
        this.pagamento = pagamento;
    }

    @Override
    public ResponseEntity<AcompanhamentoResponseDTO> payment(PagamentoDTO pagamentoDTO) {
//        Pagamento pagamento = new Pagamento();
//        pagamento.setIdPedido(pagamentoDTO.getIdPedido());
//        pagamento.setPagou(pagamentoDTO.getPagou());
        AcompanhamentoResponseDTO response = pagamento.realiza(pagamentoDTO);
        return ResponseEntity.ok(response);
    }

}
