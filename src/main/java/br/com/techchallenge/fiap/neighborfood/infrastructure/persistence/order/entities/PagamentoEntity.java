/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pagamento")
@SequenceGenerator(name = "pagamento_sequence", initialValue = 1)
public class PagamentoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //@Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "id_pedido")
    private Long idPedido;

    @Column(name = "status")
    private Boolean pagou;
}
