/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.infrastructure.gateways;

import br.com.techchallenge.fiap.neighborfood.adapters.inbound.response.AcompanhamentoResponse;
import br.com.techchallenge.fiap.neighborfood.application.gateways.AdminGateway;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminARepositoryGateway implements AdminGateway {

    private final AdminGateway adminGateway;

    public AdminARepositoryGateway(UserRepositoryGateway userRepositoryGateway, AdminGateway adminGateway) {
        this.adminGateway = adminGateway;
    }

    @Override
    public List<AcompanhamentoResponse> listaPedidos(Long idAdmin) {
        return adminGateway.listaPedidos(idAdmin);
    }
}
