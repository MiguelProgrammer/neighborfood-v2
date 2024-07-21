/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.infrastructure.gateways;

import br.com.techchallenge.fiap.neighborfood.adapter.gateways.AccessGateway;
import br.com.techchallenge.fiap.neighborfood.adapter.inbound.UsuarioRequest;
import br.com.techchallenge.fiap.neighborfood.adapter.presenter.MapperUser;
import br.com.techchallenge.fiap.neighborfood.core.domain.usuario.Admin;
import br.com.techchallenge.fiap.neighborfood.core.domain.usuario.Cliente;
import br.com.techchallenge.fiap.neighborfood.core.domain.usuario.Usuario;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.user.AdmRepository;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.user.ClienteRepository;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.user.entities.AdminEntity;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.user.entities.ClienteEntity;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

@Component
public class LoginRepositoryGateway implements AccessGateway {

    private final AdmRepository admRepository;
    private final ClienteRepository clienteRepository;
    private MapperUser mapperUser;

    public LoginRepositoryGateway(AdmRepository admRepository, ClienteRepository clienteRepository) {
        this.admRepository = admRepository;
        this.clienteRepository = clienteRepository;
        mapperUser = new MapperUser();
    }

    @Override
    public Usuario login(UsuarioRequest request) {
        return this.getUsuario(this.requestToDomain(request));
    }

    @Override
    public Usuario cadastro(UsuarioRequest request) {
        return this.registerUsuario(this.requestToDomain(request));
    }

    /**
     * @param request
     * @return
     */
    @Override
    public Usuario cadastroAdm(UsuarioRequest request) {
        Usuario usuario = this.requestToDomain(request);
        Admin admin = this.requestToDomainAdmin(usuario);
        return this.registerUsuario(admin);
    }


    private Usuario registerUsuario(Usuario request) {

        Usuario usuario;

        if (request instanceof Admin adminRequest) {
            AdminEntity admin = mapperUser.fromEntity(adminRequest);
            admRepository.save(admin);
            usuario = mapperUser.fromModel(admin);
        } else {
            Cliente clienteDomain = this.requestToDomainCliente(request);
            ClienteEntity clienteEntity = mapperUser.fromEntity(clienteDomain);
            clienteRepository.save(clienteEntity);
            usuario = mapperUser.fromModel(clienteEntity);
        }

        return usuario;
    }


    private Usuario getUsuario(Usuario request) {

        Usuario usuario = new Usuario();
        ClienteEntity cliente = clienteRepository.findByCpf(request.getCpf());

        if (!ObjectUtils.isEmpty(cliente)) {
            usuario = mapperUser.fromModel(cliente);
        } else {
            AdminEntity admin = admRepository.findByCpf(request.getCpf());
            if (!ObjectUtils.isEmpty(admin)) {
                usuario = mapperUser.fromModel(admin);
            }
        }
        return usuario;
    }


    /**
     * MAPEIA REQUEST PARA DOMINIO
     * @param request
     * @return
     */
    private Usuario requestToDomain(UsuarioRequest request){
        Usuario usuario = new Usuario();
        usuario.setCpf(request.getCpf());
        usuario.setEmail(request.getEmail());
        usuario.setNome(request.getNome());
        return usuario;
    }

    /**
     * MAPEIA REQUEST PARA DOMINIO CLIENTE
     * @param request
     * @return
     */
    private Cliente requestToDomainCliente(Usuario request){
        Cliente cliente = new Cliente();
        cliente.setCpf(request.getCpf());
        cliente.setEmail(request.getEmail());
        cliente.setNome(request.getNome());
        return cliente;
    }

    /**
     * MAPEIA REQUEST PARA DOMINIO ADMIN
     * @param request
     * @return
     */
    private Admin requestToDomainAdmin(Usuario request){
        Admin admin = new Admin();
        admin.setCpf(request.getCpf());
        admin.setEmail(request.getEmail());
        admin.setNome(request.getNome());
        return admin;
    }

}
