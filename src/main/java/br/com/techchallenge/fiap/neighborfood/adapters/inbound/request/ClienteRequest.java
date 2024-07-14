/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.adapters.inbound.request;

import br.com.techchallenge.fiap.neighborfood.domain.dto.ClienteRequestDTO;

public class ClienteRequest extends UsuarioRequest {

    public ClienteRequest() {
    }

    public ClienteRequest(String nome, String email, String cpf) {
        super(nome, email, cpf);
    }

    @Override
    public UsuarioRequest dtoFromDomain(Object dtoType) {
        ClienteRequestDTO dto = (ClienteRequestDTO) dtoType;
        ClienteRequest domain = new ClienteRequest();
        domain.setNome(dto.getNome());
        domain.setCpf(dto.getCpf());
        domain.setEmail(dto.getEmail());
        return domain;
    }

}
