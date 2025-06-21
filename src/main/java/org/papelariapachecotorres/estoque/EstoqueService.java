package org.papelariapachecotorres.estoque;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class EstoqueService {
    private final EstoqueMockRepository repository = new EstoqueMockRepository();

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
        return repository.update(id, estoque);
    }

    public boolean delete(int id) {
        return repository.delete(id);
    }
} 