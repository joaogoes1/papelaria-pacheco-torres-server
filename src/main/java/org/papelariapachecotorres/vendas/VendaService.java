package org.papelariapachecotorres.vendas;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class VendaService {
    private final VendaRepository repository;

    public VendaService(VendaRepository repository) {
        this.repository = repository;
    }

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
        Optional<Venda> existing = repository.findById(id);
        if (existing.isPresent()) {
            Venda v = existing.get();
            v.setClienteId(venda.getClienteId());
            v.setItens(venda.getItens());
            v.setTotal(venda.getTotal());
            v.setData(venda.getData());
            return repository.save(v);
        }
        return null;
    }

    public boolean delete(int id) {
        Optional<Venda> venda = repository.findById(id);
        if (venda.isPresent()) {
            repository.delete(venda.get());
            return true;
        }
        return false;
    }
} 