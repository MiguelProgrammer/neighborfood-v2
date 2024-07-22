/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.framework.web;


import _generated_sources_swagger_clientes.NeighborfoodApi;
import br.com.techchallenge.fiap.neighborfood.adapter.controllers.Notificacao;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.MimoDTO;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.MimoRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificacaoController implements NeighborfoodApi {

    private Notificacao notificacaoController;

    /**
     * POST /neighborfood/painel/cliente : Envia mimo ao último cliente que realizou um pedido
     * Envia mimo ao cliente
     *
     * @param mimoRequestDTO (required)
     * @return Mimo enviado (status code 200)
     * or Id inválido (status code 400)
     * or Mimo não disponível (status code 404)
     */

    @Override
    public ResponseEntity<MimoDTO> sendBonus(MimoRequestDTO mimoRequestDTO) {
        return ResponseEntity.ok(notificacaoController.enviaMimos(mimoRequestDTO));
    }
}
