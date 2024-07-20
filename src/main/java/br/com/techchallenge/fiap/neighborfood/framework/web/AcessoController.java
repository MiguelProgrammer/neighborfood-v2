/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.framework.web;


import _generated_sources_swagger_acesso.NeighborfoodApi;
import br.com.techchallenge.fiap.neighborfood.adapter.gateways.AccessGateway;
import br.com.techchallenge.fiap.neighborfood.adapter.inbound.AdminRequest;
import br.com.techchallenge.fiap.neighborfood.adapter.inbound.ClienteRequest;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.AdminRequestDTO;
import br.com.techchallenge.fiap.neighborfood.core.domain.dto.ClienteRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AcessoController implements NeighborfoodApi {

    private AccessGateway accessGateway;

    public AcessoController(AccessGateway accessGateway) {
        this.accessGateway = accessGateway;
    }

    /**
     * POST /neighborfood/login : Se cadastrar, logar
     * Identificação do cliente
     *
     * @param clienteRequestDTO Identifica um cliente logado (optional)
     * @return Usuário logado (status code 200)
     * or request inválida (status code 400)
     */
    @Override
    public ResponseEntity<Object> login(ClienteRequestDTO clienteRequestDTO) {
        return ResponseEntity.ok(
                accessGateway.login(new ClienteRequest().dtoFromDomain(clienteRequestDTO)));
    }

    /**
     * POST /neighborfood/painel/login : Cadastrar adm, logar adm
     * Identificação do adm
     *
     * @param adminRequestDTO Identifica um adminsitrador logado (optional)
     * @return Usuário logado (status code 200)
     * or request inválida (status code 400)
     */
    @Override
    public ResponseEntity<Object> loginAdm(AdminRequestDTO adminRequestDTO) {
        return ResponseEntity.ok(
                accessGateway.login(new AdminRequest().dtoFromDomain(adminRequestDTO)));
    }

    /**
     * POST /neighborfood/cadastro : Se cadastrar, logar
     * Cria cliente
     *
     * @param clienteRequestDTO Cria um novo cliente (optional)
     * @return Usuário logado (status code 200)
     * or request inválida (status code 400)
     */
    @Override
    public ResponseEntity<Object> register(ClienteRequestDTO clienteRequestDTO) {
        return ResponseEntity.ok(
                accessGateway.cadastro(new ClienteRequest().dtoFromDomain(clienteRequestDTO)));
    }

    /**
     * POST /neighborfood/painel/cadastro : Se cadastrar, logar
     * Cria cliente
     *
     * @param adminRequestDTO Cria um novo administrador (optional)
     * @return Usuário cadastrao (status code 200)
     * or request inválida (status code 400)
     */
    @Override
    public ResponseEntity<Object> registerAdm(AdminRequestDTO adminRequestDTO) {
        return ResponseEntity.ok(
                accessGateway.cadastro(new AdminRequest().dtoFromDomain(adminRequestDTO)));
    }

}
