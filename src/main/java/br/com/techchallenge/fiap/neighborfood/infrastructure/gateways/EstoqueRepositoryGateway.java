/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.infrastructure.gateways;

import br.com.techchallenge.fiap.neighborfood.adapter.gateways.EstoqueGateway;
import br.com.techchallenge.fiap.neighborfood.core.domain.pedido.Produto;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.ProdutoRepository;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.entities.ProdutoEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class EstoqueRepositoryGateway implements EstoqueGateway {

    private final ProdutoRepository produtoRepository;

    public EstoqueRepositoryGateway(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void repoemEstoque(Set<Produto> produtos) {
        try {
            produtos.forEach(produto -> {
                produtoRepository.save(produto.dtoFromEntity());
            });
        } catch (RuntimeException ex) {
            System.err.println(ex);
        }
    }


    @Override
    public Produto findById(Long idProduto) {
        Optional<ProdutoEntity> produtoById = produtoRepository.findById(idProduto);
        if (produtoById.isPresent()) {
            return new Produto()
                    .entityFromDto(produtoById.get());
        }
        return new Produto();
    }

    @Override
    public void deleteById(Long idProduto) {
        produtoRepository.deleteById(idProduto);
    }

    @Override
    public void deleteAll(Set<Produto> produtos) {
        produtos.forEach(produto -> {
            produtoRepository.delete(produto.dtoFromEntity());
        });
    }

    @Override
    @Transactional
    public void deleteByNome(String nome) {
        produtoRepository.deleteByNome(nome);
    }

    /**
     * @param idAdmin
     * @return
     */
    @Override
    public Object gerenciaEstoque(Long idAdmin) {
        return null;
    }

}
