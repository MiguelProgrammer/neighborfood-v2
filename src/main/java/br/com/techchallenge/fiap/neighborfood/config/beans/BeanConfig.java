package br.com.techchallenge.fiap.neighborfood.config.beans;
/*
 * Copyright (c) 2024. MiguelProgrammer
 */

import br.com.techchallenge.fiap.neighborfood.infrastructure.gateways.AcompanhamentoPedidoRepositorioGateway;
import br.com.techchallenge.fiap.neighborfood.infrastructure.gateways.LoginRepositoryGateway;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.PedidoRepository;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.user.AdmRepository;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.user.ClienteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public LoginRepositoryGateway loginRepositoryGateway(
            AdmRepository admRepository, ClienteRepository clienteRepository) {
       return new LoginRepositoryGateway(admRepository, clienteRepository);
    }

    @Bean
    public AcompanhamentoPedidoRepositorioGateway acompanhamentoPedidoRepositorioGateway
            (PedidoRepository pedidoRepository){
        return new AcompanhamentoPedidoRepositorioGateway(pedidoRepository);
    }

}
