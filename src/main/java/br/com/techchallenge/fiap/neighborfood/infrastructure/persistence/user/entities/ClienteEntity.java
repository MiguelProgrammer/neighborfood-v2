/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.user.entities;

import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.entities.PedidoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cliente")
@SequenceGenerator(name = "cliente_sequence")
public class ClienteEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //@Setter(AccessLevel.PROTECTED)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "pedidos")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    private Set<PedidoEntity> pedidos = new HashSet<>();
}
