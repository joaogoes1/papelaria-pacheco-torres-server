package org.papelariapachecotorres.clientes;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

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
        Optional<Cliente> existing = repository.findById(id);
        if (existing.isPresent()) {
            Cliente c = existing.get();
            c.setNome(cliente.getNome());
            c.setCpf(cliente.getCpf());
            c.setEndereco(cliente.getEndereco());
            c.setTelefone(cliente.getTelefone());
            c.setEmail(cliente.getEmail());
            return repository.save(c);
        }
        return null;
    }

    public boolean delete(int id) {
        Optional<Cliente> cliente = repository.findById(id);
        if (cliente.isPresent()) {
            repository.delete(cliente.get());
            return true;
        }
        return false;
    }

    public int importClientesCsv(String path) throws IOException {
        int recordsAdded = 0;
        try (Reader in = new FileReader(path);
             CSVParser parser = new CSVParser(in, CSVFormat.DEFAULT.withHeader().withSkipHeaderRecord())) {
            for (CSVRecord record : parser) {
                Cliente cliente = new Cliente();
                cliente.setNome(record.get("nome"));
                cliente.setCpf(record.get("cpf"));
                cliente.setEndereco(record.get("endereco"));
                cliente.setTelefone(record.get("telefone"));
                cliente.setEmail(record.get("email"));
                cliente.setCreatedAt(Instant.now());
                repository.save(cliente);
                recordsAdded++;
            }
        }
        return recordsAdded;
    }
} 