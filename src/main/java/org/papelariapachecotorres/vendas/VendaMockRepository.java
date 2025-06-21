package org.papelariapachecotorres.vendas;

import java.util.*;

public class VendaMockRepository {
    private static final List<Venda> vendas = new ArrayList<>();
    private static int idCounter = 2;

    static {
        vendas.add(new Venda(1, 1, Arrays.asList(
            new Venda.ItemVenda(1, 2, 2.50),
            new Venda.ItemVenda(2, 1, 15.90)
        ), 20.90, "2024-01-18T14:30:00Z"));
        vendas.add(new Venda(2, 2, Arrays.asList(
            new Venda.ItemVenda(3, 5, 1.25)
        ), 6.25, "2024-01-19T16:45:00Z"));
    }

    public List<Venda> findAll() {
        return new ArrayList<>(vendas);
    }

    public Optional<Venda> findById(int id) {
        return vendas.stream().filter(v -> v.getId() == id).findFirst();
    }

    public Venda save(Venda venda) {
        venda.setId(++idCounter);
        vendas.add(venda);
        return venda;
    }

    public Venda update(int id, Venda venda) {
        Optional<Venda> existing = findById(id);
        if (existing.isPresent()) {
            Venda v = existing.get();
            v.setClienteId(venda.getClienteId());
            v.setItens(venda.getItens());
            v.setTotal(venda.getTotal());
            v.setData(venda.getData());
            return v;
        }
        return null;
    }

    public boolean delete(int id) {
        return vendas.removeIf(v -> v.getId() == id);
    }
} 