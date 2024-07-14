/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order;

import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ItensRepository extends JpaRepository<ItemEntity, Long> {

    ItemEntity findAllById(Long id);

    Set<ItemEntity> findByIdPedido(Long id);
}
