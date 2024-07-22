/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.infrastructure.gateways;

import br.com.techchallenge.fiap.neighborfood.adapter.gateways.NotificacaoGateway;
import br.com.techchallenge.fiap.neighborfood.core.domain.acompanhamento.Mimo;
import br.com.techchallenge.fiap.neighborfood.core.domain.acompanhamento.Notificacao;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.MimoDTO;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.notification.MimoRepository;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.notification.NotificacaoRepository;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.notification.entities.MimoEntity;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.notification.entities.NotificacaoEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotificacaoRepositoryGateway implements NotificacaoGateway {

    private final NotificacaoRepository notificacaoRepository;
    private final MimoRepository mimoRepository;

    public NotificacaoRepositoryGateway(NotificacaoRepository notificacaoRepository, MimoRepository mimoRepository) {
        this.notificacaoRepository = notificacaoRepository;
        this.mimoRepository = mimoRepository;
    }

    @Override
    @Transactional
    public Mimo enviaMimos(MimoDTO mimoRequest) {
        MimoEntity entity = new MimoEntity();
        entity.setIdUsuario(mimoRequest.getIdCliente());
        entity.setCodigo(Long.valueOf(mimoRequest.getCodigo()));
        entity.setDescricao(mimoRequest.getDescricao());
        MimoEntity save = mimoRepository.save(entity);
        return new Mimo().fromModel(save);
    }


    @Override
    public Notificacao notifica(Notificacao notificacao) {
        Notificacao alerta = new Notificacao();
        NotificacaoEntity save = notificacaoRepository.save(alerta.fromEntity(notificacao));
        return alerta.entityfromDomain(save);
    }


    @Override
    public List<Notificacao> findAll() {
        List<Notificacao> lista = new ArrayList<>();
        notificacaoRepository.findAll().forEach(sms -> {
            lista.add(new Notificacao().entityfromDomain(sms));
        });
        return lista;
    }
}
