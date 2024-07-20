/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.adapter.inbound;

import br.com.techchallenge.fiap.neighborfood.core.domain.dto.PedidoRequestDTO;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.ProdutoDTO;
import br.com.techchallenge.fiap.neighborfood.core.domain.enums.Categoria;
import br.com.techchallenge.fiap.neighborfood.core.domain.pedido.Item;
import br.com.techchallenge.fiap.neighborfood.core.domain.pedido.Produto;

import java.util.HashSet;
import java.util.Set;

public class PedidoRequest {

    private Long id;
    private Long idCliente;
    private Set<Item> itensPedido = new HashSet<>();

    public PedidoRequest() {
    }

    public PedidoRequest(Long id, Long idCliente, Set<Item> itensPedido) {
        this.id = id;
        this.idCliente = idCliente;
        this.itensPedido = itensPedido;
    }

    public PedidoRequest dtoFromRequest(PedidoRequestDTO pedidoRequest) {

        PedidoRequest request = new PedidoRequest();
        request.setId(pedidoRequest.getId());
        request.setIdCliente(pedidoRequest.getIdCliente());

        pedidoRequest.getItensPedido().forEach(item -> {
            Item it = new Item();
            it.setId(item.getId());
            it.setIdPedido(item.getIdPedido());
            it.setIdProduto(item.getProduto().getId());
            it.setNome(item.getProduto().getNome());
            it.setDescricao(item.getProduto().getDescricao());
            it.setPreco(item.getProduto().getPreco());
            it.setCategoria(Categoria.valueOf(item.getProduto().getCategoria().getValue()));
            it.setImg(item.getProduto().getImg());
            request.getItensPedido().add(it);
        });
        return request;
    }

    private Set<Produto> setProdutosDtoFromProdutos(Set<ProdutoDTO> produtos) {
        Set<Produto> produtosDomain = new HashSet<>();
        produtos.forEach(produto -> {
            Produto prod = new Produto();
            prod.setId(produto.getId());
            prod.setNome(produto.getNome());
            prod.setCategoria(Categoria.valueOf(produto.getCategoria().getValue()));
            prod.setDescricao(produto.getDescricao());
            prod.setPreco(produto.getPreco());
            prod.setImg(produto.getImg());
            produtosDomain.add(prod);
        });
        return produtosDomain;
    }

    private Produto toDomain(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setId(dto.getId());
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setCategoria(Categoria.valueOf(dto.getCategoria().getValue()));
        produto.setImg(dto.getImg());
        produto.setImg(dto.getImg());
        produto.setPreco(dto.getPreco());
        return produto;
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

    public Set<Item> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(Set<Item> itensPedido) {
        this.itensPedido = itensPedido;
    }
}
