/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.config;

import br.com.techchallenge.fiap.neighborfood.adapters.outbound.UserAdapter;
import br.com.techchallenge.fiap.neighborfood.adapters.outbound.repository.jpa.ClienteRepository;
import br.com.techchallenge.fiap.neighborfood.domain.ports.inbound.*;
import br.com.techchallenge.fiap.neighborfood.domain.ports.outbound.*;
import br.com.techchallenge.fiap.neighborfood.domain.usecase.*;
import br.com.techchallenge.fiap.neighborfood.domain.usecase.acompanhachain.AcompanhamentoChain;
import br.com.techchallenge.fiap.neighborfood.domain.usecase.acompanhachain.anemic.AcompanhamentoChainFinalizadoImpl;
import br.com.techchallenge.fiap.neighborfood.domain.usecase.acompanhachain.anemic.AcompanhamentoChainPreparacaoImpl;
import br.com.techchallenge.fiap.neighborfood.domain.usecase.acompanhachain.anemic.AcompanhamentoChainProntoImpl;
import br.com.techchallenge.fiap.neighborfood.domain.usecase.acompanhachain.anemic.AcompanhamentoChainRecebidoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class BeanConfig {


    @Bean
    public AcompanhamentoUseCasePort acompanhamentoUseCaseImpl(PedidoUseCaseAdapterPort pedidoUseCaseAdapterPort, AcompanhamentoChain statusPedidoChain) {
        return new AcompanhamentoUseCaseImpl(pedidoUseCaseAdapterPort, statusPedidoChain);
    }

    @Bean
    public AcompanhamentoChain acompanhamentoChainPronto() {
        return new AcompanhamentoChainProntoImpl();
    }

    @Bean
    @Primary
    public AcompanhamentoChain acompanhamentoChainRecebido() {
        return new AcompanhamentoChainRecebidoImpl();
    }

    @Bean
    public AcompanhamentoChain acompanhamentoChainPreparacao() {
        return new AcompanhamentoChainPreparacaoImpl();
    }

    @Bean
    public AcompanhamentoChain acompanhamentoChainFinalizado() {
        return new AcompanhamentoChainFinalizadoImpl();
    }

    @Bean
    public AdminUseCasePort adminUseCasePort(PedidoUseCaseAdapterPort pedidoUseCaseAdapterPort, UserAdapter userAdapter) {
        return new AdmUseCaseImpl(pedidoUseCaseAdapterPort, userAdapter);
    }

    @Bean
    public ProdutoUseCasePort estoqueUseCasePort(ProdutoUseCaseAdapterPort estoqueUseCaseAdapterPort,
                                                 LoginUseCaseAdapterPort loginAdapter, UserAdapter userdapter) {
        return new ProdutoUseCaseImpl(estoqueUseCaseAdapterPort, loginAdapter, userdapter);
    }

    @Bean
    public LoginUseCasePort loginUseCasePort(LoginUseCaseAdapterPort loginAdapterPort,
                                             NotificationUseCaseAdapterPort notificacaoAdapter,
                                             UserAdapter userAdapter) {
        return new LoginUseCaseImpl(loginAdapterPort, notificacaoAdapter, userAdapter);
    }

    @Bean

    public NotificationUseCasePort notificationUseCasePort(NotificationUseCaseAdapterPort notificacaoAdapterPort, ClienteRepository clienteRepository) {
        return new NotificacaoUseCaseImpl(notificacaoAdapterPort, clienteRepository);
    }

    @Bean
    public PagamentoUseCasePort pagamentoUseCasePort(PedidoUseCaseAdapterPort pedidoUseCaseAdapterPort, AcompanhamentoUseCasePort acompanhamentoUseCasePort) {
        return new PagamentoUseCaseImpl(pedidoUseCaseAdapterPort, acompanhamentoUseCasePort);
    }

    @Bean
    public PedidoUseCasePort pedidoUseCasePort(PedidoUseCaseAdapterPort pedidoUseCaseAdapterPort,
                                               ProdutoUseCaseAdapterPort estoqueUseCaseAdapterPort,
                                               NotificationUseCaseAdapterPort notificationUseCaseAdapterPort,
                                               AcompanhamentoUseCasePort acompanhamentoUseCasePort,
                                               UserAdapter userAdapter) {
        return new PedidoUseCaseImpl(pedidoUseCaseAdapterPort, estoqueUseCaseAdapterPort,
                notificationUseCaseAdapterPort, acompanhamentoUseCasePort, userAdapter);
    }
}
