package org.papelariapachecotorres.estoque;

import jakarta.persistence.*;

@Entity
@Table(name = "estoque")
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "produto_id", nullable = false)
    private Integer produtoId;
    
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
    
    @Column(name = "quantidade_minima", nullable = false)
    private Integer quantidadeMinima;
    
    @Column(name = "ultima_atualizacao", nullable = false)
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