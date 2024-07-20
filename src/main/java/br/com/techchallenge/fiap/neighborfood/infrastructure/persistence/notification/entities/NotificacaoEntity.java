/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.notification.entities;

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
@Table(name = "notificacao")
@SequenceGenerator(name = "notificacao_sequence", initialValue = 1)
public class NotificacaoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //@Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "acao")
    private String acao;

    @Column(name = "descricao")
    private String descricao;
}
