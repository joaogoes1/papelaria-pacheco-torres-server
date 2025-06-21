package org.papelariapachecotorres.produtos;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    private final ProdutoMockRepository repository = new ProdutoMockRepository();

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
        return repository.update(id, produto);
    }

    public boolean delete(int id) {
        return repository.delete(id);
    }
} 