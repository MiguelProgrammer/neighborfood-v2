/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.core.usecase.clientes;

import br.com.techchallenge.fiap.neighborfood.adapter.gateways.NotificacaoGateway;
import br.com.techchallenge.fiap.neighborfood.adapter.gateways.UserGateway;
import br.com.techchallenge.fiap.neighborfood.adapter.presenter.MapperUser;
import br.com.techchallenge.fiap.neighborfood.core.domain.acompanhamento.Mimo;
import br.com.techchallenge.fiap.neighborfood.core.domain.acompanhamento.Notificacao;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.MimoDTO;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.MimoRequestDTO;
import br.com.techchallenge.fiap.neighborfood.core.domain.usuario.Cliente;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@Component
public class NotificacaoUseCase {

    private final NotificacaoGateway notificacao;
    private final UserGateway userGateway;
    private final MapperUser mapperUser = new MapperUser();

    public NotificacaoUseCase(NotificacaoGateway notificacao, UserGateway userGateway) {
        this.notificacao = notificacao;
        this.userGateway = userGateway;
    }

    public MimoDTO enviaMimos(MimoRequestDTO mimoRequest) {
        MimoDTO mimoDTO = new MimoDTO();
        mimoDTO.setIdCliente(mimoRequest.getIdCliente());
        Cliente cliente = (Cliente) userGateway.usuarioById(mimoRequest.getIdCliente());
        if (cliente != null) {
            Random gerador = new Random();
            mimoDTO.setCodigo(String.valueOf(gerador.nextInt(26)));
            mimoDTO.setDescricao("Desconto no próximo pedido. R$ " + new BigDecimal("10.90").setScale(2, RoundingMode.HALF_UP));
            mimoDTO.setIdCliente(cliente.getId());
            Mimo mimo = notificacao.enviaMimos(mimoDTO);
            mimoDTO.setCodigo(mimo.getCodigo().toString());
            mimoDTO.setDescricao(mimo.getDescricao());
            mimoDTO.setIdCliente(mimo.getIdUsuario());
            mimoDTO.setAcao(mimo.getAcao());

            System.out.println("Mimos enviados para sua próxima compra, obrigado e volte sempre!");
        }
        return mimoDTO;
    }

    public Notificacao notifica(Mimo mimoRequest) {
        return null;
    }
}
