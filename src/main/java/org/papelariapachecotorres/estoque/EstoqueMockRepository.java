package org.papelariapachecotorres.estoque;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class EstoqueMockRepository {
    private static final List<Estoque> estoques = new ArrayList<>();
    private static final AtomicInteger idCounter = new AtomicInteger(3);

    static {
        estoques.add(new Estoque(1, 1, 50, 10, "2024-01-15T08:00:00Z"));
        estoques.add(new Estoque(2, 2, 25, 5, "2024-01-16T10:15:00Z"));
        estoques.add(new Estoque(3, 3, 100, 20, "2024-01-17T07:30:00Z"));
    }

    public List<Estoque> findAll() {
        return new ArrayList<>(estoques);
    }

    public Optional<Estoque> findById(int id) {
        return estoques.stream().filter(e -> e.getId() == id).findFirst();
    }

    public List<Estoque> findByProdutoId(int produtoId) {
        List<Estoque> result = new ArrayList<>();
        for (Estoque e : estoques) {
            if (e.getProdutoId().equals(produtoId)) {
                result.add(e);
            }
        }
        return result;
    }

    public Estoque save(Estoque estoque) {
        estoque.setId(idCounter.incrementAndGet());
        estoques.add(estoque);
        return estoque;
    }

    public Estoque update(int id, Estoque estoque) {
        Optional<Estoque> existing = findById(id);
        if (existing.isPresent()) {
            Estoque e = existing.get();
            e.setProdutoId(estoque.getProdutoId());
            e.setQuantidade(estoque.getQuantidade());
            e.setQuantidadeMinima(estoque.getQuantidadeMinima());
            e.setUltimaAtualizacao(estoque.getUltimaAtualizacao());
            return e;
        }
        return null;
    }

    public boolean delete(int id) {
        return estoques.removeIf(e -> e.getId() == id);
    }
} 