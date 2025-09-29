package org.papelariapachecotorres.estoque;


import java.time.Instant;

public class EstoqueDTO {
    private Integer id;
    private Integer produtoId;
    private Integer quantidade;
    private Integer quantidadeMinima;
    private Instant ultimaAtualizacao;

    public EstoqueDTO() {
    }

    public EstoqueDTO(Integer id, Integer produtoId, Integer quantidade, Integer quantidadeMinima, Instant ultimaAtualizacao) {
        this.id = id;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.quantidadeMinima = quantidadeMinima;
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(Integer quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public Instant getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(Instant ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    Estoque toDomain() {
        return new Estoque(
                id,
                produtoId,
                quantidade,
                quantidadeMinima,
                ultimaAtualizacao
        );
    }

    public static EstoqueDTO fromDomain(Estoque estoque) {
        return new EstoqueDTO(
                estoque.getId(),
                estoque.getProdutoId(),
                estoque.getQuantidade(),
                estoque.getQuantidadeMinima(),
                estoque.getUltimaAtualizacao()
        );
    }
} 