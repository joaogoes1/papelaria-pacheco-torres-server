package org.papelariapachecotorres.vendas;

import jakarta.persistence.*;
import java.util.List;
import java.time.Instant;
import java.math.BigDecimal;

@Entity
@Table(name = "vendas")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // FK inteira conforme schema; pode evoluir para @ManyToOne(Cliente)
    @Column(name = "cliente_id", nullable = false)
    private Integer clienteId;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ItemVenda> itens;

    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;  // DECIMAL(10,2)

    @Column(name = "data", nullable = false)
    private Instant data;      // TIMESTAMP

    public Venda() {}

    public Venda(Integer id, Integer clienteId, List<ItemVenda> itens, BigDecimal total, Instant data) {
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
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public Instant getData() { return data; }
    public void setData(Instant data) { this.data = data; }

    @Entity
    @Table(name = "itens_venda")
    public static class ItemVenda {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @ManyToOne(optional = false)
        @JoinColumn(name = "venda_id", nullable = false)
        private Venda venda;

        @Column(name = "produto_id", nullable = false)
        private Integer produtoId;

        @Column(name = "quantidade", nullable = false)
        private Integer quantidade;

        @Column(name = "preco_unitario", nullable = false, precision = 10, scale = 2)
        private BigDecimal precoUnitario;

        public ItemVenda() {}

        public ItemVenda(Integer produtoId, Integer quantidade, BigDecimal precoUnitario) {
            this.produtoId = produtoId;
            this.quantidade = quantidade;
            this.precoUnitario = precoUnitario;
        }

        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }
        public Venda getVenda() { return venda; }
        public void setVenda(Venda venda) { this.venda = venda; }
        public Integer getProdutoId() { return produtoId; }
        public void setProdutoId(Integer produtoId) { this.produtoId = produtoId; }
        public Integer getQuantidade() { return quantidade; }
        public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
        public BigDecimal getPrecoUnitario() { return precoUnitario; }
        public void setPrecoUnitario(BigDecimal precoUnitario) { this.precoUnitario = precoUnitario; }
    }
}
