/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.core.domain.usuario;


public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String Notificacao;

    public Usuario() {
    }

    public Usuario(Long id, String nome, String email, String cpf, String notificacao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        Notificacao = notificacao;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNotificacao() {
        return Notificacao;
    }

    public void setNotificacao(String notificacao) {
        Notificacao = notificacao;
    }
}
