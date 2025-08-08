package org.papelariapachecotorres.produtos;

import java.util.List;
import java.util.Optional;

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

    public Optional<Produto> getById(int id) {
        return repository.findById(id);
    }

    public Produto create(Produto produto) {
        return repository.save(produto);
    }

    public Produto update(int id, Produto produto) {
        Optional<Produto> existing = repository.findById(id);
        if (existing.isPresent()) {
            Produto p = existing.get();
            p.setNome(produto.getNome());
            p.setCodigo(produto.getCodigo());
            p.setPreco(produto.getPreco());
            p.setCategoria(produto.getCategoria());
            p.setDescricao(produto.getDescricao());
            return repository.save(p);
        }
        return null;
    }

    public boolean delete(int id) {
        Optional<Produto> produto = repository.findById(id);
        if (produto.isPresent()) {
            repository.delete(produto.get());
            return true;
        }
        return false;
    }
} 