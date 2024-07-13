/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.domain.ports.outbound;

import br.com.techchallenge.fiap.neighborfood.adapters.inbound.request.AdminRequest;
import br.com.techchallenge.fiap.neighborfood.adapters.inbound.request.ClienteRequest;
import br.com.techchallenge.fiap.neighborfood.domain.model.Admin;
import br.com.techchallenge.fiap.neighborfood.domain.model.Cliente;

public interface LoginUseCaseAdapterPort {

    Cliente login(ClienteRequest clienteRequest);

    Cliente cadastro(ClienteRequest clienteRequest);

    Admin loginAdm(AdminRequest adminRequest);

    Admin cadastroAdm(AdminRequest adminRequest);

}
