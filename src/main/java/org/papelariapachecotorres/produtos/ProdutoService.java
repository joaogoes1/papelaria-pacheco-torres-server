package org.papelariapachecotorres.produtos;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> getAll() {
        return repository.findAll();
    }

    public Page<Produto> getAllPaginated(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Produto> searchPaginated(String search, String categoria, Pageable pageable) {
        if (search != null && !search.trim().isEmpty()) {
            return repository.findBySearchAndCategory(search, categoria, pageable);
        } else if (categoria != null) {
            return repository.findByCategoria(categoria, pageable);
        }
        return repository.findAll(pageable);
    }

    public Optional<Produto> getById(UUID id) {
        return repository.findById(id);
    }

    public Produto create(Produto produto) {
        return repository.save(produto);
    }

    public Produto update(UUID id, Produto produto) {
        Optional<Produto> existing = repository.findById(id);
        if (existing.isPresent()) {
            Produto p = existing.get();
            p.setNome(produto.getNome());
            p.setCodigo(produto.getCodigo());
            p.setPreco(produto.getPreco());
            p.setCategoria(produto.getCategoria());
            p.setDescricao(produto.getDescricao());
            p.setCreatedAt(Instant.now());
            return repository.save(p);
        }
        return null;
    }

    public boolean delete(UUID id) {
        Optional<Produto> produto = repository.findById(id);
        if (produto.isPresent()) {
            repository.delete(produto.get());
            return true;
        }
        return false;
    }
} 