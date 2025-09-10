package org.papelariapachecotorres.vendas;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class VendaDTO {
    private Integer id;
    private Integer clienteId;
    private List<ItemVendaDTO> itens;
    private BigDecimal total;
    private Instant data;

    public VendaDTO() {}

    public VendaDTO(Integer id, Integer clienteId, List<ItemVendaDTO> itens, BigDecimal total, Instant data) {
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
    public List<ItemVendaDTO> getItens() { return itens; }
    public void setItens(List<ItemVendaDTO> itens) { this.itens = itens; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public Instant getData() { return data; }
    public void setData(Instant data) { this.data = data; }

    public static class ItemVendaDTO {
        private Integer id;
        private VendaDTO venda;
        private Integer produtoId;
        private Integer quantidade;
        private BigDecimal precoUnitario;

        public ItemVendaDTO() {}

        public ItemVendaDTO(Integer produtoId, Integer quantidade, BigDecimal precoUnitario) {
            this.produtoId = produtoId;
            this.quantidade = quantidade;
            this.precoUnitario = precoUnitario;
        }

        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }
        public VendaDTO getVenda() { return venda; }
        public void setVenda(VendaDTO venda) { this.venda = venda; }
        public Integer getProdutoId() { return produtoId; }
        public void setProdutoId(Integer produtoId) { this.produtoId = produtoId; }
        public Integer getQuantidade() { return quantidade; }
        public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
        public BigDecimal getPrecoUnitario() { return precoUnitario; }
        public void setPrecoUnitario(BigDecimal precoUnitario) { this.precoUnitario = precoUnitario; }

        public static Venda.ItemVenda toDomain(ItemVendaDTO itemVenda) {
            return new Venda.ItemVenda(
                    itemVenda.getProdutoId(),
                    itemVenda.getQuantidade(),
                    itemVenda.getPrecoUnitario()
            );
        }

        public static ItemVendaDTO fromDomain(Venda.ItemVenda itemVenda) {
            return new ItemVendaDTO(
                    itemVenda.getProdutoId(),
                    itemVenda.getQuantidade(),
                    itemVenda.getPrecoUnitario()
            );
        }
    }

    public static Venda toDomain(VendaDTO venda) {
        return new Venda(
                venda.getId(),
                venda.getClienteId(),
                venda.getItens().stream().map(VendaDTO.ItemVendaDTO::toDomain).toList(),
                venda.getTotal(),
                venda.getData()
        );
    }

    public static VendaDTO fromDomain(Venda venda) {
        return new VendaDTO(
                venda.getId(),
                venda.getClienteId(),
                venda.getItens().stream().map(VendaDTO.ItemVendaDTO::fromDomain).toList(),
                venda.getTotal(),
                venda.getData()
        );
    }
} 