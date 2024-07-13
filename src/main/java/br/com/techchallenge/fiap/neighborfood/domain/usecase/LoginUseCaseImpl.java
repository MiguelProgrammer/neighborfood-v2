/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.domain.usecase;

import br.com.techchallenge.fiap.neighborfood.adapters.inbound.request.AdminRequest;
import br.com.techchallenge.fiap.neighborfood.adapters.inbound.request.ClienteRequest;
import br.com.techchallenge.fiap.neighborfood.adapters.outbound.UserAdapter;
import br.com.techchallenge.fiap.neighborfood.config.exception.AdminException;
import br.com.techchallenge.fiap.neighborfood.config.exception.ClienteException;
import br.com.techchallenge.fiap.neighborfood.domain.model.Admin;
import br.com.techchallenge.fiap.neighborfood.domain.model.Cliente;
import br.com.techchallenge.fiap.neighborfood.domain.ports.inbound.LoginUseCasePort;
import br.com.techchallenge.fiap.neighborfood.domain.ports.outbound.LoginUseCaseAdapterPort;
import br.com.techchallenge.fiap.neighborfood.domain.ports.outbound.NotificationUseCaseAdapterPort;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginUseCaseImpl implements LoginUseCasePort {

    private LoginUseCaseAdapterPort loginAdapterPort;
    private NotificationUseCaseAdapterPort notificacaoAdapter;
    private UserAdapter userAdapter;

    private final String MESSAGE = "Usuário não encontrado ou não cadastrado!\n\n" +
            "Por favor, verifique as informações inseridas.";
    private final String MESSAGE_SUCCESS = "Cliente cadastrado com sucesso!";


    public LoginUseCaseImpl(LoginUseCaseAdapterPort loginAdapterPort, NotificationUseCaseAdapterPort notificacaoAdapter, UserAdapter userAdapter) {
        this.loginAdapterPort = loginAdapterPort;
        this.notificacaoAdapter = notificacaoAdapter;
        this.userAdapter = userAdapter;
    }

    @Override
    public Cliente loginExecute(ClienteRequest clienteRequest) {
        Cliente cliente = new Cliente();
        try {
            cliente = loginAdapterPort.login(clienteRequest);

            if (cliente.getId() == null) {
                throw new ClienteException(MESSAGE);
            }
        } catch (ClienteException ex) {
            System.err.println(ex.getMessage());
        }
        return cliente;
    }

    @Override
    public Cliente cadastroExecute(ClienteRequest clienteRequest) {

        Cliente clienteCadastrado = new Cliente();
        try {
            clienteCadastrado = this.loginExecute(clienteRequest);
            if (naoCadastrado(clienteCadastrado)) {
                clienteCadastrado = loginAdapterPort.cadastro(clienteRequest);
                log.info(MESSAGE_SUCCESS);
            }
        } catch (ClienteException ex) {
            System.err.println(ex.getMessage());
        }
        return clienteCadastrado;
    }


    @Override
    public Admin loginAdmExecute(AdminRequest adminRequest) {

        Admin admin = new Admin();

        try {
            admin = loginAdapterPort.loginAdm(adminRequest);
            if (admin.getId() == null) {
                throw new AdminException(MESSAGE);
            } else {
                admin.setNotificacao(notificacaoAdapter.findAll().toString());
            }

        } catch (AdminException ex) {
            System.err.println(ex.getMessage());
        }
        return admin;
    }


    @Override
    public Admin cadastroAdmExecute(AdminRequest adminRequest) {
        Admin admin = new Admin();
        try {
            admin = this.loginAdmExecute(adminRequest);
            if (naoCadastrado(admin)) {
                admin = loginAdapterPort.cadastroAdm(adminRequest);
                log.info("ADM CADASTRADO.");
            }
        } catch (AdminException ex) {
            System.err.println(ex.getMessage());
        }
        return admin;
    }


    private static boolean naoCadastrado(Cliente cadastro) {
        return cadastro.getCpf() == null
                && cadastro.getId() == null
                && cadastro.getNome() == null
                && cadastro.getEmail() == null;
    }

    private static boolean naoCadastrado(Admin cadastro) {
        return cadastro.getCpf() == null
                && cadastro.getId() == null
                && cadastro.getNome() == null
                && cadastro.getEmail() == null;
    }

}
