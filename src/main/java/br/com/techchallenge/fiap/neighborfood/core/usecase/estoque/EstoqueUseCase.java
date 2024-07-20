/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.core.usecase.estoque;

import br.com.techchallenge.fiap.neighborfood.core.domain.enums.Categoria;
import br.com.techchallenge.fiap.neighborfood.core.domain.pedido.Produto;
import br.com.techchallenge.fiap.neighborfood.infrastructure.gateways.EstoqueRepositoryGateway;
import br.com.techchallenge.fiap.neighborfood.infrastructure.gateways.UserRepositoryGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class EstoqueUseCase {


    private final EstoqueRepositoryGateway produtoRepositoryGateway;
    private final UserRepositoryGateway userRepositoryGateway;

    public EstoqueUseCase(EstoqueRepositoryGateway produtoRepositoryGateway, UserRepositoryGateway userRepositoryGateway) {
        this.produtoRepositoryGateway = produtoRepositoryGateway;
        this.userRepositoryGateway = userRepositoryGateway;
    }

    public Object gerenciaEstoque(Long idAmdin) {
        Set<Produto> listaProdutos = new HashSet<>();

        if (userRepositoryGateway.usuarioById(idAmdin).getId() != null) {

            /**
             * MOCK PRODUCTS
             */
            Produto produtoLanche = new Produto();
            produtoLanche.setNome("Hambuguer N*1");
            produtoLanche.setDescricao("Hambuguer com duas carnes e molho");
            produtoLanche.setCategoria(Categoria.LANCHE);
            produtoLanche.setPreco(new BigDecimal("14.89"));
            produtoLanche.setImg("https://www.designi.com.br/images/preview/11052407.jpg");
            listaProdutos.add(produtoLanche);

            Produto produtoBebida = new Produto();
            produtoBebida.setNome("Coca Cola 600ml");
            produtoBebida.setDescricao("Refrigerante cola 600ml garrafa");
            produtoBebida.setCategoria(Categoria.BEBIDA);
            produtoBebida.setPreco(new BigDecimal("9.90"));
            produtoBebida.setImg("https://w7.pngwing.com/pngs/574/913/png-transparent-caffeine-free-coca-cola-soft-drink-coca-cola-coca-cola-bottle-glass-beer-bottle-cola-thumbnail.png");
            listaProdutos.add(produtoBebida);

            Produto produtoAcompanha = new Produto();
            produtoAcompanha.setNome("Batata Frita");
            produtoAcompanha.setDescricao("Batata fritas média");
            produtoAcompanha.setCategoria(Categoria.ACOMPANHAMENTO);
            produtoAcompanha.setPreco(new BigDecimal("7.00"));
            produtoAcompanha.setImg("https://www.designi.com.br/images/preview/10001736.jpg");
            listaProdutos.add(produtoAcompanha);

            Produto produtoSobremesa = new Produto();
            produtoSobremesa.setNome("Sorvete de casquinha");
            produtoSobremesa.setDescricao("Sorvete de casquinha meio a meio");
            produtoSobremesa.setCategoria(Categoria.SOBREMESA);
            produtoSobremesa.setPreco(new BigDecimal("4.90"));
            produtoSobremesa.setImg("https://i.pinimg.com/originals/9a/25/9e/9a259efb922ed0e05b46b73373777adc.png");
            listaProdutos.add(produtoSobremesa);

            this.repoemEstoqueExecute(listaProdutos);

            final String CADASTRO_PRODUTOS = "-- NEIGHBORFOOD --\n "
                    + listaProdutos.size() + " novos produtos foram cadastrados no estoque \n" +
                    "-- NEIGHBORFOOD --";

            log.info(CADASTRO_PRODUTOS);
            return ResponseEntity.ok(CADASTRO_PRODUTOS);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado ou não possui permissão!");
        }
    }


    public void repoemEstoqueExecute(Set<Produto> produtos) {
        produtoRepositoryGateway.repoemEstoque(produtos);
    }

    public Object findById(Long idProduto) {
        return produtoRepositoryGateway.findById(idProduto);
    }

    public void deleteById(Long idProduto) {
        produtoRepositoryGateway.deleteById(idProduto);
    }

    public void deleteAllExecute(Set<Produto> produtos) {
        produtoRepositoryGateway.deleteAll(produtos);
    }
}
