package org.papelariapachecotorres.estoque;

public class Estoque {
    private Integer id;
    private Integer produtoId;
    private Integer quantidade;
    private Integer quantidadeMinima;
    private String ultimaAtualizacao;

    public Estoque() {}

    public Estoque(Integer id, Integer produtoId, Integer quantidade, Integer quantidadeMinima, String ultimaAtualizacao) {
        this.id = id;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.quantidadeMinima = quantidadeMinima;
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getProdutoId() { return produtoId; }
    public void setProdutoId(Integer produtoId) { this.produtoId = produtoId; }
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    public Integer getQuantidadeMinima() { return quantidadeMinima; }
    public void setQuantidadeMinima(Integer quantidadeMinima) { this.quantidadeMinima = quantidadeMinima; }
    public String getUltimaAtualizacao() { return ultimaAtualizacao; }
    public void setUltimaAtualizacao(String ultimaAtualizacao) { this.ultimaAtualizacao = ultimaAtualizacao; }
} 