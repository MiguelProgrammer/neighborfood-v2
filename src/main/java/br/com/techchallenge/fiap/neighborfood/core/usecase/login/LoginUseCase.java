/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.core.usecase.login;


import br.com.techchallenge.fiap.neighborfood.adapter.inbound.UsuarioRequest;
import br.com.techchallenge.fiap.neighborfood.config.exceptions.UsuarioException;
import br.com.techchallenge.fiap.neighborfood.core.domain.Finals;
import br.com.techchallenge.fiap.neighborfood.core.domain.usuario.Usuario;
import br.com.techchallenge.fiap.neighborfood.infrastructure.gateways.LoginRepositoryGateway;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginUseCase {

    private final LoginRepositoryGateway acesso;
    private Usuario usuario = new Usuario();

    public LoginUseCase(LoginRepositoryGateway acesso) {
        this.acesso = acesso;
    }

    public Usuario login(UsuarioRequest request) {
        usuario = acesso.login(request);
        if (usuario.getId() == null) {
            throw new UsuarioException(Finals.MESSAGE);
        }
        return usuario;
    }

    public Usuario cadastro(UsuarioRequest request) {
        usuario = this.login(request);
        if (cadastrado(usuario)) {
            usuario = acesso.cadastro(request);
            log.info(Finals.MESSAGE_SUCCESS);
        }
        return usuario;
    }

    private static boolean cadastrado(Usuario cadastro) {
        return cadastro == null;
    }
}
