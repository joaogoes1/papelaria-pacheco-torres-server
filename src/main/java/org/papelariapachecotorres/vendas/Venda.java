package org.papelariapachecotorres.vendas;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.List;
import java.time.Instant;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "vendas")
public class Venda {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    // FK inteira conforme schema; pode evoluir para @ManyToOne(Cliente)
    @Column(name = "cliente_id", nullable = false)
    private UUID clienteId;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ItemVenda> itens;

    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;  // DECIMAL(10,2)

    @Column(name = "data", nullable = false)
    private Instant data;      // TIMESTAMP

    public Venda() {}

    public Venda(UUID id, UUID clienteId, List<ItemVenda> itens, BigDecimal total, Instant data) {
        this.id = id;
        this.clienteId = clienteId;
        this.itens = itens;
        this.total = total;
        this.data = data;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getClienteId() { return clienteId; }
    public void setClienteId(UUID clienteId) { this.clienteId = clienteId; }
    public List<ItemVenda> getItens() { return itens; }
    public void setItens(List<ItemVenda> itens) { this.itens = itens; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public Instant getData() { return data; }
    public void setData(Instant data) { this.data = data; }

    @Entity
    @Table(name = "itens_venda")
    public static class ItemVenda {
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        private UUID id;

        @ManyToOne(optional = false)
        @JoinColumn(name = "venda_id", nullable = false)
        private Venda venda;

        @Column(name = "produto_id", nullable = false)
        private UUID produtoId;

        @Column(name = "quantidade", nullable = false)
        private Integer quantidade;

        @Column(name = "preco_unitario", nullable = false, precision = 10, scale = 2)
        private BigDecimal precoUnitario;

        public ItemVenda() {}

        public ItemVenda(UUID produtoId, Integer quantidade, BigDecimal precoUnitario) {
            this.produtoId = produtoId;
            this.quantidade = quantidade;
            this.precoUnitario = precoUnitario;
        }

        public UUID getId() { return id; }
        public void setId(UUID id) { this.id = id; }
        public Venda getVenda() { return venda; }
        public void setVenda(Venda venda) { this.venda = venda; }
        public UUID getProdutoId() { return produtoId; }
        public void setProdutoId(UUID produtoId) { this.produtoId = produtoId; }
        public Integer getQuantidade() { return quantidade; }
        public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
        public BigDecimal getPrecoUnitario() { return precoUnitario; }
        public void setPrecoUnitario(BigDecimal precoUnitario) { this.precoUnitario = precoUnitario; }
    }
}
