/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.web;


import _generated_sources_swagger.NeighborfoodApi;
import br.com.techchallenge.fiap.neighborfood.adapters.inbound.request.AdminRequest;
import br.com.techchallenge.fiap.neighborfood.adapters.inbound.request.ClienteRequest;
import br.com.techchallenge.fiap.neighborfood.adapters.inbound.request.PedidoRequest;
import br.com.techchallenge.fiap.neighborfood.adapters.inbound.response.AcompanhamentoResponse;
import br.com.techchallenge.fiap.neighborfood.application.usecase.login.LoginUseCase;
import br.com.techchallenge.fiap.neighborfood.application.usecase.order.PedidoUseCase;
import br.com.techchallenge.fiap.neighborfood.application.usecase.order.acompanhachain.status.AcompanhamentoUseCase;
import br.com.techchallenge.fiap.neighborfood.application.usecase.order.acompanhachain.status.NotificacaoUseCase;
import br.com.techchallenge.fiap.neighborfood.application.usecase.order.payment.PagamentoUseCase;
import br.com.techchallenge.fiap.neighborfood.application.usecase.product.ProdutoUseCase;
import br.com.techchallenge.fiap.neighborfood.application.usecase.user.AdmUseCase;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.acompanhamento.Mimo;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.pagamento.Pagamento;
import br.com.techchallenge.fiap.neighborfood.domain.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LanchoneteController implements NeighborfoodApi {

    private LoginUseCase loginUseCase;
    private ProdutoUseCase produtoUseCase;
    private PedidoUseCase pedidoUseCase;
    private PagamentoUseCase pagamentoUseCase;
    private AdmUseCase admUseCase;
    private NotificacaoUseCase notificacaoUseCase;
    private AcompanhamentoUseCase acompanhamentoUseCase;

    /**
     * POST /neighborfood/login : Se cadastrar, logar
     * Identificação do cliente
     *
     * @param clienteRequestDTO Identifica um cliente logado (optional)
     * @return Usuário logado (status code 200)
     * or request inválida (status code 400)
     */
    @Override
    public ResponseEntity<Object> login(ClienteRequestDTO clienteRequestDTO) {
        return ResponseEntity.ok(
                loginUseCase.login(new ClienteRequest().dtoFromDomain(clienteRequestDTO)));
    }

    /**
     * POST /neighborfood/painel/login : Cadastrar adm, logar adm
     * Identificação do adm
     *
     * @param adminRequestDTO Identifica um adminsitrador logado (optional)
     * @return Usuário logado (status code 200)
     * or request inválida (status code 400)
     */
    @Override
    public ResponseEntity<Object> loginAdm(AdminRequestDTO adminRequestDTO) {
        return ResponseEntity.ok(
                loginUseCase.login(new AdminRequest().dtoFromDomain(adminRequestDTO)));
    }

    /**
     * POST /neighborfood/cadastro : Se cadastrar, logar
     * Cria cliente
     *
     * @param clienteRequestDTO Cria um novo cliente (optional)
     * @return Usuário logado (status code 200)
     * or request inválida (status code 400)
     */
    @Override
    public ResponseEntity<Object> register(ClienteRequestDTO clienteRequestDTO) {
        return ResponseEntity.ok(
                loginUseCase.cadastro(new ClienteRequest().dtoFromDomain(clienteRequestDTO)));
    }

    /**
     * POST /neighborfood/painel/cadastro : Se cadastrar, logar
     * Cria cliente
     *
     * @param adminRequestDTO Cria um novo administrador (optional)
     * @return Usuário cadastrao (status code 200)
     * or request inválida (status code 400)
     */
    @Override
    public ResponseEntity<Object> registerAdm(AdminRequestDTO adminRequestDTO) {
        return ResponseEntity.ok(
                loginUseCase.cadastro(new AdminRequest().dtoFromDomain(adminRequestDTO)));
    }


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
        return ResponseEntity.ok(produtoUseCase.gerenciaEstoque(idAdmin));
    }


    /**
     * GET /neighborfood/menu : Apresenta o menu com itens opcionais
     * menu de opções
     *
     * @return Apresenta os itens de menu (status code 200)
     * or request inválida (status code 400)
     */
    @Override
    public ResponseEntity<Object> menu() {
        return ResponseEntity.ok(pedidoUseCase.menuOpcionais());
    }

    @Override
    public ResponseEntity<AcompanhamentoResponseDTO> order(PedidoRequestDTO pedidoRequest) {
        AcompanhamentoResponse response =
                pedidoUseCase.pedido(new PedidoRequest().dtoFromRequest(pedidoRequest));
        return ResponseEntity.ok(response.pedidoFromResponse());
    }

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


    /**
     * GET /neighborfood/acompanhamento/{idPedido} : Procura o status de um pedido
     * Retorna o status de um pedido
     *
     * @param idPedido id do pedido (required)
     * @return successful operation (status code 200)
     * or Id inválido (status code 400)
     * or Pedido não encontrado (status code 404)
     */
    @Override
    public ResponseEntity<AcompanhamentoResponseDTO> findOrderByIdOrder(Long idPedido) {
        AcompanhamentoResponse orderStatusExecute = acompanhamentoUseCase.getOrderStatus(idPedido);
        return ResponseEntity.ok(orderStatusExecute.pedidoFromResponse());
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
        Mimo mimo = new Mimo();
        mimo.setIdUsuario(mimoRequestDTO.getIdCliente());
        return ResponseEntity.ok(notificacaoUseCase.enviaMimos(mimo));
    }

    /**
     * PUT /neighborfood/pedido/update : Atualizar um pedido
     * Atualizar itens de um pedido já realizado
     *
     * @param pedidoDTO atualiar um  pedido (optional)
     * @return Pedido atualizado (status code 200)
     * or request inválida (status code 400)
     */
    @Override
    public ResponseEntity<AcompanhamentoResponseDTO> updateOrder(PedidoRequestDTO pedidoDTO) {
        AcompanhamentoResponse response = pedidoUseCase.atualizarPedido(new PedidoRequest().dtoFromRequest(pedidoDTO));
        return ResponseEntity.ok(response.pedidoFromResponse());
    }


}
