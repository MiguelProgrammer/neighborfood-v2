package br.com.techchallenge.fiap.neighborfood.config.beans;
/*
 * Copyright (c) 2024. MiguelProgrammer
 */

import br.com.techchallenge.fiap.neighborfood.core.usecase.login.LoginUseCase;
import br.com.techchallenge.fiap.neighborfood.infrastructure.gateways.AcompanhamentoPedidoRepositorioGateway;
import br.com.techchallenge.fiap.neighborfood.infrastructure.gateways.LoginRepositoryGateway;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.PedidoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public LoginUseCase loginController(LoginRepositoryGateway loginRepositoryGateway){
       return new LoginUseCase(loginRepositoryGateway);
    }

    @Bean
    public AcompanhamentoPedidoRepositorioGateway acompanhamentoPedidoRepositorioGateway
            (PedidoRepository pedidoRepository){
        return new AcompanhamentoPedidoRepositorioGateway(pedidoRepository);
    }

}
