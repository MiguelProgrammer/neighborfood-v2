/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.core.domain.usuario;

public class Admin extends Usuario {

    private String notificacao;

    public Admin() {
    }

    public Admin(Long id, String nome, String email, String cpf, String notificacao) {
        super(id, nome, email, cpf, notificacao);
    }

    public String getNotificacao() {
        return notificacao;
    }

    public void setNotificacao(String notificacao) {
        this.notificacao = notificacao;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "notificacao='" + notificacao + '\'' +
                '}';
    }
}
