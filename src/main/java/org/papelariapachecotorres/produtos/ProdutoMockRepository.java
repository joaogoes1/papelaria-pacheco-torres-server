package org.papelariapachecotorres.produtos;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ProdutoMockRepository {
    private static final List<Produto> produtos = new ArrayList<>();
    private static final AtomicInteger idCounter = new AtomicInteger(3);

    static {
        produtos.add(new Produto(1, "Caneta Azul", "CAN001", 2.50, "Escritório", "Caneta esferográfica azul", "2024-01-10T09:15:00Z"));
        produtos.add(new Produto(2, "Caderno Universitário", "CAD001", 15.90, "Papelaria", "Caderno 96 folhas", "2024-01-11T11:45:00Z"));
        produtos.add(new Produto(3, "Lápis HB", "LAP001", 1.25, "Escritório", "Lápis grafite HB", "2024-01-12T16:30:00Z"));
    }

    public List<Produto> findAll() {
        return new ArrayList<>(produtos);
    }

    public Optional<Produto> findById(int id) {
        return produtos.stream().filter(p -> p.getId() == id).findFirst();
    }

    public Produto save(Produto produto) {
        produto.setId(idCounter.incrementAndGet());
        produtos.add(produto);
        return produto;
    }

    public Produto update(int id, Produto produto) {
        Optional<Produto> existing = findById(id);
        if (existing.isPresent()) {
            Produto p = existing.get();
            p.setNome(produto.getNome());
            p.setCodigo(produto.getCodigo());
            p.setPreco(produto.getPreco());
            p.setCategoria(produto.getCategoria());
            p.setDescricao(produto.getDescricao());
            // createdAt não é alterado
            return p;
        }
        return null;
    }

    public boolean delete(int id) {
        return produtos.removeIf(p -> p.getId() == id);
    }
} 