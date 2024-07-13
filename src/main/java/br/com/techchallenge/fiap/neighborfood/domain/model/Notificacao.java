/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.domain.model;

import br.com.techchallenge.fiap.neighborfood.adapters.outbound.entities.NotificacaoEntity;

public class Notificacao {

    private Long idUsuario;
    private String descricao;

    public Notificacao() {
    }

    public Notificacao(Long idUsuario, String descricao) {
        this.idUsuario = idUsuario;
        this.descricao = descricao;
    }

    public NotificacaoEntity fromEntity(Notificacao notificacao) {
        NotificacaoEntity entity = new NotificacaoEntity();
        entity.setIdUsuario(notificacao.getIdUsuario());
        entity.setDescricao(notificacao.getDescricao());
        return entity;
    }

    public Notificacao entityfromDomain(NotificacaoEntity entity) {
        Notificacao notificacao = new Notificacao();
        notificacao.setIdUsuario(entity.getIdUsuario());
        notificacao.setDescricao(entity.getDescricao());
        return notificacao;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
