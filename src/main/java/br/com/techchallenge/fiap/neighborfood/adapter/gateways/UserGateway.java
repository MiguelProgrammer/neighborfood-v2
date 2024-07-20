/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.adapter.gateways;


import br.com.techchallenge.fiap.neighborfood.core.domain.usuario.Usuario;

public interface UserGateway {

    Usuario usuarioById(Long idCliente);
    Usuario usuarioByCpf(String cpf);

}
