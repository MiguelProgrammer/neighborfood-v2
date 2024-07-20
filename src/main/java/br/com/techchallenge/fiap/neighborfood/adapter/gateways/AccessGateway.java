/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.adapter.gateways;


import br.com.techchallenge.fiap.neighborfood.adapter.inbound.UsuarioRequest;
import br.com.techchallenge.fiap.neighborfood.core.domain.usuario.Usuario;

public interface AccessGateway {

    Usuario login(UsuarioRequest request);
    Usuario cadastro(UsuarioRequest request);

}
