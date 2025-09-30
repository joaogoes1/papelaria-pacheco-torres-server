package org.papelariapachecotorres.estoque;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Estoque> getAllPaginated(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Estoque> findLowStock(Pageable pageable) {
        return repository.findLowStock(pageable);
    }

    public Page<Estoque> searchByProduct(String search, Pageable pageable) {
        return repository.findByProductSearch(search, pageable);
    }

    public Optional<Estoque> getById(UUID id) {
        return repository.findById(id);
    }

    public List<Estoque> getByProdutoId(UUID produtoId) {
        return repository.findByProdutoId(produtoId);
    }

    public Estoque create(Estoque estoque) {
        return repository.save(estoque);
    }

    public Estoque update(UUID id, Estoque estoque) {
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

    public boolean delete(UUID id) {
        Optional<Estoque> estoque = repository.findById(id);
        if (estoque.isPresent()) {
            repository.delete(estoque.get());
            return true;
        }
        return false;
    }
} 