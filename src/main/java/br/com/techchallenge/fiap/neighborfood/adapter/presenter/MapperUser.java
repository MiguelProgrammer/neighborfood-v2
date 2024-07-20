/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.adapter.presenter;

import br.com.techchallenge.fiap.neighborfood.adapter.inbound.AdminRequest;
import br.com.techchallenge.fiap.neighborfood.adapter.inbound.ClienteRequest;
import br.com.techchallenge.fiap.neighborfood.core.domain.usuario.Admin;
import br.com.techchallenge.fiap.neighborfood.core.domain.usuario.Cliente;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.user.entities.AdminEntity;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.user.entities.ClienteEntity;

import java.util.HashSet;

public class MapperUser {

    AdminRequest requestAdmin = new AdminRequest();
    private AdminEntity entityAdmin = new AdminEntity();
    private Admin admin = new Admin();
    private ClienteEntity entityCliente = new ClienteEntity();
    private Cliente cliente = new Cliente();

    public Cliente fromModel(ClienteEntity entity) {
        if (entity != null) {
            cliente.setId(entity.getId());
            cliente.setNome(entity.getNome());
            cliente.setCpf(entity.getCpf());
            cliente.setEmail(entity.getEmail());
        }
        return cliente;
    }

    public ClienteEntity fromEntity(ClienteRequest clienteRequest) {
        entityCliente.setNome(clienteRequest.getNome());
        entityCliente.setCpf(clienteRequest.getCpf());
        entityCliente.setEmail(clienteRequest.getEmail());
        entityCliente.setPedidos(new HashSet<>());
        return entityCliente;
    }


    public Admin fromModel(AdminEntity entity) {
        if (entity != null) {
            admin.setId(entity.getId());
            admin.setNome(entity.getNome());
            admin.setCpf(entity.getCpf());
            admin.setEmail(entity.getEmail());
            admin.setNotificacao(entity.getNotificacao());
        }
        return admin;
    }

    public AdminRequest domainFromRequest(Admin admin) {
        requestAdmin.setNome(admin.getNome());
        requestAdmin.setCpf(admin.getCpf());
        requestAdmin.setEmail(admin.getEmail());
        return requestAdmin;
    }

    public AdminEntity fromEntity(AdminRequest adminRequest) {
        entityAdmin.setNome(adminRequest.getNome());
        entityAdmin.setCpf(adminRequest.getCpf());
        entityAdmin.setEmail(adminRequest.getEmail());
        return entityAdmin;
    }
}
