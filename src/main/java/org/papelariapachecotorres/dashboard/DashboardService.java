package org.papelariapachecotorres.dashboard;

import org.papelariapachecotorres.clientes.ClienteService;
import org.papelariapachecotorres.estoque.EstoqueService;
import org.papelariapachecotorres.produtos.ProdutoService;
import org.papelariapachecotorres.vendas.VendaService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardService {
    private final ClienteService clienteService;
    private final ProdutoService produtoService;
    private final EstoqueService estoqueService;
    private final VendaService vendaService;

    public DashboardService(ClienteService clienteService, ProdutoService produtoService, EstoqueService estoqueService, VendaService vendaService) {
        this.clienteService = clienteService;
        this.produtoService = produtoService;
        this.estoqueService = estoqueService;
        this.vendaService = vendaService;
    }

    public Dashboard getDashboard() {
        int totalClientes = clienteService.getAll().size();
        int totalProdutos = produtoService.getAll().size();
        int totalEstoque = estoqueService.getAll().size();
        int vendasHoje = vendaService.getAll().stream().filter(venda -> venda.getData().equals(LocalDate.now().toString())).toList().size();
        return new Dashboard(totalClientes, totalProdutos, totalEstoque, vendasHoje);
    }

    public ChartDataDTO getChartData() {
        var normalDistribution = calculateNormalDistributionData();
        var salesBoxplot = calculateSalesBoxplotData();
        var binomialDistribution = new ChartDataDTO.BinomialDistributionData(10, 0.05);
        var topProducts = calculateTopProductsData();
        var stockStatus = calculateStockStatusData();
        var salesPerClient = calculateSalesPerClientData();

        return new ChartDataDTO(
            normalDistribution,
            salesBoxplot,
            binomialDistribution,
            topProducts,
            stockStatus,
            salesPerClient
        );
    }

    private ChartDataDTO.NormalDistributionData calculateNormalDistributionData() {
        var vendas = vendaService.getAll();

        var valores = vendas.stream()
            .mapToDouble(venda -> venda.getTotal().doubleValue())
            .toArray();

        double media = Arrays.stream(valores).average().orElse(0.0);
        double desvio = Math.sqrt(Arrays.stream(valores)
            .map(valor -> Math.pow(valor - media, 2))
            .average().orElse(0.0));
        
        double min = Arrays.stream(valores).min().orElse(0.0);
        double max = Arrays.stream(valores).max().orElse(0.0);

        return new ChartDataDTO.NormalDistributionData(media, desvio, min, max);
    }

    private ChartDataDTO.SalesBoxplotData calculateSalesBoxplotData() {
        var vendas = vendaService.getAll();

        var valores = vendas.stream()
            .map(venda -> venda.getTotal().doubleValue())
            .collect(Collectors.toList());

        return new ChartDataDTO.SalesBoxplotData(valores, "total");
    }

    private ChartDataDTO.TopProductsData calculateTopProductsData() {
        var vendas = vendaService.getAll();
        Map<String, Integer> produtoQuantidades = new HashMap<>();
        for (var venda : vendas) {
            String produto = "Produto " + venda.getId();
            produtoQuantidades.merge(produto, 1, Integer::sum);
        }

        var produtosOrdenados = produtoQuantidades.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(9)
            .collect(Collectors.toList());

        var produtos = produtosOrdenados.stream()
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
        
        var quantidades = produtosOrdenados.stream()
            .map(Map.Entry::getValue)
            .collect(Collectors.toList());

        return new ChartDataDTO.TopProductsData(produtos, quantidades);
    }

    private ChartDataDTO.StockStatusData calculateStockStatusData() {
        var estoque = estoqueService.getAll();
        var produtos = estoque.stream()
            .map(item -> {
                var produto = produtoService.getById(item.getProdutoId());
                return produto.isPresent() ? produto.get().getNome() : "Produto " + item.getProdutoId();
            })
            .collect(Collectors.toList());
        
        var estoqueAtual = estoque.stream()
            .map(item -> item.getQuantidade())
            .collect(Collectors.toList());
        
        var estoqueMin = estoque.stream()
            .map(item -> item.getQuantidadeMinima())
            .collect(Collectors.toList());

        return new ChartDataDTO.StockStatusData(produtos, estoqueAtual, estoqueMin);
    }

    private ChartDataDTO.SalesPerClientData calculateSalesPerClientData() {
        var vendas = vendaService.getAll();
        Map<String, Double> clienteTotais = new HashMap<>();
        for (var venda : vendas) {
            var cliente = clienteService.getById(venda.getClienteId());
            String nomeCliente = cliente.isPresent() ? cliente.get().getNome() : "Cliente " + venda.getClienteId();
            clienteTotais.merge(nomeCliente, venda.getTotal().doubleValue(), Double::sum);
        }

        var clientesOrdenados = clienteTotais.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .limit(10)
            .collect(Collectors.toList());

        var clientes = clientesOrdenados.stream()
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
        
        var totais = clientesOrdenados.stream()
            .map(Map.Entry::getValue)
            .collect(Collectors.toList());

        return new ChartDataDTO.SalesPerClientData(clientes, totais);
    }
}
