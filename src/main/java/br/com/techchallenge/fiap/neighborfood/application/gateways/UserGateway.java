/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.application.gateways;


import br.com.techchallenge.fiap.neighborfood.core.domain.model.usuario.Usuario;

public interface UserGateway {

    Usuario usuarioById(Long idCliente);
    Usuario usuarioByCpf(String cpf);

}
