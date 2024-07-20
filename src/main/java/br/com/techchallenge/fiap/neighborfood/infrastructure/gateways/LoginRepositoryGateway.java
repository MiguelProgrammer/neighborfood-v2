/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.infrastructure.gateways;

import br.com.techchallenge.fiap.neighborfood.adapter.gateways.AccessGateway;
import br.com.techchallenge.fiap.neighborfood.adapter.inbound.AdminRequest;
import br.com.techchallenge.fiap.neighborfood.adapter.inbound.ClienteRequest;
import br.com.techchallenge.fiap.neighborfood.adapter.inbound.UsuarioRequest;
import br.com.techchallenge.fiap.neighborfood.adapter.presenter.MapperUser;
import br.com.techchallenge.fiap.neighborfood.core.domain.usuario.Usuario;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.user.AdmRepository;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.user.ClienteRepository;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.user.entities.AdminEntity;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.user.entities.ClienteEntity;
import org.springframework.stereotype.Component;

@Component
public class LoginRepositoryGateway implements AccessGateway {

    private final AdmRepository admRepository;
    private final ClienteRepository clienteRepository;
    private MapperUser mapperUser;

    public LoginRepositoryGateway(AdmRepository admRepository, ClienteRepository clienteRepository) {
        this.admRepository = admRepository;
        this.clienteRepository = clienteRepository;
        mapperUser = new MapperUser();
    }

    @Override
    public Usuario login(UsuarioRequest request) {
        return this.getUsuario(request);
    }

    @Override
    public Usuario cadastro(UsuarioRequest request) {
        return this.registerUsuario(request);
    }


    private Usuario registerUsuario(UsuarioRequest request) {

        if (request instanceof ClienteRequest clienteRequest) {
            ClienteEntity cliente = mapperUser.fromEntity(clienteRequest);
            clienteRepository.save(cliente);
            return mapperUser.fromModel(cliente);
        }

        AdminRequest adminRequest = (AdminRequest) request;
        AdminEntity usuario = mapperUser.fromEntity(adminRequest);
        admRepository.save(usuario);
        return mapperUser.fromModel(usuario);
    }

    private Usuario getUsuario(UsuarioRequest request) {

        if (request instanceof ClienteRequest clienteRequest) {
            ClienteEntity cliente = clienteRepository.findByCpf(clienteRequest.getCpf());
            return mapperUser.fromModel(cliente);
        }

        AdminEntity usuario = admRepository.findByCpf(request.getCpf());
        return mapperUser.fromModel(usuario);
    }
}
