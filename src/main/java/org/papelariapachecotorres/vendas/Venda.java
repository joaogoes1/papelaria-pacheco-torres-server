package org.papelariapachecotorres.vendas;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "vendas")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "cliente_id", nullable = false)
    private Integer clienteId;
    
    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItemVenda> itens;
    
    @Column(name = "total", nullable = false)
    private Double total;
    
    @Column(name = "data", nullable = false)
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

    @Entity
    @Table(name = "itens_venda")
    public static class ItemVenda {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        
        @ManyToOne
        @JoinColumn(name = "venda_id", nullable = false)
        private Venda venda;
        
        @Column(name = "produto_id", nullable = false)
        private Integer produtoId;
        
        @Column(name = "quantidade", nullable = false)
        private Integer quantidade;
        
        @Column(name = "preco_unitario", nullable = false)
        private Double precoUnitario;

        public ItemVenda() {}

        public ItemVenda(Integer produtoId, Integer quantidade, Double precoUnitario) {
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
        public Double getPrecoUnitario() { return precoUnitario; }
        public void setPrecoUnitario(Double precoUnitario) { this.precoUnitario = precoUnitario; }
    }
} 