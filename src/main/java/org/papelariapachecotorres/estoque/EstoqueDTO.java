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