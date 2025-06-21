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
                cliente.setCreatedAt(Instant.now().toString());
                repository.save(cliente);
                recordsAdded++;
            }
        }
        return recordsAdded;
    }
} 