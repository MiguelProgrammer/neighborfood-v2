
/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.adapters.outbound.repository.jpa;

import br.com.techchallenge.fiap.neighborfood.adapters.outbound.entities.MimoEntity;
import br.com.techchallenge.fiap.neighborfood.adapters.outbound.entities.NotificacaoEntity;
import br.com.techchallenge.fiap.neighborfood.domain.model.Mimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacaoRepository extends JpaRepository<NotificacaoEntity, Long> {

    MimoEntity findByIdUsuario(Mimo mimoRequest);
}
