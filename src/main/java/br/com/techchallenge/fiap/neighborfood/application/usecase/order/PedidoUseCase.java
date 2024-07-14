/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.application.usecase.order;

import br.com.techchallenge.fiap.neighborfood.adapters.inbound.request.PedidoRequest;
import br.com.techchallenge.fiap.neighborfood.adapters.inbound.response.AcompanhamentoResponse;
import br.com.techchallenge.fiap.neighborfood.application.gateways.NotificationGateway;
import br.com.techchallenge.fiap.neighborfood.application.gateways.PedidoGateway;
import br.com.techchallenge.fiap.neighborfood.application.gateways.ProdutoGateway;
import br.com.techchallenge.fiap.neighborfood.config.exception.PedidoException;
import br.com.techchallenge.fiap.neighborfood.config.exception.UsuarioException;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.acompanhamento.Notificacao;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.enums.Categoria;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.enums.Status;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.pedido.Item;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.pedido.Pedido;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.pedido.Produto;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.usuario.Cliente;
import br.com.techchallenge.fiap.neighborfood.infrastructure.gateways.AcompanhamentoPedidoRepositorioGateway;
import br.com.techchallenge.fiap.neighborfood.infrastructure.gateways.UserRepositoryGateway;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.notification.entities.NotificacaoEntity;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
public class PedidoUseCase {

    private static final String MESSAGE_ADM_ESTOQUE =
            "Caro adm, por favor, veja a quantia de itens cadastrado no estoque!";
    private static final String CLIENTE_NOT_FOUND = "\n\nCliente ou Pedido não encontrado!\n\n";
    private static final String ITENS_EM_FALLTA = "\n\nItens selecionados em falta!\n\n";

    private final PedidoGateway pedidoGateway;
    private final ProdutoGateway produtoGateway;
    private final NotificationGateway notificationGateway;
    private final AcompanhamentoPedidoRepositorioGateway acompanhamentoPedidoRepositorioGateway;
    private final UserRepositoryGateway userRepositoryGateway;

    public PedidoUseCase(PedidoGateway pedidoGateway, ProdutoGateway estoqueUseCaseAdapterPort,
                         NotificationGateway notificationGateway,
                         AcompanhamentoPedidoRepositorioGateway acompanhamentoPedidoRepositorioGateway, UserRepositoryGateway userRepositoryGateway) {
        this.pedidoGateway = pedidoGateway;
        this.produtoGateway = estoqueUseCaseAdapterPort;
        this.notificationGateway = notificationGateway;
        this.acompanhamentoPedidoRepositorioGateway = acompanhamentoPedidoRepositorioGateway;
        this.userRepositoryGateway = userRepositoryGateway;
    }

    public Object menuOpcionais() {
        HashMap<Categoria, Set> menuItens = new HashMap<>();
        for (Categoria opt : Categoria.values()) {
            menuItens.put(opt, pedidoGateway.menuOpcionais(opt));
        }
        return menuItens;
    }


    public AcompanhamentoResponse pedido(PedidoRequest request) {

        Pedido pedido = new Pedido();

        List<Item> itensPedido = new ArrayList<>();
        Set<Produto> deleteProdutos = new HashSet<>();
        AcompanhamentoResponse pedidoResponse = new AcompanhamentoResponse();

        Cliente cliente = (Cliente) userRepositoryGateway.usuarioById(request.getIdCliente());

        if (cliente.getId() == null) {
            throw new UsuarioException("CLIENTE NÃO ENCONTRADO/LOGADO");
        }

        pedido.setIdCliente(cliente.getId());
        request.getItensPedido().forEach(item -> {

            Produto prod = produtoGateway.findById(item.getIdProduto());

            if (prod.getId() != null) {
                pedido.setTotal(pedido.getTotal().add(prod.getPreco()));

                Item itemPedido = new Item();
                itemPedido.setIdProduto(prod.getId());
                itemPedido.setNome(prod.getNome());
                itemPedido.setDescricao(prod.getDescricao());
                itemPedido.setCategoria(prod.getCategoria());
                itemPedido.setPreco(prod.getPreco());
                itemPedido.setImg(prod.getImg());
                itensPedido.add(itemPedido);
                deleteProdutos.add(prod);
            } else {
                Item emFalta = new Item();
                emFalta.setPreco(BigDecimal.ZERO);
                emFalta.setImg("https://st4.depositphotos.com/14953852/24787/v/450/depositphotos_247872612-stock-illustration-no-image-available-icon-vector.jpg");
                emFalta.setDescricao("produto em falta!");
                itensPedido.add(emFalta);
            }

        });

        pedido.setDataPedido(new Date());
        pedido.setStatus(Status.RECEBIDO);
        pedido.setItensProdutos(itensPedido);
        pedido.setIdCliente(request.getIdCliente());

        if (!pedido.getTotal().equals(BigDecimal.ZERO)) {

            log.info(acompanhamentoPedidoRepositorioGateway.sms(pedido.getStatus()));
            pedidoResponse = pedidoGateway.pedido(pedido);

            produtoGateway.deleteAll(deleteProdutos);
        } else {
            NotificacaoEntity notificacao = new NotificacaoEntity();
            notificacao.setDescricao(MESSAGE_ADM_ESTOQUE);
            notificationGateway.notifica(new Notificacao().entityfromDomain(notificacao));
            log.info(ITENS_EM_FALLTA);
        }

        return pedidoResponse;
    }

    public AcompanhamentoResponse atualizarPedido(PedidoRequest pedido) {
        Set<Item> itens = pedidoGateway.findAllByIdPedido(pedido.getId());
        Cliente cliente = (Cliente) userRepositoryGateway.usuarioById(pedido.getIdCliente());
        Set<Produto> produtos = new HashSet<>();

        if (cliente.getId() == null && produtos == null) {
            throw new PedidoException(CLIENTE_NOT_FOUND);
        }

        itens.forEach(item -> {
            Produto produto = new Produto();
            produto.setId(item.getId());
            produto.setNome(item.getNome());
            produto.setDescricao(item.getDescricao());
            produto.setCategoria(item.getCategoria());
            produto.setPreco(item.getPreco());
            produto.setImg(item.getImg());
            produtos.add(produto);
        });

        produtoGateway.repoemEstoque(produtos);

        pedidoGateway.removeItens(itens);
        Pedido pedidoRealizado = pedidoGateway.findByIdPedido(pedido.getId());
        pedidoGateway.pedido(pedidoRealizado);
        pedidoRealizado.setTotal(BigDecimal.ZERO);
        pedidoGateway.commitUpdates(pedidoRealizado.domainFromEntity());


        pedido.getItensPedido().forEach(item -> {
            Pedido pedidoDTO = pedidoGateway.findByIdPedido(pedido.getId());
            pedidoDTO.setTotal(pedidoDTO.getTotal().add(item.getPreco()));
            pedidoGateway.commitUpdates(pedidoDTO.domainFromEntity());
            pedidoGateway.saveItens(item);
            pedidoGateway.commitUpdates(pedidoDTO.domainFromEntity());
            produtoGateway.deleteByNome(item.getNome());
        });

        log.info("Pedido atualizado!");

        return pedidoGateway.atualizarPedido(pedidoGateway.findByIdPedido(pedido.getId()));

    }


    public void removeItens(Set<Item> itens) {
        pedidoGateway.removeItens(itens);
    }
}
