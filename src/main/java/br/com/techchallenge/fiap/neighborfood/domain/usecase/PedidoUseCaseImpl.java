/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.domain.usecase;

import br.com.techchallenge.fiap.neighborfood.adapters.inbound.request.PedidoRequest;
import br.com.techchallenge.fiap.neighborfood.adapters.inbound.response.AcompanhamentoResponse;
import br.com.techchallenge.fiap.neighborfood.adapters.outbound.repository.UserAdapter;
import br.com.techchallenge.fiap.neighborfood.adapters.outbound.repository.entities.NotificacaoEntity;
import br.com.techchallenge.fiap.neighborfood.config.exception.ClienteException;
import br.com.techchallenge.fiap.neighborfood.config.exception.PedidoException;
import br.com.techchallenge.fiap.neighborfood.domain.model.*;
import br.com.techchallenge.fiap.neighborfood.domain.model.enums.Categoria;
import br.com.techchallenge.fiap.neighborfood.domain.model.enums.Status;
import br.com.techchallenge.fiap.neighborfood.domain.ports.inbound.AcompanhamentoUseCasePort;
import br.com.techchallenge.fiap.neighborfood.domain.ports.inbound.PedidoUseCasePort;
import br.com.techchallenge.fiap.neighborfood.domain.ports.outbound.NotificationUseCaseAdapterPort;
import br.com.techchallenge.fiap.neighborfood.domain.ports.outbound.PedidoUseCaseAdapterPort;
import br.com.techchallenge.fiap.neighborfood.domain.ports.outbound.ProdutoUseCaseAdapterPort;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
public class PedidoUseCaseImpl implements PedidoUseCasePort {

    private static final String MESSAGE_ADM_ESTOQUE =
            "Caro adm, por favor, veja a quantia de itens cadastrado no estoque!";
    private static final String CLIENTE_NOT_FOUND = "\n\nCliente ou Pedido não encontrado!\n\n";
    private static final String ITENS_EM_FALLTA = "\n\nItens selecionados em falta!\n\n";

    private PedidoUseCaseAdapterPort pedidoUseCaseAdapterPort;
    private ProdutoUseCaseAdapterPort produtoUseCaseAdapterPort;
    private NotificationUseCaseAdapterPort notificationUseCaseAdapterPort;
    private AcompanhamentoUseCasePort acompanhamentoUseCasePort;
    private UserAdapter userAdapter;

    public PedidoUseCaseImpl(PedidoUseCaseAdapterPort pedidoUseCaseAdapterPort, ProdutoUseCaseAdapterPort estoqueUseCaseAdapterPort, NotificationUseCaseAdapterPort notificationUseCaseAdapterPort, AcompanhamentoUseCasePort acompanhamentoUseCasePort, UserAdapter userAdapter) {
        this.pedidoUseCaseAdapterPort = pedidoUseCaseAdapterPort;
        this.produtoUseCaseAdapterPort = estoqueUseCaseAdapterPort;
        this.notificationUseCaseAdapterPort = notificationUseCaseAdapterPort;
        this.acompanhamentoUseCasePort = acompanhamentoUseCasePort;
        this.userAdapter = userAdapter;
    }

    @Override
    public Object menuOpcionaisExecute() {
        HashMap<Categoria, Set> menuItens = new HashMap<>();

        for (Categoria opt : Categoria.values()) {
            menuItens.put(opt, pedidoUseCaseAdapterPort.menuOpcionais(opt));
        }
        return menuItens;
    }

    @Override
    public AcompanhamentoResponse pedidoExecute(PedidoRequest request) {

        Pedido pedido = new Pedido();

        List<Item> itensPedido = new ArrayList<>();
        Set<Produto> deleteProdutos = new HashSet<>();
        AcompanhamentoResponse pedidoResponse = new AcompanhamentoResponse();

        Cliente cliente = userAdapter.clienteById(request.getIdCliente());


        if (cliente.getId() == null) {
            throw new ClienteException("CLIENTE NÃO ENCONTRADO/LOGADO");
        }

        pedido.setIdCliente(cliente.getId());
        request.getItensPedido().forEach(item -> {

            Produto prod = produtoUseCaseAdapterPort.findById(item.getIdProduto());

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

            log.info(acompanhamentoUseCasePort.smsExecute(pedido.getStatus()));
            pedidoResponse = pedidoUseCaseAdapterPort.pedido(pedido);

            produtoUseCaseAdapterPort.deleteAll(deleteProdutos);
        } else {
            NotificacaoEntity notificacao = new NotificacaoEntity();
            notificacao.setDescricao(MESSAGE_ADM_ESTOQUE);
            notificationUseCaseAdapterPort.notifica(new Notificacao().entityfromDomain(notificacao));
            log.info(ITENS_EM_FALLTA);
        }

        return pedidoResponse;
    }


    @Override
    public AcompanhamentoResponse atualizarPedidoExecute(PedidoRequest pedido) {
        Set<Item> itens = pedidoUseCaseAdapterPort.findAllByIdPedido(pedido.getId());
        Cliente cliente = userAdapter.clienteById(pedido.getIdCliente());
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

        produtoUseCaseAdapterPort.repoemEstoque(produtos);

        pedidoUseCaseAdapterPort.removeItens(itens);
        Pedido pedidoRealizado = pedidoUseCaseAdapterPort.findByIdPedido(pedido.getId());
        pedidoUseCaseAdapterPort.pedido(pedidoRealizado);
        pedidoRealizado.setTotal(BigDecimal.ZERO);
        pedidoUseCaseAdapterPort.commitUpdates(pedidoRealizado.domainFromEntity());


        pedido.getItensPedido().forEach(item -> {
            Pedido pedidoDTO = pedidoUseCaseAdapterPort.findByIdPedido(pedido.getId());
            pedidoDTO.setTotal(pedidoDTO.getTotal().add(item.getPreco()));
            pedidoUseCaseAdapterPort.commitUpdates(pedidoDTO.domainFromEntity());
            pedidoUseCaseAdapterPort.saveItens(item);
            pedidoUseCaseAdapterPort.commitUpdates(pedidoDTO.domainFromEntity());
            produtoUseCaseAdapterPort.deleteByNome(item.getNome());
        });

        log.info("Pedido atualizado!");

        return pedidoUseCaseAdapterPort.atualizarPedido(pedidoUseCaseAdapterPort.findByIdPedido(pedido.getId()));

    }

    @Override
    public void removeItensExecute(Set<Item> itens) {
        pedidoUseCaseAdapterPort.removeItens(itens);
    }
}
