/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.framework.web;


import _generated_sources_swagger_estoque.NeighborfoodApi;
import br.com.techchallenge.fiap.neighborfood.adapter.controllers.AcompanhamentoResponse;
import br.com.techchallenge.fiap.neighborfood.adapter.gateways.EstoqueGateway;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.AcompanhamentoResponseDTO;
import br.com.techchallenge.fiap.neighborfood.core.usecase.estoque.AdmUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EstoqueController implements NeighborfoodApi {

    private EstoqueGateway estoqueGateway;
    private AdmUseCase admUseCase;

    /**
     * GET /neighborfood/painel/produto/cadastro/{idAdmin} : Cadastra produtos
     * Cadastra produtos em lote
     *
     * @param idAdmin id identificador do administrador (required)
     * @return Produtos cadastrados (status code 200)
     * or Id inválido (status code 400)
     * or Produto inválido (status code 404)
     */

    @Override
    public ResponseEntity<Object> registerProduct(Long idAdmin) {
        return ResponseEntity.ok(estoqueGateway.gerenciaEstoque(idAdmin));
    }

    /**
     * GET /neighborfood/painel/pedido/lista/{idAdmin} : Lista de pedidos efetuados contendo seus clientes, itens, status, valores e data de pedido e data de entrega.
     * Lista os pedidos recebidos
     *
     * @param idAdmin id identificador do administrador (required)
     * @return Lista de pedidos (status code 200)
     * or Id inválido (status code 400)
     * or Mimo não disponível (status code 404)
     */
    @Override
    public ResponseEntity<List<AcompanhamentoResponseDTO>> listOrders(Long idAdmin) {
        List<AcompanhamentoResponseDTO> statusDosPedidos = new ArrayList<>();
        List<AcompanhamentoResponse> acompanhamentoResponses = admUseCase.listaPedidos(idAdmin);

        acompanhamentoResponses.forEach(resp -> {
            statusDosPedidos.add(resp.pedidoFromResponse());
        });
        return ResponseEntity.ok(statusDosPedidos);
    }
}
