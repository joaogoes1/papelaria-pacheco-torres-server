package org.papelariapachecotorres.clientes;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ClienteMockRepository {
    private static final List<Cliente> clientes = new ArrayList<>();
    private static final AtomicInteger idCounter = new AtomicInteger(3);

    static {
        clientes.add(new Cliente(1, "João Góes", "123.456.789-00", "Rua das Flores, 123", "(11) 99999-9999", "joao@email.com", "2024-01-15T10:30:00Z"));
        clientes.add(new Cliente(2, "Pedro Arenas", "987.654.321-00", "Av. Principal, 456", "(11) 88888-8888", "maria@email.com", "2024-01-16T14:20:00Z"));
        clientes.add(new Cliente(3, "Frederico", "987.655.321-00", "Av. Principal, 456", "(11) 88881-8888", "freed@email.com", "2024-01-16T14:20:00Z"));
    }

    public List<Cliente> findAll() {
        return new ArrayList<>(clientes);
    }

    public Optional<Cliente> findById(int id) {
        return clientes.stream().filter(c -> c.getId() == id).findFirst();
    }

    public Cliente save(Cliente cliente) {
        cliente.setId(idCounter.incrementAndGet());
        clientes.add(cliente);
        return cliente;
    }

    public Cliente update(int id, Cliente cliente) {
        Optional<Cliente> existing = findById(id);
        if (existing.isPresent()) {
            Cliente c = existing.get();
            c.setNome(cliente.getNome());
            c.setCpf(cliente.getCpf());
            c.setEndereco(cliente.getEndereco());
            c.setTelefone(cliente.getTelefone());
            c.setEmail(cliente.getEmail());
            // createdAt não é alterado
            return c;
        }
        return null;
    }

    public boolean delete(int id) {
        return clientes.removeIf(c -> c.getId() == id);
    }
} 