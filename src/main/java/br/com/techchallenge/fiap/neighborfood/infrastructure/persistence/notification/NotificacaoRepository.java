
/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.notification;

import br.com.techchallenge.fiap.neighborfood.domain.model.Mimo;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.notification.entities.MimoEntity;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.notification.entities.NotificacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacaoRepository extends JpaRepository<NotificacaoEntity, Long> {

    MimoEntity findByIdUsuario(Mimo mimoRequest);
}
