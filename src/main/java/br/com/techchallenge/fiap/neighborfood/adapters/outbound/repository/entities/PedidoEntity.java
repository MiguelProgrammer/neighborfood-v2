/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.adapters.outbound.repository.entities;

import br.com.techchallenge.fiap.neighborfood.domain.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedido")
@SequenceGenerator(name = "pedido_sequence", initialValue = 1)
public class  PedidoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_pedido")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "id_cliente")
    private Long idCliente;

    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @OneToMany(mappedBy = "id")
    private Set<ItemEntity> itensProdutos = new HashSet<>();

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "total")
    private BigDecimal total = BigDecimal.ZERO;

    @Column(name = "data_pedido")
    private Date dataPedido;

    @Column(name = "data_pedido_fim")
    private Date dataPedidoFim;
}
