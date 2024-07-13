/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.adapters.inbound.request;

import br.com.techchallenge.fiap.neighborfood.domain.dto.AdminRequestDTO;

public class AdminRequest {

    private String nome;
    private String email;
    private String cpf;

    public AdminRequest() {
    }

    public AdminRequest(String nome, String email, String cpf) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    public AdminRequest dtoFromDomain(AdminRequestDTO dto) {
        AdminRequest domain = new AdminRequest();
        domain.setNome(dto.getNome());
        domain.setCpf(dto.getCpf());
        domain.setEmail(dto.getEmail());
        return domain;
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
}
