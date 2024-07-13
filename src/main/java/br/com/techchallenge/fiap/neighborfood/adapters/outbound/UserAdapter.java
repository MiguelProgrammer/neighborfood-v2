/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.adapters.outbound;

import br.com.techchallenge.fiap.neighborfood.adapters.outbound.entities.AdminEntity;
import br.com.techchallenge.fiap.neighborfood.adapters.outbound.entities.ClienteEntity;
import br.com.techchallenge.fiap.neighborfood.adapters.outbound.repository.jpa.AdmRepository;
import br.com.techchallenge.fiap.neighborfood.adapters.outbound.repository.jpa.ClienteRepository;
import br.com.techchallenge.fiap.neighborfood.domain.model.Admin;
import br.com.techchallenge.fiap.neighborfood.domain.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class UserAdapter {

    private AdmRepository admRepository;
    private ClienteRepository clienteRepository;

    public UserAdapter(AdmRepository admRepository, ClienteRepository clienteRepository) {
        this.admRepository = admRepository;
        this.clienteRepository = clienteRepository;
    }

    public Cliente clienteById(Long idCliente) {
        ClienteEntity entity = clienteRepository.findById(idCliente).get();
        return new Cliente().fromModel(entity);
    }

    public Cliente clienteByCpf(String cpf) {
        return new Cliente().fromModel(clienteRepository.findByCpf(cpf));
    }

    public Admin adminByCpf(String cpf) {
        return new Admin().fromModel(admRepository.findByCpf(cpf));
    }

    public Admin adminById(Long idAdmin) {
        return new Admin().fromModel(admRepository.findById(idAdmin).orElse(new AdminEntity()));
    }

}
