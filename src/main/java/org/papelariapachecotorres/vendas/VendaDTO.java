package org.papelariapachecotorres.vendas;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class VendaDTO {
    private UUID id;
    private UUID clienteId;
    private List<ItemVendaDTO> itens;
    private BigDecimal total;
    private Instant data;

    public VendaDTO() {}

    public VendaDTO(UUID id, UUID clienteId, List<ItemVendaDTO> itens, BigDecimal total, Instant data) {
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
    public List<ItemVendaDTO> getItens() { return itens; }
    public void setItens(List<ItemVendaDTO> itens) { this.itens = itens; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public Instant getData() { return data; }
    public void setData(Instant data) { this.data = data; }

    public static class ItemVendaDTO {
        private UUID id;
        private VendaDTO venda;
        private UUID produtoId;
        private Integer quantidade;
        private BigDecimal precoUnitario;

        public ItemVendaDTO() {}

        public ItemVendaDTO(UUID produtoId, Integer quantidade, BigDecimal precoUnitario) {
            this.produtoId = produtoId;
            this.quantidade = quantidade;
            this.precoUnitario = precoUnitario;
        }

        public UUID getId() { return id; }
        public void setId(UUID id) { this.id = id; }
        public VendaDTO getVenda() { return venda; }
        public void setVenda(VendaDTO venda) { this.venda = venda; }
        public UUID getProdutoId() { return produtoId; }
        public void setProdutoId(UUID produtoId) { this.produtoId = produtoId; }
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