/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.domain.model;

import br.com.techchallenge.fiap.neighborfood.adapters.outbound.entities.MimoEntity;

public class Mimo {

    private Long codigo;
    private Long idUsuario;
    private String acao;
    private String descricao;

    public Mimo() {
    }

    public Mimo(Long codigo, String descricao, Long idUsuario, String acao) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.idUsuario = idUsuario;
        this.acao = acao;
    }


    public Mimo fromModel(MimoEntity entity) {
        Mimo mimo = new Mimo();
        mimo.setIdUsuario(entity.getIdUsuario());
        mimo.setDescricao(entity.getDescricao());
        mimo.setCodigo(entity.getCodigo());
        mimo.setAcao("COMPRA");
        return mimo;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
