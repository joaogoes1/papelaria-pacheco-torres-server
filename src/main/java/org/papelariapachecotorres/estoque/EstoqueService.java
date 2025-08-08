package org.papelariapachecotorres.estoque;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class EstoqueService {
    private final EstoqueRepository repository;

    public EstoqueService(EstoqueRepository repository) {
        this.repository = repository;
    }

    public List<Estoque> getAll() {
        return repository.findAll();
    }

    public Optional<Estoque> getById(int id) {
        return repository.findById(id);
    }

    public List<Estoque> getByProdutoId(int produtoId) {
        return repository.findByProdutoId(produtoId);
    }

    public Estoque create(Estoque estoque) {
        return repository.save(estoque);
    }

    public Estoque update(int id, Estoque estoque) {
        Optional<Estoque> existing = repository.findById(id);
        if (existing.isPresent()) {
            Estoque e = existing.get();
            e.setProdutoId(estoque.getProdutoId());
            e.setQuantidade(estoque.getQuantidade());
            e.setQuantidadeMinima(estoque.getQuantidadeMinima());
            e.setUltimaAtualizacao(estoque.getUltimaAtualizacao());
            return repository.save(e);
        }
        return null;
    }

    public boolean delete(int id) {
        Optional<Estoque> estoque = repository.findById(id);
        if (estoque.isPresent()) {
            repository.delete(estoque.get());
            return true;
        }
        return false;
    }
} 