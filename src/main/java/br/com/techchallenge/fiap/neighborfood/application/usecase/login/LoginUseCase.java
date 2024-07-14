/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.application.usecase.login;


import br.com.techchallenge.fiap.neighborfood.adapters.inbound.request.UsuarioRequest;
import br.com.techchallenge.fiap.neighborfood.application.gateways.AccessGateway;
import br.com.techchallenge.fiap.neighborfood.config.exception.UsuarioException;
import br.com.techchallenge.fiap.neighborfood.core.domain.model.usuario.Usuario;
import lombok.extern.slf4j.Slf4j;

import static br.com.techchallenge.fiap.neighborfood.core.domain.model.Finals.MESSAGE;
import static br.com.techchallenge.fiap.neighborfood.core.domain.model.Finals.MESSAGE_SUCCESS;

@Slf4j
public class LoginUseCase {

    private final AccessGateway acesso;
    private Usuario usuario = new Usuario();

    public LoginUseCase(AccessGateway acesso) {
        this.acesso = acesso;
    }

    public Usuario login(UsuarioRequest request) {
        usuario = acesso.login(request);
        if (usuario.getId() == null) {
            throw new UsuarioException(MESSAGE);
        }
        return usuario;
    }

    public Usuario cadastro(UsuarioRequest request) {
        usuario = this.login(request);
        if (cadastrado(usuario)) {
            usuario = acesso.cadastro(request);
            log.info(MESSAGE_SUCCESS);
        }
        return usuario;
    }

    private static boolean cadastrado(Usuario cadastro) {
        return cadastro == null;
    }
}
