/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.adapter.presenter;

import br.com.techchallenge.fiap.neighborfood.core.domain.usuario.Admin;
import br.com.techchallenge.fiap.neighborfood.core.domain.usuario.Cliente;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.user.entities.AdminEntity;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.user.entities.ClienteEntity;

import java.util.HashSet;

public class MapperUser {

    private AdminEntity entityAdmin = new AdminEntity();
    private Admin admin = new Admin();
    private ClienteEntity entityCliente = new ClienteEntity();
    private Cliente cliente = new Cliente();

    public Cliente fromModel(ClienteEntity entity) {
        if (entity != null) {
            this.cliente.setId(entity.getId());
            this.cliente.setNome(entity.getNome());
            this.cliente.setCpf(entity.getCpf());
            this.cliente.setEmail(entity.getEmail());
        }
        return cliente;
    }

    public ClienteEntity fromEntity(Cliente clienteRequest) {
        this.entityCliente.setNome(clienteRequest.getNome());
        this.entityCliente.setCpf(clienteRequest.getCpf());
        this.entityCliente.setEmail(clienteRequest.getEmail());
        this.entityCliente.setPedidos(new HashSet<>());
        return entityCliente;
    }


    public Admin fromModel(AdminEntity entity) {
        if (entity != null) {
            this.admin.setId(entity.getId());
            this.admin.setNome(entity.getNome());
            this.admin.setCpf(entity.getCpf());
            this.admin.setEmail(entity.getEmail());
            this.admin.setNotificacao(entity.getNotificacao());
        }
        return admin;
    }


    public AdminEntity fromEntity(Admin adminRequest) {
        this.entityAdmin.setNome(adminRequest.getNome());
        this.entityAdmin.setCpf(adminRequest.getCpf());
        this.entityAdmin.setEmail(adminRequest.getEmail());
        return entityAdmin;
    }
}
