package br.com.techchallenge.fiap.neighborfood.config.beans;
/*
 * Copyright (c) 2024. MiguelProgrammer
 */

import br.com.techchallenge.fiap.neighborfood.adapter.gateways.*;
import br.com.techchallenge.fiap.neighborfood.core.usecase.acompanhamento.AcompanhamentoUseCase;
import br.com.techchallenge.fiap.neighborfood.core.usecase.estoque.AdmUseCase;
import br.com.techchallenge.fiap.neighborfood.core.usecase.estoque.EstoqueUseCase;
import br.com.techchallenge.fiap.neighborfood.core.usecase.login.LoginUseCase;
import br.com.techchallenge.fiap.neighborfood.core.usecase.pagamento.PagamentoUseCase;
import br.com.techchallenge.fiap.neighborfood.core.usecase.pedido.PedidoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public PagamentoUseCase configBeanPagamento(PedidoGateway pedidoGateway, AcompanhamentoGateway acompanhamentoGateway) {
        return new PagamentoUseCase(pedidoGateway, acompanhamentoGateway);
    }

    @Bean
    public AcompanhamentoUseCase configBeanAcompanhamento(PedidoGateway pedidoGateway) {
        return new AcompanhamentoUseCase(pedidoGateway);
    }

    @Bean
    public EstoqueUseCase configBeanEstoque(EstoqueGateway estoqueGateway, UserGateway userGateway) {
        return new EstoqueUseCase(estoqueGateway, userGateway);
    }

    @Bean
    public AdmUseCase confiBeanAdmin(PedidoGateway pedidoGateway, UserGateway userGateway) {
        return new AdmUseCase(pedidoGateway, userGateway);
    }

    @Bean
    public PedidoUseCase configBeanPedido(PedidoGateway pedidoGateway, EstoqueGateway produtoGateway,
                                          AcompanhamentoGateway acompanhamentoGateway,
                                          UserGateway userGateway) {
        return new PedidoUseCase(pedidoGateway,produtoGateway, acompanhamentoGateway,
                userGateway);
    }

    @Bean
    public LoginUseCase configBeanAcesso(AccessGateway accessGateway){
        return new LoginUseCase(accessGateway);
    }


}
