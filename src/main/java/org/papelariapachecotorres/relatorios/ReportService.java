package org.papelariapachecotorres.relatorios;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.papelariapachecotorres.clientes.Cliente;
import org.papelariapachecotorres.clientes.ClienteRepository;
import org.papelariapachecotorres.estoque.Estoque;
import org.papelariapachecotorres.estoque.EstoqueRepository;
import org.papelariapachecotorres.vendas.Venda;
import org.papelariapachecotorres.vendas.VendaRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

@Service
public class ReportService {

    private final ClienteRepository clienteRepository;
    private final VendaRepository vendaRepository;
    private final EstoqueRepository estoqueRepository;

    public ReportService(ClienteRepository clienteRepository, VendaRepository vendaRepository, EstoqueRepository estoqueRepository) {
        this.clienteRepository = clienteRepository;
        this.vendaRepository = vendaRepository;
        this.estoqueRepository = estoqueRepository;
    }

    public String exportVendasCsv() throws IOException {
        List<Venda> vendas = vendaRepository.findAll();
        StringWriter writer = new StringWriter();

        try (CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID", "ClienteID", "Total", "Data"))) {
            for (Venda venda : vendas) {
                printer.printRecord(venda.getId(), venda.getClienteId(), venda.getTotal(), venda.getData());
            }
        }
        return writer.toString();
    }

    public String exportClientesCsv() throws IOException {
        List<Cliente> clientes = clienteRepository.findAll();
        StringWriter writer = new StringWriter();

        try (CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID", "Nome", "CPF", "Email"))) {
            for (Cliente cliente : clientes) {
                printer.printRecord(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getEmail());
            }
        }
        return writer.toString();
    }

    public String exportEstoqueCsv() throws IOException {
        List<Estoque> estoques = estoqueRepository.findAll();
        StringWriter writer = new StringWriter();

        try (CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID", "ProdutoID", "Quantidade", "UltimaAtualizacao"))) {
            for (Estoque estoque : estoques) {
                printer.printRecord(estoque.getId(), estoque.getProdutoId(), estoque.getQuantidade(), estoque.getUltimaAtualizacao());
            }
        }
        return writer.toString();
    }
}
