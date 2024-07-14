/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.config.exception;

import br.com.techchallenge.fiap.neighborfood.infrastructure.gateways.UserRepositoryGateway;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.user.ClienteRepository;
import br.com.techchallenge.fiap.neighborfood.application.gateways.PedidoGateway;
import br.com.techchallenge.fiap.neighborfood.application.usecase.order.acompanhachain.status.AcompanhamentoUseCase;
import br.com.techchallenge.fiap.neighborfood.domain.ports.inbound.*;
import br.com.techchallenge.fiap.neighborfood.domain.ports.outbound.*;
import br.com.techchallenge.fiap.neighborfood.domain.usecase.*;
import br.com.techchallenge.fiap.neighborfood.domain.usecase.acompanhachain.AcompanhamentoChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class BeanConfig {


    @Bean
    public br.com.techchallenge.fiap.neighborfood.domain.ports.inbound.AcompanhamentoUseCasePort acompanhamentoUseCaseImpl(PedidoGateway pedidoGateway, AcompanhamentoChain statusPedidoChain) {
        return new AcompanhamentoUseCase(pedidoGateway, statusPedidoChain);
    }

    @Bean
    public AcompanhamentoChain acompanhamentoChainPronto() {
        return new br.com.techchallenge.fiap.neighborfood.domain.usecase.acompanhachain.anemic.AcompanhamentoChainPronto();
    }

    @Bean
    @Primary
    public AcompanhamentoChain acompanhamentoChainRecebido() {
        return new br.com.techchallenge.fiap.neighborfood.domain.usecase.acompanhachain.anemic.AcompanhamentoChainRecebido();
    }

    @Bean
    public AcompanhamentoChain acompanhamentoChainPreparacao() {
        return new br.com.techchallenge.fiap.neighborfood.domain.usecase.acompanhachain.anemic.AcompanhamentoChainPreparacao();
    }

    @Bean
    public AcompanhamentoChain acompanhamentoChainFinalizado() {
        return new br.com.techchallenge.fiap.neighborfood.domain.usecase.acompanhachain.anemic.AcompanhamentoChainFinalizado();
    }

    @Bean
    public br.com.techchallenge.fiap.neighborfood.domain.ports.inbound.AdminUseCasePort adminUseCasePort(PedidoGateway pedidoGateway, UserRepositoryGateway userRepositoryGateway) {
        return new AdmUseCaseImpl(pedidoGateway, userRepositoryGateway);
    }

    @Bean
    public br.com.techchallenge.fiap.neighborfood.domain.ports.inbound.ProdutoUseCasePort estoqueUseCasePort(ProdutoUseCaseAdapterPort estoqueUseCaseAdapterPort,
                                                                                                             LoginUseCaseAdapterPort loginAdapter, UserRepositoryGateway userdapter) {
        return new ProdutoUseCaseImpl(estoqueUseCaseAdapterPort, loginAdapter, userdapter);
    }

    @Bean
    public LoginUseCasePort loginUseCasePort(LoginUseCaseAdapterPort loginAdapterPort,
                                             NotificationUseCaseAdapterPort notificacaoAdapter,
                                             UserRepositoryGateway userRepositoryGateway) {
        return new LoginUseCaseImpl(loginAdapterPort, notificacaoAdapter, userRepositoryGateway);
    }

    @Bean

    public NotificationUseCasePort notificationUseCasePort(NotificationUseCaseAdapterPort notificacaoAdapterPort, ClienteRepository clienteRepository) {
        return new NotificacaoUseCaseImpl(notificacaoAdapterPort, clienteRepository);
    }

    @Bean
    public PagamentoUseCasePort pagamentoUseCasePort(PedidoGateway pedidoGateway, AcompanhamentoUseCasePort acompanhamentoUseCasePort) {
        return new PagamentoUseCaseImpl(pedidoGateway, acompanhamentoUseCasePort);
    }

    @Bean
    public PedidoUseCasePort pedidoUseCasePort(PedidoGateway pedidoGateway,
                                               ProdutoUseCaseAdapterPort estoqueUseCaseAdapterPort,
                                               NotificationUseCaseAdapterPort notificationUseCaseAdapterPort,
                                               AcompanhamentoUseCasePort acompanhamentoUseCasePort,
                                               UserRepositoryGateway userRepositoryGateway) {
        return new PedidoUseCaseImpl(pedidoGateway, estoqueUseCaseAdapterPort,
                notificationUseCaseAdapterPort, acompanhamentoUseCasePort, userRepositoryGateway);
    }
}
