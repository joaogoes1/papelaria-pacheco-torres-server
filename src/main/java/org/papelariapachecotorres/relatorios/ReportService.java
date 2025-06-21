package org.papelariapachecotorres.relatorios;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.papelariapachecotorres.clientes.Cliente;
import org.papelariapachecotorres.clientes.ClienteMockRepository;
import org.papelariapachecotorres.estoque.Estoque;
import org.papelariapachecotorres.estoque.EstoqueMockRepository;
import org.papelariapachecotorres.vendas.Venda;
import org.papelariapachecotorres.vendas.VendaMockRepository;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ReportService {

    private final ClienteMockRepository clienteRepository = new ClienteMockRepository();
    private final VendaMockRepository vendaRepository = new VendaMockRepository();
    private final EstoqueMockRepository estoqueRepository = new EstoqueMockRepository();

    private Path ensureReportDirectory() throws IOException {
        Path reportDir = Paths.get("reports");
        if (!Files.exists(reportDir)) {
            Files.createDirectories(reportDir);
        }
        return reportDir;
    }

    public String exportVendasCsv() throws IOException {
        List<Venda> vendas = vendaRepository.findAll();
        Path reportDir = ensureReportDirectory();
        Path filePath = reportDir.resolve("relatorio_vendas.csv");

        try (FileWriter out = new FileWriter(filePath.toFile());
             CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("ID", "ClienteID", "Total", "Data"))) {
            for (Venda venda : vendas) {
                printer.printRecord(venda.getId(), venda.getClienteId(), venda.getTotal(), venda.getData());
            }
        }
        return filePath.toAbsolutePath().toString();
    }

    public String exportClientesCsv() throws IOException {
        List<Cliente> clientes = clienteRepository.findAll();
        Path reportDir = ensureReportDirectory();
        Path filePath = reportDir.resolve("relatorio_clientes.csv");

        try (FileWriter out = new FileWriter(filePath.toFile());
             CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("ID", "Nome", "CPF", "Email"))) {
            for (Cliente cliente : clientes) {
                printer.printRecord(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getEmail());
            }
        }
        return filePath.toAbsolutePath().toString();
    }

    public String exportEstoqueCsv() throws IOException {
        List<Estoque> estoques = estoqueRepository.findAll();
        Path reportDir = ensureReportDirectory();
        Path filePath = reportDir.resolve("relatorio_estoque.csv");

        try (FileWriter out = new FileWriter(filePath.toFile());
             CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("ID", "ProdutoID", "Quantidade", "UltimaAtualizacao"))) {
            for (Estoque estoque : estoques) {
                printer.printRecord(estoque.getId(), estoque.getProdutoId(), estoque.getQuantidade(), estoque.getUltimaAtualizacao());
            }
        }
        return filePath.toAbsolutePath().toString();
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
                cliente.setCreatedAt(record.get("createdAt"));
                clienteRepository.save(cliente);
                recordsAdded++;
            }
        }
        return recordsAdded;
    }
} 