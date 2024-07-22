/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.core.usecase.pedido;

import br.com.techchallenge.fiap.neighborfood.adapter.gateways.*;
import br.com.techchallenge.fiap.neighborfood.adapter.inbound.PedidoRequest;
import br.com.techchallenge.fiap.neighborfood.config.exceptions.PedidoException;
import br.com.techchallenge.fiap.neighborfood.core.domain.acompanhamento.Notificacao;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.AcompanhamentoResponseDTO;
import br.com.techchallenge.fiap.neighborfood.core.domain.enums.Categoria;
import br.com.techchallenge.fiap.neighborfood.core.domain.enums.Status;
import br.com.techchallenge.fiap.neighborfood.core.domain.pedido.Item;
import br.com.techchallenge.fiap.neighborfood.core.domain.pedido.Pedido;
import br.com.techchallenge.fiap.neighborfood.core.domain.pedido.Produto;
import br.com.techchallenge.fiap.neighborfood.core.domain.usuario.Cliente;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.notification.entities.NotificacaoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

import static br.com.techchallenge.fiap.neighborfood.core.domain.Finals.*;

@Slf4j
@Component
public class PedidoUseCase {


    private final PedidoGateway pedidoGateway;
    private final EstoqueGateway estoqueGateway;
    private final NotificacaoGateway notificacaoGateway;
    private final AcompanhamentoGateway acompanhamentoGateway;
    private final UserGateway userGateway;

    public PedidoUseCase(PedidoGateway pedidoGateway, EstoqueGateway estoqueGateway,
                         NotificacaoGateway notificacaoGateway, AcompanhamentoGateway acompanhamentoGateway,
                         UserGateway userGateway) {
        this.pedidoGateway = pedidoGateway;
        this.estoqueGateway = estoqueGateway;
        this.notificacaoGateway = notificacaoGateway;
        this.acompanhamentoGateway = acompanhamentoGateway;
        this.userGateway = userGateway;
    }

    public Object menuOpcionais() {
        HashMap<Categoria, Set> menuItens = new HashMap<>();
        for (Categoria opt : Categoria.values()) {
            menuItens.put(opt, pedidoGateway.menuOpcionais(opt));
        }
        return menuItens;
    }


    public AcompanhamentoResponseDTO pedido(PedidoRequest request) {

        Pedido pedido = new Pedido();

        List<Item> itensPedido = new ArrayList<>();
        Set<Produto> deleteProdutos = new HashSet<>();
        AcompanhamentoResponseDTO pedidoResponse = new AcompanhamentoResponseDTO();

        Cliente cliente = (Cliente) userGateway.usuarioById(request.getIdCliente());

        if (cliente.getId() == null) {
            log.info("CLIENTE NÃO ENCONTRADO/LOGADO");
        }

        pedido.setIdCliente(cliente.getId());
        request.getItensPedido().forEach(item -> {

            Produto prod = estoqueGateway.findById(item.getIdProduto());

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

            log.info(acompanhamentoGateway.sms(pedido.getStatus()));
            pedidoResponse = pedidoGateway.pedido(pedido);

            estoqueGateway.deleteAll(deleteProdutos);
        } else {
            NotificacaoEntity notificacao = new NotificacaoEntity();
            notificacao.setDescricao(MESSAGE_ADM_ESTOQUE);
            notificacaoGateway.notifica(new Notificacao().entityfromDomain(notificacao));
            log.info(ITENS_EM_FALLTA);
        }

        return pedidoResponse;
    }

    public AcompanhamentoResponseDTO atualizarPedido(PedidoRequest pedido) {
        Set<Item> itens = pedidoGateway.findAllByIdPedido(pedido.getId());
        Cliente cliente = (Cliente) userGateway.usuarioById(pedido.getIdCliente());
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

        estoqueGateway.repoemEstoque(produtos);

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
            estoqueGateway.deleteByNome(item.getNome());
        });

        log.info("Pedido atualizado!");

        return pedidoGateway.atualizarPedido(pedidoGateway.findByIdPedido(pedido.getId()));

    }


    public void removeItens(Set<Item> itens) {
        pedidoGateway.removeItens(itens);
    }
}
