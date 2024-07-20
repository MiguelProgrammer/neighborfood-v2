/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.infrastructure.gateways;

import br.com.techchallenge.fiap.neighborfood.adapter.controllers.AcompanhamentoResponse;
import br.com.techchallenge.fiap.neighborfood.adapter.gateways.PedidoGateway;
import br.com.techchallenge.fiap.neighborfood.core.domain.enums.Categoria;
import br.com.techchallenge.fiap.neighborfood.core.domain.enums.Status;
import br.com.techchallenge.fiap.neighborfood.core.domain.pedido.Item;
import br.com.techchallenge.fiap.neighborfood.core.domain.pedido.Pedido;
import br.com.techchallenge.fiap.neighborfood.core.domain.pedido.Produto;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.ItensRepository;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.PagamentoRepository;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.PedidoRepository;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.ProdutoRepository;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.entities.ItemEntity;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.entities.PagamentoEntity;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.entities.PedidoEntity;
import br.com.techchallenge.fiap.neighborfood.infrastructure.persistence.order.entities.ProdutoEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class PedidoRepositoryGateway implements PedidoGateway {

    private final PedidoRepository pedidoRepository;
    private final PagamentoRepository pagamentoRepository;
    private final ProdutoRepository produtoRepository;
    private final ItensRepository itensRepository;

    public PedidoRepositoryGateway(PedidoRepository pedidoRepository, PagamentoRepository pagamentoRepository, ProdutoRepository produtoRepository, ItensRepository itensRepository) {
        this.pedidoRepository = pedidoRepository;
        this.pagamentoRepository = pagamentoRepository;
        this.produtoRepository = produtoRepository;
        this.itensRepository = itensRepository;
    }

    @Override
    public Set<Produto> menuOpcionais(Categoria combo) {
        Set<ProdutoEntity> byCategoria = produtoRepository.findByCategoria(combo);
        return new Produto().setProdutosRequestFromSetEntity(byCategoria);
    }

    @Override
    public AcompanhamentoResponse pedido(Pedido pedido) {
        PedidoEntity entity = new PedidoEntity();
        PedidoEntity updateResponse = new PedidoEntity();

        if (pedido.getStatus().equals(Status.FINALIZADO)) {
            Optional<PedidoEntity> p = pedidoRepository.findById(pedido.getId());
            if (p.isPresent() && pedido.getDataPedidoFim() == null) {
                updateResponse = p.get();
                pedido.setDataPedidoFim(p.get().getDataPedidoFim());
            }
        }

        entity.setId(pedido.getId());
        entity.setDataPedido(pedido.getDataPedido());
        entity.setIdCliente(pedido.getIdCliente());
        entity.setDataPedido(pedido.getDataPedido());
        entity.setDataPedidoFim(pedido.getDataPedidoFim());
        entity.setStatus(pedido.getStatus());
        entity.setTotal(pedido.getTotal());
        pedido.getItensProdutos().forEach(item -> {
            entity.getItensProdutos().add(item.itemDomainFromItemEntity());
        });

        if (!pedido.getStatus().equals(Status.FINALIZADO)) {

            PedidoEntity save = pedidoRepository.saveAndFlush(entity);
            save.getItensProdutos().forEach(item -> {
                item.setIdPedido(save.getId());
            });
            updateResponse = pedidoRepository.saveAndFlush(save);

            AcompanhamentoResponse response = new AcompanhamentoResponse().pedidoEntityFromResponse(updateResponse);
            return response;
        } else {

            AcompanhamentoResponse response = new AcompanhamentoResponse().pedidoEntityFromResponse(pedido.domainFromEntity());
            return response;

        }
    }

    @Override
    public AcompanhamentoResponse atualizarPedido(Pedido pedido) {
        PedidoEntity updateResponse = pedidoRepository.saveAndFlush(pedido.domainFromEntity());
        return new AcompanhamentoResponse().pedidoEntityFromResponse(updateResponse);
    }

    @Override
    public Pedido commitUpdates(PedidoEntity pedidoEntity) {
        PedidoEntity entity = pedidoRepository.findById(pedidoEntity.getId()).get();
        entity = pedidoEntity;
        pedidoRepository.save(entity);
        return new Pedido().entityFromDomain(entity);
    }

    @Override
    public void saveItens(Item item) {
        itensRepository.saveAndFlush(new Pedido().itemDomainFromItemEntity(item));
    }

    @Override
    public void removeItens(Set<Item> itens) {
        itens.forEach(item -> {
            itensRepository.deleteById(item.getId());
        });
    }

    @Override
    public Set<Item> findAllById(Long id) {
        Set<ItemEntity> itens = itensRepository.findByIdPedido(id);
        Set<Item> itensDomain = new HashSet<>();
        itens.forEach(item -> {
            Item produto = new Item();
            produto.setId(item.getId());
            produto.setIdPedido(item.getIdPedido());
            produto.setIdProduto(item.getIdProduto());
            produto.setNome(item.getNome());
            produto.setDescricao(item.getDescricao());
            produto.setCategoria(item.getCategoria());
            produto.setPreco(item.getPreco());
            produto.setImg(item.getImg());
            itensDomain.add(produto);
        });
        return itensDomain;
    }

    @Override
    public Set<Item> findAllByIdPedido(Long id) {
        Set<Item> produtos = new HashSet<>();
        Set<ItemEntity> byIdPedido = itensRepository.findByIdPedido(id);
        byIdPedido.forEach(item -> {
            Item produto = new Item();
            produto.setId(item.getId());
            produto.setIdPedido(item.getIdPedido());
            produto.setIdProduto(item.getIdProduto());
            produto.setNome(item.getNome());
            produto.setDescricao(item.getDescricao());
            produto.setCategoria(item.getCategoria());
            produto.setPreco(item.getPreco());
            produto.setImg(item.getImg());
            produtos.add(produto);
        });
        return produtos;
    }


    @Override
    public Pedido findByIdPedido(Long id) {
        Optional<PedidoEntity> pedidoRepositoryById = pedidoRepository.findById(id);
        pedidoRepositoryById.get().setItensProdutos(itensRepository.findByIdPedido(id));
        return new Pedido().entityFromDomain(pedidoRepositoryById.get());
    }

    @Override
    public Pedido findById(Long id) {
        PedidoEntity entity = pedidoRepository.findById(id).get();
        entity.setItensProdutos(itensRepository.findByIdPedido(entity.getId()));
        return new Pedido().entityFromDomain(entity);
    }

    @Override
    @Transactional
    public void salvaPagamento(PagamentoEntity entity) {
        PagamentoEntity pagamento = new PagamentoEntity();
        pagamento.setPagou(entity.getPagou());
        pagamento.setIdPedido(entity.getIdPedido());
        pagamentoRepository.save(pagamento);
    }

    @Override
    public List<PedidoEntity> pedidos() {
        List<PedidoEntity> pedidos = pedidoRepository.findAll();
        pedidos.forEach(pedido -> {
            pedido.setItensProdutos(itensRepository.findByIdPedido(pedido.getId()));
        });
        return pedidos;
    }
}
