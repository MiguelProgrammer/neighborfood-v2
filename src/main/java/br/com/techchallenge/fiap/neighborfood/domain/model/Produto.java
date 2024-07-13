/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.domain.model;

import br.com.techchallenge.fiap.neighborfood.adapters.outbound.repository.entities.ProdutoEntity;
import br.com.techchallenge.fiap.neighborfood.domain.model.enums.Categoria;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Produto {

    private Long id;
    private String nome;
    private BigDecimal preco;
    private Categoria categoria;
    private String descricao;;
    private String img;

    public Produto() {
    }

    public Produto(Long id, String nome, BigDecimal preco, Categoria categoria, String descricao, String img) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.descricao = descricao;
        this.img = img;
    }

    public ProdutoEntity dtoFromEntity(){
        ProdutoEntity entity = new ProdutoEntity();
        entity.setNome(this.getNome());
        entity.setDescricao(this.getDescricao());
        entity.setPreco(this.getPreco());
        entity.setCategoria(this.getCategoria());
        entity.setImg(this.getImg());
        entity.setId(this.getId());
        return entity;
    }

    public Produto entityFromDto(ProdutoEntity entity){
        Produto produto = new Produto();
        produto.setNome(entity.getNome());
        produto.setDescricao(entity.getDescricao());
        produto.setPreco(entity.getPreco());
        produto.setCategoria(entity.getCategoria());
        produto.setImg(entity.getImg());
        produto.setId(entity.getId());
        return produto;
    }

    public Set<Produto> setProdutosRequestFromSetEntity(Set<ProdutoEntity> entities){
        Set<Produto> produtos = new HashSet<>();
        entities.forEach(produto -> {
            produtos.add(this.entityFromDto(produto));
        });

        return produtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
