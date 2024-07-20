/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.adapter.inbound;

import br.com.techchallenge.fiap.neighborfood.core.domain.dto.AdminRequestDTO;

public class AdminRequest extends UsuarioRequest {

    public AdminRequest() {
    }

    public AdminRequest(String nome, String email, String cpf) {
        super(nome, email, cpf);
    }

    @Override
    public UsuarioRequest dtoFromDomain(Object dtoType) {
        AdminRequestDTO dto = (AdminRequestDTO) dtoType;
        AdminRequest domain = new AdminRequest();
        domain.setNome(dto.getNome());
        domain.setCpf(dto.getCpf());
        domain.setEmail(dto.getEmail());
        return domain;
    }
}
