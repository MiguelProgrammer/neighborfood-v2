/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.domain.usecase;

import br.com.techchallenge.fiap.neighborfood.adapters.outbound.repository.entities.ClienteEntity;
import br.com.techchallenge.fiap.neighborfood.adapters.outbound.repository.entities.MimoEntity;
import br.com.techchallenge.fiap.neighborfood.adapters.outbound.repository.jpa.ClienteRepository;
import br.com.techchallenge.fiap.neighborfood.domain.dto.MimoDTO;
import br.com.techchallenge.fiap.neighborfood.domain.model.Mimo;
import br.com.techchallenge.fiap.neighborfood.domain.model.Notificacao;
import br.com.techchallenge.fiap.neighborfood.domain.ports.inbound.NotificationUseCasePort;
import br.com.techchallenge.fiap.neighborfood.domain.ports.outbound.NotificationUseCaseAdapterPort;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.Random;

public class NotificacaoUseCaseImpl implements NotificationUseCasePort {

    private NotificationUseCaseAdapterPort notificacaoAdapterPort;
    private ClienteRepository clienteRepository;

    public NotificacaoUseCaseImpl(NotificationUseCaseAdapterPort notificacaoAdapterPort, ClienteRepository clienteRepository) {
        this.notificacaoAdapterPort = notificacaoAdapterPort;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public MimoDTO enviaMimosExecute(Mimo mimoRequest) {
        MimoDTO mimoDTO = new MimoDTO();
        Optional<ClienteEntity> cliente = clienteRepository.findById(mimoRequest.getIdUsuario());
        if(cliente.isPresent()){
            Random gerador = new Random();
            mimoRequest.setCodigo(Long.valueOf(String.valueOf(gerador.nextInt(26))));
            mimoRequest.setDescricao("Desconto no próximo pedido. R$ " + new BigDecimal(10.90).setScale(2, RoundingMode.HALF_UP));
            mimoRequest.setIdUsuario(cliente.get().getId());
            Mimo mimo = notificacaoAdapterPort.enviaMimos(mimoRequest);
            mimoDTO.setCodigo(mimo.getCodigo().toString());
            mimoDTO.setDescricao(mimo.getDescricao());
            mimoDTO.setIdCliente(mimo.getIdUsuario());
            mimoDTO.setAcao(mimo.getAcao());

            System.out.println("Mimos enviados para sua próxima compra, obrigado e volte sempre!");
        }
        return mimoDTO;
    }

    @Override
    public Notificacao notificaExecute(Mimo mimoRequest) {
        return null;
    }
}
