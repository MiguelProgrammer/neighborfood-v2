/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.core.usecase.pedido;

import br.com.techchallenge.fiap.neighborfood.adapter.controllers.AcompanhamentoResponse;
import br.com.techchallenge.fiap.neighborfood.adapter.inbound.PedidoRequest;
import br.com.techchallenge.fiap.neighborfood.config.exceptions.PedidoException;
import br.com.techchallenge.fiap.neighborfood.core.domain.acompanhamento.Notificacao;
import br.com.techchallenge.fiap.neighborfood.core.domain.enums.Categoria;
import br.com.techchallenge.fiap.neighborfood.core.domain.enums.Status;
import br.com.techchallenge.fiap.neighborfood.core.domain.pedido.Item;
import br.com.techchallenge.fiap.neighborfood.core.domain.pedido.Pedido;
import br.com.techchallenge.fiap.neighborfood.core.domain.pedido.Produto;
import br.com.techchallenge.fiap.neighborfood.core.domain.usuario.Cliente;
import br.com.techchallenge.fiap.neighborfood.infrastructure.gateways.*;
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

    private final PedidoRepositoryGateway pedidoRepositoryGateway;
    private final EstoqueRepositoryGateway produtoRepositoryGateway;
    private final NotificacaoRepositoryGateway notificacaoRepositoryGateway;
    private final AcompanhamentoPedidoRepositorioGateway acompanhamentoPedidoRepositorioGateway;
    private final UserRepositoryGateway userRepositoryGateway;

    public PedidoUseCase(PedidoRepositoryGateway pedidoRepositoryGateway, EstoqueRepositoryGateway produtoRepositoryGateway, NotificacaoRepositoryGateway notificacaoRepositoryGateway, AcompanhamentoPedidoRepositorioGateway acompanhamentoPedidoRepositorioGateway, UserRepositoryGateway userRepositoryGateway) {
        this.pedidoRepositoryGateway = pedidoRepositoryGateway;
        this.produtoRepositoryGateway = produtoRepositoryGateway;
        this.notificacaoRepositoryGateway = notificacaoRepositoryGateway;
        this.acompanhamentoPedidoRepositorioGateway = acompanhamentoPedidoRepositorioGateway;
        this.userRepositoryGateway = userRepositoryGateway;
    }

    public Object menuOpcionais() {
        HashMap<Categoria, Set> menuItens = new HashMap<>();
        for (Categoria opt : Categoria.values()) {
            menuItens.put(opt, pedidoRepositoryGateway.menuOpcionais(opt));
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
            log.info("CLIENTE NÃO ENCONTRADO/LOGADO");
        }

        pedido.setIdCliente(cliente.getId());
        request.getItensPedido().forEach(item -> {

            Produto prod = produtoRepositoryGateway.findById(item.getIdProduto());

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
            pedidoResponse = pedidoRepositoryGateway.pedido(pedido);

            produtoRepositoryGateway.deleteAll(deleteProdutos);
        } else {
            NotificacaoEntity notificacao = new NotificacaoEntity();
            notificacao.setDescricao(MESSAGE_ADM_ESTOQUE);
            notificacaoRepositoryGateway.notifica(new Notificacao().entityfromDomain(notificacao));
            log.info(ITENS_EM_FALLTA);
        }

        return pedidoResponse;
    }

    public AcompanhamentoResponse atualizarPedido(PedidoRequest pedido) {
        Set<Item> itens = pedidoRepositoryGateway.findAllByIdPedido(pedido.getId());
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

        produtoRepositoryGateway.repoemEstoque(produtos);

        pedidoRepositoryGateway.removeItens(itens);
        Pedido pedidoRealizado = pedidoRepositoryGateway.findByIdPedido(pedido.getId());
        pedidoRepositoryGateway.pedido(pedidoRealizado);
        pedidoRealizado.setTotal(BigDecimal.ZERO);
        pedidoRepositoryGateway.commitUpdates(pedidoRealizado.domainFromEntity());


        pedido.getItensPedido().forEach(item -> {
            Pedido pedidoDTO = pedidoRepositoryGateway.findByIdPedido(pedido.getId());
            pedidoDTO.setTotal(pedidoDTO.getTotal().add(item.getPreco()));
            pedidoRepositoryGateway.commitUpdates(pedidoDTO.domainFromEntity());
            pedidoRepositoryGateway.saveItens(item);
            pedidoRepositoryGateway.commitUpdates(pedidoDTO.domainFromEntity());
            produtoRepositoryGateway.deleteByNome(item.getNome());
        });

        log.info("Pedido atualizado!");

        return pedidoRepositoryGateway.atualizarPedido(pedidoRepositoryGateway.findByIdPedido(pedido.getId()));

    }


    public void removeItens(Set<Item> itens) {
        pedidoRepositoryGateway.removeItens(itens);
    }
}
