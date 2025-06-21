package org.papelariapachecotorres.clientes;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private final ClienteMockRepository repository = new ClienteMockRepository();

    public List<Cliente> getAll() {
        return repository.findAll();
    }

    public Optional<Cliente> getById(int id) {
        return repository.findById(id);
    }

    public Cliente create(Cliente cliente) {
        return repository.save(cliente);
    }

    public Cliente update(int id, Cliente cliente) {
        return repository.update(id, cliente);
    }

    public boolean delete(int id) {
        return repository.delete(id);
    }
} 