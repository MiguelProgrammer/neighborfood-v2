/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.adapter.controllers;

import br.com.techchallenge.fiap.neighborfood.adapter.inbound.UsuarioRequest;
import br.com.techchallenge.fiap.neighborfood.core.domain.usuario.Usuario;
import br.com.techchallenge.fiap.neighborfood.core.usecase.login.LoginUseCase;
import org.springframework.stereotype.Component;

@Component
public class Acesso {

    private final LoginUseCase loginUseCase;

    public Acesso(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    public Usuario login(UsuarioRequest request) {
        return loginUseCase.login(request);
    }

    public Usuario cadastro(UsuarioRequest usuarioRequest) {
        return loginUseCase.cadastro(usuarioRequest);
    }

    public Usuario cadastroAdm(UsuarioRequest usuarioRequest) {
        return loginUseCase.cadastroAdm(usuarioRequest);
    }
}
