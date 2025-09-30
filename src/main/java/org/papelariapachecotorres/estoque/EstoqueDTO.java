package org.papelariapachecotorres.estoque;


import java.time.Instant;
import java.util.UUID;

public class EstoqueDTO {
    private UUID id;
    private UUID produtoId;
    private Integer quantidade;
    private Integer quantidadeMinima;
    private Instant ultimaAtualizacao;

    public EstoqueDTO() {
    }

    public EstoqueDTO(UUID id, UUID produtoId, Integer quantidade, Integer quantidadeMinima, Instant ultimaAtualizacao) {
        this.id = id;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.quantidadeMinima = quantidadeMinima;
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(UUID produtoId) {
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