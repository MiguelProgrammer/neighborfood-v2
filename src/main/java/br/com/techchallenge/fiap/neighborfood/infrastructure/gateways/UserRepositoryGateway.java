/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.infrastructure.gateways;

import br.com.techchallenge.fiap.neighborfood.adapter.gateways.UserGateway;
import br.com.techchallenge.fiap.neighborfood.adapter.presenter.MapperUser;
import br.com.techchallenge.fiap.neighborfood.core.domain.usuario.Usuario;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.user.AdmRepository;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.user.ClienteRepository;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.user.entities.AdminEntity;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.user.entities.ClienteEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryGateway implements UserGateway {

    private final AdmRepository admRepository;
    private final ClienteRepository clienteRepository;
    private MapperUser mapperUser = new MapperUser();

    public UserRepositoryGateway(AdmRepository admRepository, ClienteRepository clienteRepository) {
        this.admRepository = admRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Usuario usuarioById(Long idUsuario) {
        return this.getUsuarioPorId(idUsuario);
    }

    @Override
    public Usuario usuarioByCpf(String cpf) {
        return this.getUsuarioPorCpf(cpf);
    }

    private Usuario getUsuarioPorCpf(String cpf) {
        ClienteEntity cliente = clienteRepository.findByCpf(cpf);
        if (cliente == null) {
            AdminEntity usuario = admRepository.findByCpf(cpf);
            return usuario != null ? mapperUser.fromModel(usuario) : new Usuario();
        }
        return mapperUser.fromModel(cliente);
    }

    private Usuario getUsuarioPorId(Long idUsuario) {
        Optional<ClienteEntity> cliente = clienteRepository.findById(idUsuario);
        if (cliente.isEmpty()) {
            Optional<AdminEntity> admin = admRepository.findById(idUsuario);
            if (!admin.isEmpty()) {
                return mapperUser.fromModel(admin.get());
            }
            return new Usuario();
        }
        return mapperUser.fromModel(cliente.get());
    }

}
