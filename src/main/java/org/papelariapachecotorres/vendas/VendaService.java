package org.papelariapachecotorres.vendas;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class VendaService {
    private final VendaMockRepository repository = new VendaMockRepository();

    public List<Venda> getAll() {
        return repository.findAll();
    }

    public Optional<Venda> getById(int id) {
        return repository.findById(id);
    }

    public Venda create(Venda venda) {
        return repository.save(venda);
    }

    public Venda update(int id, Venda venda) {
        return repository.update(id, venda);
    }

    public boolean delete(int id) {
        return repository.delete(id);
    }
} 