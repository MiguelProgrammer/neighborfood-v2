/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.core.domain.model.usuario;

import br.com.techchallenge.fiap.neighborfood.core.domain.model.pedido.Pedido;

import java.util.HashSet;
import java.util.Set;

public class Cliente extends Usuario {

    private Set<Pedido> pedidos = new HashSet<>();

    public Cliente() {
    }

    public Cliente(Long id, String nome, String email, String cpf, Set<Pedido> pedidos) {
        super(id, nome, email, cpf);
        this.pedidos = pedidos;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}

