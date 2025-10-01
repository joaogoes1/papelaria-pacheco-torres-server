package org.papelariapachecotorres.financeiro;

import java.math.BigDecimal;
import java.util.List;

public class FinanceiroDTO {
    private BigDecimal receitaTotal;
    private BigDecimal receitaMensal;
    private BigDecimal receitaDiaria;
    private BigDecimal ticketMedio;
    private Integer totalVendas;
    private Integer totalVendasMes;
    private BigDecimal crescimentoMensal; // Percentual
    private List<ReceitaMensalDTO> receitaPorMes;
    private List<TopClienteDTO> topClientes;
    private List<VendaPorCategoriaDTO> vendasPorCategoria;

    public FinanceiroDTO() {}

    public FinanceiroDTO(BigDecimal receitaTotal, BigDecimal receitaMensal, BigDecimal receitaDiaria,
                        BigDecimal ticketMedio, Integer totalVendas, Integer totalVendasMes,
                        BigDecimal crescimentoMensal, List<ReceitaMensalDTO> receitaPorMes,
                        List<TopClienteDTO> topClientes, List<VendaPorCategoriaDTO> vendasPorCategoria) {
        this.receitaTotal = receitaTotal;
        this.receitaMensal = receitaMensal;
        this.receitaDiaria = receitaDiaria;
        this.ticketMedio = ticketMedio;
        this.totalVendas = totalVendas;
        this.totalVendasMes = totalVendasMes;
        this.crescimentoMensal = crescimentoMensal;
        this.receitaPorMes = receitaPorMes;
        this.topClientes = topClientes;
        this.vendasPorCategoria = vendasPorCategoria;
    }

    // Getters e Setters
    public BigDecimal getReceitaTotal() { return receitaTotal; }
    public void setReceitaTotal(BigDecimal receitaTotal) { this.receitaTotal = receitaTotal; }

    public BigDecimal getReceitaMensal() { return receitaMensal; }
    public void setReceitaMensal(BigDecimal receitaMensal) { this.receitaMensal = receitaMensal; }

    public BigDecimal getReceitaDiaria() { return receitaDiaria; }
    public void setReceitaDiaria(BigDecimal receitaDiaria) { this.receitaDiaria = receitaDiaria; }

    public BigDecimal getTicketMedio() { return ticketMedio; }
    public void setTicketMedio(BigDecimal ticketMedio) { this.ticketMedio = ticketMedio; }

    public Integer getTotalVendas() { return totalVendas; }
    public void setTotalVendas(Integer totalVendas) { this.totalVendas = totalVendas; }

    public Integer getTotalVendasMes() { return totalVendasMes; }
    public void setTotalVendasMes(Integer totalVendasMes) { this.totalVendasMes = totalVendasMes; }

    public BigDecimal getCrescimentoMensal() { return crescimentoMensal; }
    public void setCrescimentoMensal(BigDecimal crescimentoMensal) { this.crescimentoMensal = crescimentoMensal; }

    public List<ReceitaMensalDTO> getReceitaPorMes() { return receitaPorMes; }
    public void setReceitaPorMes(List<ReceitaMensalDTO> receitaPorMes) { this.receitaPorMes = receitaPorMes; }

    public List<TopClienteDTO> getTopClientes() { return topClientes; }
    public void setTopClientes(List<TopClienteDTO> topClientes) { this.topClientes = topClientes; }

    public List<VendaPorCategoriaDTO> getVendasPorCategoria() { return vendasPorCategoria; }
    public void setVendasPorCategoria(List<VendaPorCategoriaDTO> vendasPorCategoria) { this.vendasPorCategoria = vendasPorCategoria; }

    public static class ReceitaMensalDTO {
        private String mes; // "2025-01"
        private BigDecimal receita;
        private Integer totalVendas;

        public ReceitaMensalDTO() {}

        public ReceitaMensalDTO(String mes, BigDecimal receita, Integer totalVendas) {
            this.mes = mes;
            this.receita = receita;
            this.totalVendas = totalVendas;
        }

        public String getMes() { return mes; }
        public void setMes(String mes) { this.mes = mes; }

        public BigDecimal getReceita() { return receita; }
        public void setReceita(BigDecimal receita) { this.receita = receita; }

        public Integer getTotalVendas() { return totalVendas; }
        public void setTotalVendas(Integer totalVendas) { this.totalVendas = totalVendas; }
    }

    public static class TopClienteDTO {
        private String clienteId;
        private String clienteNome;
        private BigDecimal totalGasto;
        private Integer totalCompras;

        public TopClienteDTO() {}

        public TopClienteDTO(String clienteId, String clienteNome, BigDecimal totalGasto, Integer totalCompras) {
            this.clienteId = clienteId;
            this.clienteNome = clienteNome;
            this.totalGasto = totalGasto;
            this.totalCompras = totalCompras;
        }

        public String getClienteId() { return clienteId; }
        public void setClienteId(String clienteId) { this.clienteId = clienteId; }

        public String getClienteNome() { return clienteNome; }
        public void setClienteNome(String clienteNome) { this.clienteNome = clienteNome; }

        public BigDecimal getTotalGasto() { return totalGasto; }
        public void setTotalGasto(BigDecimal totalGasto) { this.totalGasto = totalGasto; }

        public Integer getTotalCompras() { return totalCompras; }
        public void setTotalCompras(Integer totalCompras) { this.totalCompras = totalCompras; }
    }

    public static class VendaPorCategoriaDTO {
        private String categoria;
        private BigDecimal receita;
        private Integer quantidadeVendida;

        public VendaPorCategoriaDTO() {}

        public VendaPorCategoriaDTO(String categoria, BigDecimal receita, Integer quantidadeVendida) {
            this.categoria = categoria;
            this.receita = receita;
            this.quantidadeVendida = quantidadeVendida;
        }

        public String getCategoria() { return categoria; }
        public void setCategoria(String categoria) { this.categoria = categoria; }

        public BigDecimal getReceita() { return receita; }
        public void setReceita(BigDecimal receita) { this.receita = receita; }

        public Integer getQuantidadeVendida() { return quantidadeVendida; }
        public void setQuantidadeVendida(Integer quantidadeVendida) { this.quantidadeVendida = quantidadeVendida; }
    }
}
