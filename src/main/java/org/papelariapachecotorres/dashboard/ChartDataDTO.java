package org.papelariapachecotorres.dashboard;

import java.util.List;

public class ChartDataDTO {
    private NormalDistributionData normalDistribution;
    private SalesBoxplotData salesBoxplot;
    private BinomialDistributionData binomialDistribution;
    private TopProductsData topProducts;
    private StockStatusData stockStatus;
    private SalesPerClientData salesPerClient;

    // Construtores
    public ChartDataDTO() {}

    public ChartDataDTO(
        NormalDistributionData normalDistribution,
        SalesBoxplotData salesBoxplot,
        BinomialDistributionData binomialDistribution,
        TopProductsData topProducts,
        StockStatusData stockStatus,
        SalesPerClientData salesPerClient
    ) {
        this.normalDistribution = normalDistribution;
        this.salesBoxplot = salesBoxplot;
        this.binomialDistribution = binomialDistribution;
        this.topProducts = topProducts;
        this.stockStatus = stockStatus;
        this.salesPerClient = salesPerClient;
    }

    // Getters e Setters
    public NormalDistributionData getNormalDistribution() { return normalDistribution; }
    public void setNormalDistribution(NormalDistributionData normalDistribution) { this.normalDistribution = normalDistribution; }

    public SalesBoxplotData getSalesBoxplot() { return salesBoxplot; }
    public void setSalesBoxplot(SalesBoxplotData salesBoxplot) { this.salesBoxplot = salesBoxplot; }

    public BinomialDistributionData getBinomialDistribution() { return binomialDistribution; }
    public void setBinomialDistribution(BinomialDistributionData binomialDistribution) { this.binomialDistribution = binomialDistribution; }

    public TopProductsData getTopProducts() { return topProducts; }
    public void setTopProducts(TopProductsData topProducts) { this.topProducts = topProducts; }

    public StockStatusData getStockStatus() { return stockStatus; }
    public void setStockStatus(StockStatusData stockStatus) { this.stockStatus = stockStatus; }

    public SalesPerClientData getSalesPerClient() { return salesPerClient; }
    public void setSalesPerClient(SalesPerClientData salesPerClient) { this.salesPerClient = salesPerClient; }

    // Classes internas para cada tipo de gráfico
    public static class NormalDistributionData {
        private double media;
        private double desvio;
        private double min;
        private double max;

        public NormalDistributionData() {}

        public NormalDistributionData(double media, double desvio, double min, double max) {
            this.media = media;
            this.desvio = desvio;
            this.min = min;
            this.max = max;
        }

        public double getMedia() { return media; }
        public void setMedia(double media) { this.media = media; }
        public double getDesvio() { return desvio; }
        public void setDesvio(double desvio) { this.desvio = desvio; }
        public double getMin() { return min; }
        public void setMin(double min) { this.min = min; }
        public double getMax() { return max; }
        public void setMax(double max) { this.max = max; }
    }

    public static class SalesBoxplotData {
        private List<Double> valores;
        private String categoryLabel;

        public SalesBoxplotData() {}

        public SalesBoxplotData(List<Double> valores, String categoryLabel) {
            this.valores = valores;
            this.categoryLabel = categoryLabel;
        }

        public List<Double> getValores() { return valores; }
        public void setValores(List<Double> valores) { this.valores = valores; }
        public String getCategoryLabel() { return categoryLabel; }
        public void setCategoryLabel(String categoryLabel) { this.categoryLabel = categoryLabel; }
    }

    public static class BinomialDistributionData {
        private int n;
        private double p;

        public BinomialDistributionData() {}

        public BinomialDistributionData(int n, double p) {
            this.n = n;
            this.p = p;
        }

        public int getN() { return n; }
        public void setN(int n) { this.n = n; }
        public double getP() { return p; }
        public void setP(double p) { this.p = p; }
    }

    public static class TopProductsData {
        private List<String> produtos;
        private List<Integer> quantidades;

        public TopProductsData() {}

        public TopProductsData(List<String> produtos, List<Integer> quantidades) {
            this.produtos = produtos;
            this.quantidades = quantidades;
        }

        public List<String> getProdutos() { return produtos; }
        public void setProdutos(List<String> produtos) { this.produtos = produtos; }
        public List<Integer> getQuantidades() { return quantidades; }
        public void setQuantidades(List<Integer> quantidades) { this.quantidades = quantidades; }
    }

    public static class StockStatusData {
        private List<String> produtos;
        private List<Integer> estoqueAtual;
        private List<Integer> estoqueMin;

        public StockStatusData() {}

        public StockStatusData(List<String> produtos, List<Integer> estoqueAtual, List<Integer> estoqueMin) {
            this.produtos = produtos;
            this.estoqueAtual = estoqueAtual;
            this.estoqueMin = estoqueMin;
        }

        public List<String> getProdutos() { return produtos; }
        public void setProdutos(List<String> produtos) { this.produtos = produtos; }
        public List<Integer> getEstoqueAtual() { return estoqueAtual; }
        public void setEstoqueAtual(List<Integer> estoqueAtual) { this.estoqueAtual = estoqueAtual; }
        public List<Integer> getEstoqueMin() { return estoqueMin; }
        public void setEstoqueMin(List<Integer> estoqueMin) { this.estoqueMin = estoqueMin; }
    }

    public static class SalesPerClientData {
        private List<String> clientes;
        private List<Double> totais;

        public SalesPerClientData() {}

        public SalesPerClientData(List<String> clientes, List<Double> totais) {
            this.clientes = clientes;
            this.totais = totais;
        }

        public List<String> getClientes() { return clientes; }
        public void setClientes(List<String> clientes) { this.clientes = clientes; }
        public List<Double> getTotais() { return totais; }
        public void setTotais(List<Double> totais) { this.totais = totais; }
    }
}
