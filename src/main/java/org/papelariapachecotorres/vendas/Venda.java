package org.papelariapachecotorres.vendas;

import java.util.List;

public class Venda {
    private Integer id;
    private Integer clienteId;
    private List<ItemVenda> itens;
    private Double total;
    private String data;

    public Venda() {}

    public Venda(Integer id, Integer clienteId, List<ItemVenda> itens, Double total, String data) {
        this.id = id;
        this.clienteId = clienteId;
        this.itens = itens;
        this.total = total;
        this.data = data;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getClienteId() { return clienteId; }
    public void setClienteId(Integer clienteId) { this.clienteId = clienteId; }
    public List<ItemVenda> getItens() { return itens; }
    public void setItens(List<ItemVenda> itens) { this.itens = itens; }
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public static class ItemVenda {
        private Integer produtoId;
        private Integer quantidade;
        private Double precoUnitario;

        public ItemVenda() {}

        public ItemVenda(Integer produtoId, Integer quantidade, Double precoUnitario) {
            this.produtoId = produtoId;
            this.quantidade = quantidade;
            this.precoUnitario = precoUnitario;
        }

        public Integer getProdutoId() { return produtoId; }
        public void setProdutoId(Integer produtoId) { this.produtoId = produtoId; }
        public Integer getQuantidade() { return quantidade; }
        public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
        public Double getPrecoUnitario() { return precoUnitario; }
        public void setPrecoUnitario(Double precoUnitario) { this.precoUnitario = precoUnitario; }
    }
} 