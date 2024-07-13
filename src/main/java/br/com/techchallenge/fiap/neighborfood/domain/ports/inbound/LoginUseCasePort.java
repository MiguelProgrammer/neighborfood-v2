/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.domain.ports.inbound;

import br.com.techchallenge.fiap.neighborfood.adapters.inbound.request.AdminRequest;
import br.com.techchallenge.fiap.neighborfood.adapters.inbound.request.ClienteRequest;
import br.com.techchallenge.fiap.neighborfood.domain.model.Admin;
import br.com.techchallenge.fiap.neighborfood.domain.model.Cliente;

public interface LoginUseCasePort {

    Cliente loginExecute(ClienteRequest clienteRequest);

    Cliente cadastroExecute(ClienteRequest clienteRequest);

    Admin loginAdmExecute(AdminRequest adminRequest);

    Admin cadastroAdmExecute(AdminRequest adminRequest);

}
