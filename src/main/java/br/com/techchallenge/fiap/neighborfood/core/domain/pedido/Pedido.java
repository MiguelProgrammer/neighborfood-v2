package br.com.techchallenge.fiap.neighborfood.core.domain.pedido;/*
 * Copyright (c) 2024. MiguelProgrammer
 */


import br.com.techchallenge.fiap.neighborfood.core.domain.enums.Status;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.entities.ItemEntity;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.entities.PedidoEntity;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.entities.ProdutoEntity;

import java.math.BigDecimal;
import java.util.*;

public class Pedido {

    private Long id;
    private Long idCliente;
    private List<Item> itensProdutos = new ArrayList<>();
    private BigDecimal total = BigDecimal.ZERO;
    private Status status;
    private Date dataPedido;
    private Date dataPedidoFim;

    public Pedido() {
    }

    public Pedido(Long id, Long idCliente, List<Item> itensProdutos, BigDecimal total, Status status, Date dataPedido, Date dataPedidoFim) {
        this.id = id;
        this.idCliente = idCliente;
        this.itensProdutos = itensProdutos;
        this.total = total;
        this.status = status;
        this.dataPedido = dataPedido;
        this.dataPedidoFim = dataPedidoFim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public List<Item> getItensProdutos() {
        return itensProdutos;
    }

    public void setItensProdutos(List<Item> itensProdutos) {
        this.itensProdutos = itensProdutos;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Date getDataPedidoFim() {
        return dataPedidoFim;
    }

    public void setDataPedidoFim(Date dataPedidoFim) {
        this.dataPedidoFim = dataPedidoFim;
    }

    public PedidoEntity domainFromEntity() {
        PedidoEntity entity = new PedidoEntity();
        entity.setId(this.id);
        entity.setIdCliente(this.idCliente);
        entity.setStatus(this.status);
        entity.setDataPedido(this.dataPedido);
        entity.setTotal(this.total);
        entity.setDataPedidoFim(this.dataPedidoFim);
        this.getItensProdutos().forEach(item -> {
            entity.getItensProdutos().add(item.itemDomainFromItemEntity());
        });
        return entity;
    }

    public Pedido entityFromDomain(PedidoEntity entity) {
        Pedido domain = new Pedido();

        domain.setId(entity.getId());
        domain.setIdCliente(entity.getIdCliente());
        domain.setStatus(entity.getStatus());
        domain.setDataPedido(entity.getDataPedido());
        domain.setTotal(entity.getTotal());
        domain.setDataPedidoFim(entity.getDataPedidoFim());
        entity.getItensProdutos().forEach(produtos -> {
            Item item = new Item();
            item.setId(produtos.getId());
            item.setIdPedido(produtos.getIdPedido());
            item.setIdProduto(produtos.getIdProduto());
            item.setNome(produtos.getNome());
            item.setDescricao(produtos.getDescricao());
            item.setCategoria(produtos.getCategoria());
            item.setPreco(produtos.getPreco());
            item.setImg(produtos.getImg());
            domain.getItensProdutos().add(item);
        });
        return domain;
    }

    public Set<Item> itemEntityFromItemDomain(ItemEntity itensProdutos) {
        Set<Item> itens = new HashSet<>();
        Item item = new Item();
        item.setId(itensProdutos.getId());
        item.setIdPedido(itensProdutos.getIdPedido());
        return itens;
    }

    public ItemEntity itemDomainFromItemEntity(Item domain) {
        ItemEntity item = new ItemEntity();
        item.setId(domain.getId());
        item.setIdPedido(domain.getIdPedido());
        item.setIdProduto(domain.getIdProduto());
        item.setNome(domain.getNome());
        item.setDescricao(domain.getDescricao());
        item.setCategoria(domain.getCategoria());
        item.setPreco(domain.getPreco());
        item.setImg(domain.getImg());
        return item;
    }

    private Set<Produto> setEntitiesToSetDomain(Set<ProdutoEntity> entities) {
        Set<Produto> produtos = new HashSet<>();
        entities.forEach(produto -> {
            produtos.add(new Produto().entityFromDto(produto));
        });
        return produtos;
    }

    private Set<ProdutoEntity> setDomainToSetEntity(Set<Produto> domains) {
        Set<ProdutoEntity> produtos = new HashSet<>();
        domains.forEach(produto -> {
            produtos.add(produto.dtoFromEntity());
        });
        return produtos;
    }

    @Override
    public String toString() {
        return "\n\n____________PEDIDO N* " + id + "____________\n\n" +
                "\nIDCLIENTE = " + idCliente +
                "\nITENS = " + itensProdutos +
                "\nTOTAL = " + total +
                "\nSTATUS = " + status +
                "\nDATA DO PEDIDO = " + dataPedido +
                "\nDATA DE FINALIZAÇÃO PEDIDO = " + dataPedidoFim +
                "\nTEMPO DE ESPERA = " + (dataPedidoFim.getMinutes() - dataPedido.getMinutes()) + " Minutos." +
                "\n____________PEDIDO N* " + id + "____________\n\n";
    }

    public String toStringAberto() {
        return "\n\n____________PEDIDO N* " + id + "____________\n\n" +
                "\nIDCLIENTE = " + idCliente +
                "\nITENS = " + itensProdutos +
                "\nTOTAL = " + total +
                "\nSTATUS = " + status +
                "\nDATA DO PEDIDO = " + dataPedido +
                "\n\n____________PEDIDO N* " + id + "____________\n\n";
    }

}
