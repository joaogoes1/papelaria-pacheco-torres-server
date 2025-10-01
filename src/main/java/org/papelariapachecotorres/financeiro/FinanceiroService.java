package org.papelariapachecotorres.financeiro;

import org.papelariapachecotorres.clientes.Cliente;
import org.papelariapachecotorres.clientes.ClienteRepository;
import org.papelariapachecotorres.produtos.Produto;
import org.papelariapachecotorres.produtos.ProdutoRepository;
import org.papelariapachecotorres.vendas.Venda;
import org.papelariapachecotorres.vendas.VendaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FinanceiroService {
    private final VendaRepository vendaRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public FinanceiroService(VendaRepository vendaRepository,
                           ClienteRepository clienteRepository,
                           ProdutoRepository produtoRepository) {
        this.vendaRepository = vendaRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    public FinanceiroDTO getResumoFinanceiro() {
        List<Venda> todasVendas = vendaRepository.findAll();

        // Receita Total
        BigDecimal receitaTotal = todasVendas.stream()
            .map(Venda::getTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Vendas do mês atual
        LocalDate hoje = LocalDate.now();
        LocalDate inicioMes = hoje.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate fimMes = hoje.with(TemporalAdjusters.lastDayOfMonth());

        Instant inicioMesInstant = inicioMes.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant fimMesInstant = fimMes.atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant();

        List<Venda> vendasMesAtual = todasVendas.stream()
            .filter(v -> !v.getData().isBefore(inicioMesInstant) && !v.getData().isAfter(fimMesInstant))
            .collect(Collectors.toList());

        BigDecimal receitaMensal = vendasMesAtual.stream()
            .map(Venda::getTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Vendas de hoje
        Instant inicioHoje = hoje.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant fimHoje = hoje.atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant();

        BigDecimal receitaDiaria = todasVendas.stream()
            .filter(v -> !v.getData().isBefore(inicioHoje) && !v.getData().isAfter(fimHoje))
            .map(Venda::getTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Ticket Médio
        BigDecimal ticketMedio = todasVendas.isEmpty()
            ? BigDecimal.ZERO
            : receitaTotal.divide(BigDecimal.valueOf(todasVendas.size()), 2, RoundingMode.HALF_UP);

        // Total de vendas
        Integer totalVendas = todasVendas.size();
        Integer totalVendasMes = vendasMesAtual.size();

        // Crescimento Mensal (Comparar com mês anterior)
        LocalDate inicioMesAnterior = hoje.minusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
        LocalDate fimMesAnterior = hoje.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

        Instant inicioMesAnteriorInstant = inicioMesAnterior.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant fimMesAnteriorInstant = fimMesAnterior.atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant();

        BigDecimal receitaMesAnterior = todasVendas.stream()
            .filter(v -> !v.getData().isBefore(inicioMesAnteriorInstant) && !v.getData().isAfter(fimMesAnteriorInstant))
            .map(Venda::getTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal crescimentoMensal = BigDecimal.ZERO;
        if (receitaMesAnterior.compareTo(BigDecimal.ZERO) > 0) {
            crescimentoMensal = receitaMensal.subtract(receitaMesAnterior)
                .divide(receitaMesAnterior, 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));
        }

        // Receita por Mês (últimos 12 meses)
        List<FinanceiroDTO.ReceitaMensalDTO> receitaPorMes = calcularReceitaPorMes(todasVendas);

        // Top 10 Clientes
        List<FinanceiroDTO.TopClienteDTO> topClientes = calcularTopClientes(todasVendas);

        // Vendas por Categoria
        List<FinanceiroDTO.VendaPorCategoriaDTO> vendasPorCategoria = calcularVendasPorCategoria(todasVendas);

        return new FinanceiroDTO(
            receitaTotal,
            receitaMensal,
            receitaDiaria,
            ticketMedio,
            totalVendas,
            totalVendasMes,
            crescimentoMensal,
            receitaPorMes,
            topClientes,
            vendasPorCategoria
        );
    }

    private List<FinanceiroDTO.ReceitaMensalDTO> calcularReceitaPorMes(List<Venda> vendas) {
        Map<String, FinanceiroDTO.ReceitaMensalDTO> receitaPorMes = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        for (Venda venda : vendas) {
            LocalDate dataVenda = venda.getData().atZone(ZoneId.systemDefault()).toLocalDate();
            String mesAno = dataVenda.format(formatter);

            receitaPorMes.computeIfAbsent(mesAno, k ->
                new FinanceiroDTO.ReceitaMensalDTO(mesAno, BigDecimal.ZERO, 0));

            FinanceiroDTO.ReceitaMensalDTO dto = receitaPorMes.get(mesAno);
            dto.setReceita(dto.getReceita().add(venda.getTotal()));
            dto.setTotalVendas(dto.getTotalVendas() + 1);
        }

        // Retornar últimos 12 meses, ordenados
        return receitaPorMes.values().stream()
            .sorted(Comparator.comparing(FinanceiroDTO.ReceitaMensalDTO::getMes).reversed())
            .limit(12)
            .sorted(Comparator.comparing(FinanceiroDTO.ReceitaMensalDTO::getMes))
            .collect(Collectors.toList());
    }

    private List<FinanceiroDTO.TopClienteDTO> calcularTopClientes(List<Venda> vendas) {
        Map<UUID, FinanceiroDTO.TopClienteDTO> clienteMap = new HashMap<>();

        for (Venda venda : vendas) {
            UUID clienteId = venda.getClienteId();
            clienteMap.computeIfAbsent(clienteId, k -> {
                Optional<Cliente> cliente = clienteRepository.findById(clienteId);
                String nomeCliente = cliente.map(Cliente::getNome).orElse("Cliente Desconhecido");
                return new FinanceiroDTO.TopClienteDTO(clienteId.toString(), nomeCliente, BigDecimal.ZERO, 0);
            });

            FinanceiroDTO.TopClienteDTO dto = clienteMap.get(clienteId);
            dto.setTotalGasto(dto.getTotalGasto().add(venda.getTotal()));
            dto.setTotalCompras(dto.getTotalCompras() + 1);
        }

        return clienteMap.values().stream()
            .sorted(Comparator.comparing(FinanceiroDTO.TopClienteDTO::getTotalGasto).reversed())
            .limit(10)
            .collect(Collectors.toList());
    }

    private List<FinanceiroDTO.VendaPorCategoriaDTO> calcularVendasPorCategoria(List<Venda> vendas) {
        Map<String, FinanceiroDTO.VendaPorCategoriaDTO> categoriaMap = new HashMap<>();

        for (Venda venda : vendas) {
            if (venda.getItens() != null) {
                for (Venda.ItemVenda item : venda.getItens()) {
                    Optional<Produto> produto = produtoRepository.findById(item.getProdutoId());
                    String categoria = produto.map(Produto::getCategoria).orElse("Outros");

                    categoriaMap.computeIfAbsent(categoria, k ->
                        new FinanceiroDTO.VendaPorCategoriaDTO(categoria, BigDecimal.ZERO, 0));

                    FinanceiroDTO.VendaPorCategoriaDTO dto = categoriaMap.get(categoria);
                    BigDecimal receitaItem = item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade()));
                    dto.setReceita(dto.getReceita().add(receitaItem));
                    dto.setQuantidadeVendida(dto.getQuantidadeVendida() + item.getQuantidade());
                }
            }
        }

        return categoriaMap.values().stream()
            .sorted(Comparator.comparing(FinanceiroDTO.VendaPorCategoriaDTO::getReceita).reversed())
            .collect(Collectors.toList());
    }
}
