/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.application.gateways;


import br.com.techchallenge.fiap.neighborfood.adapters.inbound.request.UsuarioRequest;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.usuario.Usuario;

public interface AccessGateway {

    Usuario login(UsuarioRequest request);
    Usuario cadastro(UsuarioRequest request);

}
