/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.adapter.presenter;

import br.com.techchallenge.fiap.neighborfood.adapter.inbound.PedidoRequest;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.*;
import br.com.techchallenge.fiap.neighborfood.core.domain.enums.Status;
import br.com.techchallenge.fiap.neighborfood.core.domain.pedido.Item;
import br.com.techchallenge.fiap.neighborfood.core.domain.pedido.Pedido;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.entities.PedidoEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AcompanhamentoResponse {

    private PedidoRequest pedidoRequest;
    private Status status;
    private BigDecimal total;

    public AcompanhamentoResponse() {
    }

    public AcompanhamentoResponse(PedidoRequest pedidoRequest, Status status, BigDecimal total) {
        this.pedidoRequest = pedidoRequest;
        this.status = status;
        this.total = total;
    }

    public AcompanhamentoResponseDTO pedidoFromResponse() {
        AcompanhamentoResponseDTO response = new AcompanhamentoResponseDTO();
        PedidoRequestDTO requestDTO = new PedidoRequestDTO();
        requestDTO.setIdCliente(this.pedidoRequest.getIdCliente());
        List<ItemPedido> itemPedidoList = new ArrayList<>();
        this.pedidoRequest.getItensPedido().forEach(item -> {

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setId(item.getId());
            itemPedido.setIdPedido(item.getIdPedido());

            ProdutoDTO dto = new ProdutoDTO();
            dto.setId(item.getId());
            dto.setNome(item.getNome());
            dto.setDescricao(item.getDescricao());
            dto.setPreco(item.getPreco());
            dto.setCategoria(CategoriaDTO.valueOf(item.getCategoria().toString()));
            dto.setImg(item.getImg());
            itemPedido.setProduto(dto);
            itemPedidoList.add(itemPedido);
        });
        requestDTO.setItensPedido(itemPedidoList);
        response.setPedido(requestDTO);
        response.getPedido().setId(this.pedidoRequest.getId());
        response.setStatus(StatusPedidoDTO.valueOf(this.status.toString()));
        response.setTotal(this.total);
        return response;
    }

    public AcompanhamentoResponseDTO pedidoEntityFromResponse(PedidoEntity pedidoEntity) {
        AcompanhamentoResponseDTO response = new AcompanhamentoResponseDTO();
        Set<ItemPedido> itensPedido = new HashSet<>();
        itensPedido.forEach(item -> item.setIdPedido(pedidoEntity.getId()));
        PedidoRequestDTO request = new PedidoRequestDTO();
        request.setIdCliente(pedidoEntity.getIdCliente());

        pedidoEntity.getItensProdutos().forEach(item -> {
            ItemPedido item1 = new ItemPedido();
            item1.setId(item.getId());
            item1.setIdPedido(item.getIdPedido());
            ProdutoDTO produtoDTO = new ProdutoDTO();
            produtoDTO.setId(item.getIdProduto());
            produtoDTO.setNome(item.getNome());
            produtoDTO.setDescricao(item.getDescricao());
            produtoDTO.setCategoria(CategoriaDTO.valueOf(String.valueOf(item.getCategoria())));
            produtoDTO.setPreco(item.getPreco());
            produtoDTO.setImg(item.getImg());
            item1.setProduto(produtoDTO);
            itensPedido.add(item1);
        });
        request.setItensPedido(itensPedido.stream().toList());
        response.setStatus(StatusPedidoDTO.fromValue(String.valueOf(pedidoEntity.getStatus())));
        response.setTotal(pedidoEntity.getTotal());
        //this.convertPedidoRequest(request);
        response.setPedido(request);
        request.setId(pedidoEntity.getId());
        return response;
    }

    public PedidoRequest getPedidoRequest() {
        return pedidoRequest;
    }

    public void convertPedidoRequest(PedidoRequest pedidoRequest) {
        this.pedidoRequest = pedidoRequest;
    }

    public void setPedidoRequest(PedidoRequest pedidoRequest) {
        this.pedidoRequest = pedidoRequest;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public PedidoRequest convertPedidoRequest(Pedido pedido) {
        PedidoRequest request = new PedidoRequest();
        List<Item> itens = new ArrayList<>();
        request.setId(pedido.getId());
        request.setIdCliente(pedido.getIdCliente());
        pedido.getItensProdutos().forEach(item -> {
            Item produto = new Item();
            produto.setId(item.getId());
            produto.setIdProduto(item.getIdProduto());
            produto.setIdPedido(item.getIdPedido());
            produto.setNome(item.getNome());
            produto.setCategoria(item.getCategoria());
            produto.setDescricao(item.getDescricao());
            produto.setPreco(item.getPreco());
            produto.setImg(item.getImg());
            itens.add(produto);
        });

        request.setItensPedido(new HashSet<>(itens));
        return request;
    }

}







