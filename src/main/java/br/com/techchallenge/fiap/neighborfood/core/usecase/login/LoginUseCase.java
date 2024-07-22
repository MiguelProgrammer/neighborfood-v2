/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.core.usecase.login;


import br.com.techchallenge.fiap.neighborfood.adapter.gateways.AccessGateway;
import br.com.techchallenge.fiap.neighborfood.adapter.inbound.UsuarioRequest;
import br.com.techchallenge.fiap.neighborfood.core.domain.Finals;
import br.com.techchallenge.fiap.neighborfood.core.domain.usuario.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import static br.com.techchallenge.fiap.neighborfood.core.domain.Finals.MESSAGE_SUCCESS;
import static br.com.techchallenge.fiap.neighborfood.core.domain.Finals.MESSAGE_USUARIO_CADASTRADO;

@Slf4j
@Component
public class LoginUseCase {

    private Usuario usuario = new Usuario();
    private AccessGateway accessGateway;

    public LoginUseCase(AccessGateway accessGateway) {
        this.accessGateway = accessGateway;
    }

    public Usuario login(UsuarioRequest request) {
        this.usuario = accessGateway.login(request);
        if (this.usuario.getId() == null) {
            log.info(Finals.MESSAGE);
        }
        return this.usuario;
    }

    public Usuario cadastro(UsuarioRequest request) {
        cadastrado(request);
        verificarSessao(accessGateway.cadastro(request));
        return this.usuario;
    }

    public Usuario cadastroAdm(UsuarioRequest request) {
        cadastrado(request);
        verificarSessao(accessGateway.cadastroAdm(request));
        return this.usuario;
    }

    private void verificarSessao(Usuario loginRepositoryGateway) {
        if (ObjectUtils.isEmpty(usuario.getId())) {
            this.usuario = loginRepositoryGateway;
            log.info(MESSAGE_SUCCESS);
        } else {
            log.info(MESSAGE_USUARIO_CADASTRADO);
        }
    }

    private Usuario cadastrado(UsuarioRequest cadastro) {
        this.usuario = accessGateway.login(cadastro);
        this.usuario.setNotificacao(MESSAGE_SUCCESS);
        if (!ObjectUtils.isEmpty(usuario.getId())) {
            this.usuario.setNotificacao(MESSAGE_USUARIO_CADASTRADO);
        }
        return this.usuario;
    }
}
